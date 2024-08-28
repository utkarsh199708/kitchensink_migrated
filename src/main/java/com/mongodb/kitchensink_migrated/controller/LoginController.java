package com.mongodb.kitchensink_migrated.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This should match the name of your HTML file without the .html extension
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @GetMapping("/userlist")
    public String showUserList() {

        return "userlist";
    }

}