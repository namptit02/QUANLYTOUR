package com.example.springboojpacrud.controller;

import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage( HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            if (user.getRole().equals("admin")) {
                return "redirect:/admin";
            } else if (user.getRole().equals("user")) {
                return "redirect:/user";
            }
        }
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,  HttpSession httpSession) {
        return userService.checkLogin(username,password,httpSession);
    }

    @GetMapping("/register")
    public String registerPage(Model model) throws IOException {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("fullname") String fullname,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("address") String address,
                           @RequestParam("hometown") String hometown,
                           @RequestParam(value = "DOB", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
                           Model model) {
        boolean userExists = userService.checkExist(username); // Assuming userService has a method to check if the user exists

        if (userExists) {
            return "error-register";
        } else {
            User user = new User();
            user.setFullname(fullname);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole("user");
            user.setDob(dob);
            user.setAddress(address);
            user.setHometown(hometown);
            userService.registerUser(user);
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(Model model, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        httpSession.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/user")
    public String getUserPage(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("user",user);
        }
        return "user";
    }
    @GetMapping("/admin")
    public String getAdminPage(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("user",user);
        }

        return "admin";
    }

}