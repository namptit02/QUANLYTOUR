package com.example.springboojpacrud.controller;

import com.example.springboojpacrud.model.Booking;
import com.example.springboojpacrud.model.Tour;
import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.service.BookingService;
import com.example.springboojpacrud.service.TourService;
import com.example.springboojpacrud.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TourService tourService;
    @Autowired
    BookingService bookingService;

    @GetMapping("/setting")
    public String getProfilePage() {
        return "setting";
    }

    @GetMapping("/find-and-book-tour")
    public String getBookingTourPage() {
        return "find-tour"; //
    }

    @GetMapping("/bookingHistory")
    public String getBookingHistoryPage() {
        return "booking-history"; //
    }

    @GetMapping("/setting/account")
    public String getInformationUser(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "account";
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
        }
        return "redirect:/user";
    }

    @GetMapping("/tour-list")
    public String listTourPage(Model model, HttpSession httpSession) {
        List<Tour> tours = tourService.getRandom10Tours();
        model.addAttribute("tours", tours);
        return "tour-list";
    }

    @GetMapping("/tour-list/search")
    public String searchTours(@RequestParam("keyword") String keyword, Model model) {
        List<Tour> searchedTours = tourService.searchTours(keyword);
        model.addAttribute("tours", searchedTours);
        return "tour-list";
    }

    @GetMapping("/tour-list/tour-detail/{id}")
    public String viewTourDetail(@PathVariable("id") int id, Model model, HttpSession httpSession) {
        Tour tour = tourService.getTourById(id);
        if (tour != null) {

            httpSession.setAttribute("tour", tour);
            model.addAttribute("tour", tour);
//            model.addAttribute("numberOfTickets", numberOfTickets);
            return "tour-detail"; // Redirect to the "tour-detail" page
        }
        return "redirect:/tour-list";
    }


    @GetMapping("/confirm-information")
    public String confirmInformationPage(Model model, HttpSession httpSession,
                                         @RequestParam(name = "numberOfTickets", required = false) Integer numberOfTickets,
                                         @RequestParam(name = "startDate", required = false) String startDate

    ) {
        User user = (User) httpSession.getAttribute("user");
        Tour tour = (Tour) httpSession.getAttribute("tour");
        httpSession.setAttribute("numberOfTickets", numberOfTickets);
        httpSession.setAttribute("startDate", startDate);

        if (user != null && tour != null) {
            model.addAttribute("numberOfTickets", numberOfTickets);
            model.addAttribute("startDate", startDate);
            model.addAttribute("totalPrice", tour.getPriceTicket() * numberOfTickets);
            model.addAttribute("user", user);
            model.addAttribute("tour", tour);
            return "confirm-information";
        }
        return "error";
    }

    @GetMapping("/payment-bill")
    public String paymentBillPage(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Tour tour = (Tour) httpSession.getAttribute("tour");
        httpSession.getAttribute("numberOfTickets");
        httpSession.getAttribute("startDate");

        Integer numberOfTickets = (Integer) httpSession.getAttribute("numberOfTickets");
        System.out.println("Số lượng vé từ HttpSession: " + numberOfTickets);
        String start_date = (String) httpSession.getAttribute("startDate");
        if (user != null && tour != null) {
            model.addAttribute("numberOfTickets", numberOfTickets);
            model.addAttribute("totalPrice", tour.getPriceTicket() * numberOfTickets);
            model.addAttribute("user", user);
            model.addAttribute("tour", tour);
            model.addAttribute("startDate", start_date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Booking booking = new Booking();
            try {
                booking.setStartDate(dateFormat.parse(start_date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            booking.setUser(user);
            booking.setTour(tour);
            booking.setNumberOfTickets(numberOfTickets);
            booking.setTotalPrice(tour.getPriceTicket() * numberOfTickets);
            bookingService.save(booking);
            return "payment-bill";
        }
        return "error";
    }

}
