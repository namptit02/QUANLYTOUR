package com.example.springboojpacrud.service;

import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;



    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean checkExist(String username) {
        // Use the userRepository to check if a user with the given username exists
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }




    public void save(User user) {
        userRepository.save(user);
    }
    public String checkLogin(String username, String password, HttpSession httpSession) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null && existingUser.getPassword().equals(password)) {
            httpSession.setAttribute("user", existingUser);

            if (existingUser.getRole().equals("admin")) {
                return "redirect:/admin";
            } else if (existingUser.getRole().equals("user")) {
                return "redirect:/user";
            }
        }

        return "errorlogin";
    }


}
