package com.example.springboojpacrud.controller;

import com.example.springboojpacrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminController {
    @Autowired
    UserService userService;

}
