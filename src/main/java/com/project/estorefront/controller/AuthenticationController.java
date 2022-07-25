package com.project.estorefront.controller;

import com.project.estorefront.model.*;
import com.project.estorefront.model.validators.IPasswordValidator;
import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.ValidatorFactory;
import com.project.estorefront.repository.IAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login-page";
    }

    @GetMapping("/register")
    public String register() {
        return "register-page";
    }

    @PostMapping("/validate-login")
    public ModelAndView validateLogin(@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("role") String role, HttpSession session, RedirectAttributes redirAttrs) {

        IValidator emailValidator = ValidatorFactory.instance().makeEmailValidator();
        IPasswordValidator passwordValidator = ValidatorFactory.instance().makePasswordValidator();

        if (emailValidator.validate(email) && passwordValidator.validate(password)) {

            IAuthentication authentication = AuthenticationFactory.instance().makeAuthentication();
            String userID = authentication.login(email, password);

            if (userID == null || userID.isEmpty()) {
                redirAttrs.addFlashAttribute("error", "Invalid email or password");
                return new ModelAndView("redirect:/login");
            } else {
                session.setAttribute("userID", userID.toString());
                session.setAttribute("role", role);
                if (role.contains("buyer")) {
                    return new ModelAndView("redirect:/buyer");
                } else if (role.contains("seller")) {
                    return new ModelAndView("redirect:/seller");
                } else {
                    redirAttrs.addFlashAttribute("error", "Please select a role");
                    return new ModelAndView("redirect:/login");
                }
            }
        } else {
            redirAttrs.addFlashAttribute("error", "Invalid email or password");
            return new ModelAndView("redirect:/login");
        }
    }

    @PostMapping("/validate-register")
    public ModelAndView validateRegister(@RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("contact") String contact, @RequestParam("city") String city, @RequestParam String address,
            @RequestParam("role") String role, HttpSession session, RedirectAttributes redirAttrs) {

        IValidator nameValidator = ValidatorFactory.instance().makeNameValidator();
        IValidator emailValidator = ValidatorFactory.instance().makeEmailValidator();
        IValidator phoneNumberValidator = ValidatorFactory.instance().makePhoneNumberValidator();
        IPasswordValidator passwordValidator = ValidatorFactory.instance().makePasswordValidator();

        ArrayList<String> errors = new ArrayList<>();

        if (nameValidator.validate(firstName) == false) {
            errors.add("Invalid First name");
        }

        if (nameValidator.validate(city) == false) {
            errors.add("Invalid City");
        }
        // phone number
        if (phoneNumberValidator.validate(contact) == false) {
            errors.add("Invalid Phone Number");
        }

        if (emailValidator.validate(email) == false) {
            errors.add("Invalid email");
        }
        if (passwordValidator.validate(password) == false) {
            errors.add("Invalid password");
        }
        if ((passwordValidator.comparePassword(password, confirmPassword) == false)) {
            errors.add("Password not matched");
        }

        if (errors.size() == 0) {

            IAuthentication authentication = AuthenticationFactory.instance().makeAuthentication();
            User user;

            if (role.contains("buyer")) {
                user = UserFactory.instance().getUser("buyer");
                user.setIsSeller(false);
            } else if (role.contains("seller")) {
                user = UserFactory.instance().getUser("seller");
                user.setIsSeller(true);
            } else {
                errors.add("Please select a role");
                redirAttrs.addFlashAttribute("error", "Please select a role");
                return new ModelAndView("redirect:/register");
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(contact);
            user.setCity(city);
            user.setAddress(address);
            user.setIsSeller(false);

            String userID = user.register(authentication);

            session.setAttribute("userID", userID);
            session.setAttribute("role", role);

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
        redirAttrs.addFlashAttribute("error", err);

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
    public String sendOTP(@RequestParam("email") String email, Model model, RedirectAttributes redirAttrs,
            HttpSession session) {
        IAuthentication authentication = AuthenticationFactory.instance().makeAuthentication();

        if (authentication.checkIfUserExists(email)) {
            IMailSender mailSender = MailSenderFactory.instance().makeMailSender();

            IOTPGenerator otpGenerator = MailSenderFactory.instance().makeOTPGenerator();
            String otp = otpGenerator.generateOTP();

            if (User.sendResetEmail(mailSender, email, otp)) {
                session.setAttribute("resetPwdEmail", email);
                session.setAttribute("resetPwdOTP", otp);
                return "otp-page";
            } else {
                redirAttrs.addFlashAttribute("error", "Invalid email");
                return "redirect:/reset-password";
            }
        } else {
            redirAttrs.addFlashAttribute("error", "Email not registered");
            return "redirect:/login";
        }

    }

    @PostMapping("/reset-password/verify-otp")
    public String verifyOTP(@RequestParam("otp") String otp, HttpSession session, RedirectAttributes redirAttrs) {
        String otpFromSession = (String) session.getAttribute("resetPwdOTP");
        if (otp.equals(otpFromSession)) {
            return "new-password";
        } else {
            redirAttrs.addFlashAttribute("error", "Invalid OTP");
            return "redirect:/reset-password";
        }
    }

    @PostMapping("/reset-password/reset")
    public String resetPassword(@RequestParam("password") String password, HttpSession session,
            RedirectAttributes redirAttrs) {
        String email = (String) session.getAttribute("resetPwdEmail");
        IAuthentication authentication = AuthenticationFactory.instance().makeAuthentication();

        IValidator passwordValidator = ValidatorFactory.instance().makePasswordValidator();
        if (passwordValidator.validate(password) == false) {
            redirAttrs.addFlashAttribute("error", "Invalid password");
            return "redirect:/new-password";
        }

        if (User.resetPassword(authentication, email, password)) {
            return "redirect:/login";
        } else {
            redirAttrs.addFlashAttribute("error", "Invalid OTP");
            return "redirect:/reset-password";
        }
    }

}
