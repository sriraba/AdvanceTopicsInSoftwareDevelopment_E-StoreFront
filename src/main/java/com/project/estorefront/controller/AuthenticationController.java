package com.project.estorefront.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.project.estorefront.model.*;
import com.project.estorefront.model.authentication.*;
import com.project.estorefront.model.database.DatabaseFactory;
import com.project.estorefront.model.user.Seller;
import com.project.estorefront.model.user.User;
import com.project.estorefront.model.user.UserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.estorefront.model.validators.IPasswordValidator;
import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.ValidatorFactory;
import com.project.estorefront.model.database.IDatabase;

@Controller
public class AuthenticationController {

    IDatabase database;
    IAuthentication authentication;

    public AuthenticationController() {
        database = DatabaseFactory.instance().makeDatabase();
        authentication = AuthenticationFactory.instance().makeAuthentication(database);
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login-page";
    }

    @GetMapping("/register")
    public String register(Model model) {
        ArrayList<String> cities = new ArrayList<>();
        for (CityEnum city : CityEnum.values()) {
            cities.add(city.label.toString());
        }
        model.addAttribute("cities", cities);
        return "register-page";
    }

    @PostMapping("/validate-login")
    public ModelAndView validateLogin(@RequestParam("email") String email, @RequestParam("password") String password,
                                      @RequestParam("role") String role, HttpSession session, RedirectAttributes redirectAttributes) {

        IValidator emailValidator = ValidatorFactory.instance().makeEmailValidator();
        IPasswordValidator passwordValidator = ValidatorFactory.instance().makePasswordValidator();

        if (emailValidator.validate(email) && passwordValidator.validate(password)) {

            User user;
            try {
                user = User.login(authentication, email, password);
                if (user == null || user.getUserID() == null || user.getUserID().isEmpty()) {
                    redirectAttributes.addFlashAttribute("error", "Invalid email or password");
                    return new ModelAndView("redirect:/login");
                } else {
                    session.setAttribute("userID", user.getUserID());
                    session.setAttribute("role", role);
                    session.setAttribute("city", user.getCity());
                    if (role.contains("buyer")) {
                        return new ModelAndView("redirect:/buyer");
                    } else if (role.contains("seller")) {
                        if (User.checkIfUserIsSeller(authentication, email)) {
                            return new ModelAndView("redirect:/seller/orders/view");
                        } else {
                            redirectAttributes.addFlashAttribute("error", "You are not a seller");
                            return new ModelAndView("redirect:/login");
                        }
                    } else {
                        redirectAttributes.addFlashAttribute("error", "Please select a role");
                        return new ModelAndView("redirect:/login");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Something went wrong");
                return new ModelAndView("redirect:/login");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return new ModelAndView("redirect:/login");
        }
    }

    @PostMapping("/validate-register")
    public ModelAndView validateRegister(@RequestParam("firstName") String firstName,
                                         @RequestParam("lastName") String lastName,
                                         @RequestParam("email") String email, @RequestParam("password") String password,
                                         @RequestParam("confirmPassword") String confirmPassword,
                                         @RequestParam("contact") String contact, @RequestParam("city") String city, @RequestParam String address,
                                         @RequestParam("role") String role, @RequestParam("businessName") String businessName,
                                         @RequestParam("businessDescription") String businessDescription, HttpSession session,
                                         RedirectAttributes redirectAttributes) {

        IValidator nameValidator = ValidatorFactory.instance().makeNameValidator();
        IValidator emailValidator = ValidatorFactory.instance().makeEmailValidator();
        IValidator phoneNumberValidator = ValidatorFactory.instance().makePhoneNumberValidator();
        IPasswordValidator passwordValidator = ValidatorFactory.instance().makePasswordValidator();

        ArrayList<String> errors = new ArrayList<>();

        if (nameValidator.validate(firstName) == false) {
            errors.add("Invalid First name. Name should only contain letters.");
        }

        if (nameValidator.validate(city) == false) {
            errors.add("Invalid City.");
        }

        if (phoneNumberValidator.validate(contact) == false) {
            errors.add("Invalid Phone Number. Phone should only contain numbers and should be 10-digit");
        }

        if (emailValidator.validate(email) == false) {
            errors.add("Invalid email");
        }
        if (passwordValidator.validate(password) == false) {
            errors.add(
                    "Invalid password. Password should contain an uppercase letter, one lowercase, one number, one symbol and should be greater than 8 digits.");
        }
        if ((passwordValidator.comparePassword(password, confirmPassword) == false)) {
            errors.add("Passwords do not match");
        }

        if (errors.size() == 0) {
            User user;

            CityEnum cityEnum = CityEnum.valueOf(city.toUpperCase());

            if (role.contains("buyer")) {
                user = UserFactory.instance().getUser("buyer");
                user.setIsSeller(false);
            } else if (role.contains("seller")) {
                user = UserFactory.instance().getUser("seller");
                user.setIsSeller(true);
                ((Seller) user).setBusinessName(businessName);
                ((Seller) user).setBusinessDescription(
                        businessDescription);
            } else {
                errors.add("Please select a role");
                redirectAttributes.addFlashAttribute("error", "Please select a role");
                return new ModelAndView("redirect:/register");
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(contact);
            user.setCity(cityEnum.label);
            user.setAddress(address);

            String userID;
            try {
                userID = user.register(authentication);
            } catch (SQLException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Something went wrong");
                return new ModelAndView("redirect:/register");
            }

            session.setAttribute("userID", userID);
            session.setAttribute("role", role);
            session.setAttribute("city", city);

            if (userID.isEmpty()) {
                errors.add("Email already exists");
            } else {
                if (role.contains("buyer")) {
                    return new ModelAndView("redirect:/buyer");
                } else if (role.contains("seller")) {
                    return new ModelAndView("redirect:/seller");
                } else {
                    errors.add("Please select a role");
                }
            }

            if (role.contains("buyer")) {
                return new ModelAndView("redirect:/buyer");
            } else if (role.contains("seller")) {
                return new ModelAndView("redirect:/seller");
            } else {
                errors.add("Role");
            }
        }
        String err = String.join(", ", errors);
        redirectAttributes.addFlashAttribute("error", err);

        return new ModelAndView("redirect:/register");
    }

    @GetMapping
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }

    @PostMapping("/reset-password/send-otp")
    public String sendOTP(@RequestParam("email") String email, Model model, RedirectAttributes redirectAttributes,
                          HttpSession session) {
        try {
            if (User.checkIfUserExists(authentication, email)) {
                IMailSender mailSender = MailSenderFactory.instance().makeMailSender();

                IOTPGenerator otpGenerator = MailSenderFactory.instance().makeOTPGenerator();
                String otp = otpGenerator.generateOTP();

                if (User.sendResetEmail(mailSender, email, otp)) {
                    session.setAttribute("resetPwdEmail", email);
                    session.setAttribute("resetPwdOTP", otp);
                    return "otp-page";
                } else {
                    redirectAttributes.addFlashAttribute("error", "Invalid email");
                    return "redirect:/reset-password";
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "Email not registered");
                return "redirect:/login";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Something went wrong");
            return "redirect:/reset-password";
        }
    }

    @PostMapping("/reset-password/verify-otp")
    public String verifyOTP(@RequestParam("otp") String otp, HttpSession session,
                            RedirectAttributes redirectAttributes) {
        String otpFromSession = (String) session.getAttribute("resetPwdOTP");
        if (otp.equals(otpFromSession)) {
            return "new-password";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid OTP");
            return "redirect:/reset-password";
        }
    }

    @PostMapping("/reset-password/reset")
    public String resetPassword(@RequestParam("password") String password, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        String email = (String) session.getAttribute("resetPwdEmail");

        IValidator passwordValidator = ValidatorFactory.instance().makePasswordValidator();
        if (passwordValidator.validate(password) == false) {
            redirectAttributes.addFlashAttribute("error", "Invalid password");
            return "redirect:/new-password";
        }

        try {
            if (User.resetPassword(authentication, email, password)) {
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid OTP");
                return "redirect:/reset-password";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Something went wrong");
            return "redirect:/reset-password";
        }
    }
}
