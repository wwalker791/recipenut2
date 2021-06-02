package com.recipenut.recipenut.controllers;

import com.recipenut.recipenut.Models.User;

import com.recipenut.recipenut.Models.data.UserRepository;
import com.recipenut.recipenut.Models.dto.LoginDTO;
import com.recipenut.recipenut.Models.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/registration")
    public String showRegistration(Model model) {
        model.addAttribute("title", "Sign up");
        model.addAttribute(new RegisterDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistration(@ModelAttribute @Valid RegisterDTO registerDTO, Errors errors,
                                      HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "registration");
            return "registration";
        }

        User existingUser = userRepository.findByUsername(registerDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.exists", "That username is already taken.");
            model.addAttribute("title", "Registration");
            return "registration";
        }

        String password = registerDTO.getPassword();
        String verifyPassword = registerDTO.getVerifyPassword();

        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "password.mismatch", "Passwords do not match.");
            model.addAttribute("title", "Registration");
            return "registration";

        }

        User newUser = new User(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getPassword(), registerDTO.getVerifyPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
//        newUser.setPassword(encodedPassword);
//        userRepository.save(newUser);

        return "registration_successful";
    }

    @GetMapping("/login")
    public String displayLogin(Model model) {
        model.addAttribute(new LoginDTO());
        model.addAttribute("title", "Log in");
        return "/login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginDTO loginDTO, Errors errors,
                                   HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Log in");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "That username doesn't seem to exist yet");
        }

        String password = loginDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log in");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }
}
