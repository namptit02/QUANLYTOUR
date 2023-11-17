package com.example.springboojpacrud.controller;

import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    UserService userService;


    @GetMapping("/setting")
    public String getProfilePage() {
        return "setting";
    }

    @GetMapping("/bookingTour")
    public String getBookingTourPage() {
        return "booking-tour"; //
    }

    @GetMapping("/bookingHistory")
    public String getBookingHistoryPage() {
        return "booking-history"; //
    }

    @GetMapping("/setting/account")
    public String getInformationUser(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            User userFromDatabase = userService.findByUsername(user.getUsername());

            if (userFromDatabase != null) {
                model.addAttribute("user", userFromDatabase);
                return "account";
            }
        }

        return "error";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User updatedUser, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            user.setFullname(updatedUser.getFullname());
            user.setDob(updatedUser.getDob());
            user.setAddress(updatedUser.getAddress());
            user.setHometown(updatedUser.getHometown());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setEmail(updatedUser.getEmail());
            userService.save(user);
            httpSession.setAttribute("successMessage", "Thay đổi đã được lưu thành công.");
        }
        return "redirect:/user";
    }
}
