package com.project.estorefront.controller;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;
import com.project.estorefront.model.UserFactory;
import com.project.estorefront.repository.Authentication;
import com.project.estorefront.repository.IAuthentication;
import com.project.estorefront.model.validators.EmailValidator;
import com.project.estorefront.model.validators.NameValidator;
import com.project.estorefront.model.validators.PasswordValidator;
import com.project.estorefront.model.validators.PhoneNumberValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ValidationController {

    @PostMapping("/validate-login")
    public ModelAndView validateLogin(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role, HttpSession session, RedirectAttributes redirAttrs) {
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();


        if (emailValidator.validate(email) && passwordValidator.validate(password)) {

            IAuthentication authentication = new Authentication();
            String userID = authentication.login(email, password);

            if (userID == null || userID.isEmpty()) {
                redirAttrs.addFlashAttribute("error", "Invalid email or password");
                return new ModelAndView("redirect:/login-page");
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
    public ModelAndView validateRegister(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                         @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
                                         @RequestParam("contact") String contact, @RequestParam("city") String city, @RequestParam String address, @RequestParam("role") String role, HttpSession session) {
        NameValidator nameValidator = new NameValidator();
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

        ArrayList<String> errors = new ArrayList<>();


        if (nameValidator.validate(firstName) == false) {
            errors.add("Invalid First name");
        }

        if (nameValidator.validate(city) == false) {
            errors.add("Invalid City");
        }
        //phone number
        if (phoneNumberValidator.validate(contact) == false) {
            errors.add("Invalid Phone Number");

        }
        if (emailValidator.validate(email) == false) {
            errors.add("Invalid email");
        }
        if (passwordValidator.validate(password) == false) {
            errors.add("Invalid password");
        }
        if (passwordValidator.comparePassword(password, confirmPassword) == false) {
            errors.add("Password not matched");
        }

        if (errors.size() == 0) {

            IAuthentication authentication = new Authentication();
            User user = null;

            if (role.contains("buyer")) {
                user = UserFactory.instance().getUser("buyer");
                user.setIsSeller(false);
            } else if (role.contains("seller")) {
                user = UserFactory.instance().getUser("seller");
                user.setIsSeller(true);
            } else {
                errors.add("Please select a role");
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(contact);
            user.setCity(city);
            user.setAddress(address);
            user.setIsSeller(false);

            String userID = authentication.register(user);
            session.setAttribute("userID", userID.toString());
            session.setAttribute("role", role);

            if (userID == null || userID.isEmpty()) {
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

        return new ModelAndView("redirect:/register", "error", err);
    }

}
