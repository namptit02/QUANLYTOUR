package com.example.springboojpacrud.controller;

import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/user")
    public String getUserPage(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("user",user);
        }
        return "user";
    }
    @GetMapping("/mysettings")
    public String getProfilePage() {
        // Xử lý hiển thị trang quản lý thông tin cá nhân
        return "mysettings"; // Đảm bảo rằng bạn đã cấu hình view "profile" trong Spring Boot
    }

    @GetMapping("/booking-history")
    public String getBookingHistoryPage() {
        // Xử lý hiển thị trang lịch sử đặt tour
        return "booking-history"; // Đảm bảo rằng bạn đã cấu hình view "booking-history" trong Spring Boot
    }
    @GetMapping("/mysettings/accountmanage")
    public String getInformationUser(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            // Lấy thông tin người dùng từ cơ sở dữ liệu bằng JPA
            User userFromDatabase = userService.findById(user.getId());

            if (userFromDatabase != null) {
                model.addAttribute("user", userFromDatabase);
                return "accountmanage";
            }
        }

        return "error";
    }
//    @PostMapping("/updateUser")
//    public String updateUserInfo(Model model, HttpSession httpSession, @ModelAttribute User updatedUser) {
//        User user = (User) httpSession.getAttribute("user");
//        if (user != null) {
//            // Cập nhật thông tin người dùng trong cơ sở dữ liệu bằng JPA
//            User userFromDatabase = userService.findById(user.getId());
//
//            if (userFromDatabase != null) {
//                // Kiểm tra xem trường "dob" có giá trị hợp lệ hay không
//                if (isValidDate(String.valueOf(updatedUser.getDob()))) {
//                    // Cập nhật thông tin người dùng từ updatedUser (biểu mẫu người dùng đã gửi) vào userFromDatabase
//                    userFromDatabase.setFullname(updatedUser.getFullname());
//                    userFromDatabase.setAddress(updatedUser.getAddress());
//                    userFromDatabase.setHometown(updatedUser.getHometown());
//                    userFromDatabase.setPhoneNumber(updatedUser.getPhoneNumber());
//                    userFromDatabase.setEmail(updatedUser.getEmail());
//
//                    // Lưu thông tin người dùng đã cập nhật
//                    userService.registerUser(userFromDatabase);
//
//                    // Đặt thông báo thành công
//                    model.addAttribute("successMessage", "Cập nhật thông tin thành công.");
//
//                    // Cập nhật thông tin người dùng đã cập nhật vào model và hiển thị trang "accountmanage"
//                    model.addAttribute("user", userFromDatabase);
//                    return "accountmanage";
//                } else {
//                    // Trường "dob" không hợp lệ, xử lý lỗi và hiển thị thông báo lỗi
//                    model.addAttribute("errorMessage", "Ngày sinh không hợp lệ.");
//                    return "accountmanage";
//                }
//            }
//        }
//
//        return "error";
//    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date parsedDate = sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }







}
