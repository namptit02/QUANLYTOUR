package com.example.springboojpacrud.service;

import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

//    private static List<User> listUser = new ArrayList<>();


    public void registerUser(User user) {
        userRepository.save(user);
    }
    public boolean checkExist(String username) {
        // Use the userRepository to check if a user with the given username exists
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }

    public User findByUsername(String username) {
        return  userRepository.findByUsername(username);

    }
    public User findById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null); // Hoặc xử lý khác nếu cần thiết
    }





}
