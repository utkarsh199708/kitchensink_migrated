package com.mongodb.kitchensink_migrated.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String showLoginPage() {
        logger.info("Accessing login page");
        return "login"; // This should match the name of your HTML file without the .html extension
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        logger.info("Accessing signup page");
        return "signup";
    }

    @GetMapping("/userlist")
    public String showUserList() {
        logger.info("Accessing user list page");
        return "userlist";
    }
}
