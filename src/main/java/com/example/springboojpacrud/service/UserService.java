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
    public List<User> getRandom5Users() {
        // Lấy danh sách tất cả người dùng có vai trò là "user"
        List<User> users = userRepository.findByRole("user");

        // Nếu có ít hơn 5 người dùng, bạn có thể xử lý theo ý của mình
        // ở đây, tôi sử dụng Math.min để đảm bảo không bị lỗi index nếu danh sách nhỏ hơn 5
        int count = Math.min(users.size(), 5);

        // Lấy 5 người dùng ngẫu nhiên
        List<User> randomUsers = users.subList(0, count);

        return randomUsers;
    }


}
