package com.example.springboojpacrud.controller;

import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/direct-payment")
    public String directPaymentPage(Model model, HttpSession httpSession) {
        List<User> userList = userService.getRandom5Users(); // Lấy danh sách 5 người dùng ngẫu nhiên
        model.addAttribute("users", userList);
        return "direct-payment";
    }
}
