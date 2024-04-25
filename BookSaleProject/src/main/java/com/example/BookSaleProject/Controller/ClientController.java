package com.example.BookSaleProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/client"})
public class ClientController {

    @Autowired
    UserService userService = new UserService();
    BookController bookController = new BookController();

    @GetMapping(value = "")
    public String showLogin(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        User user = new User();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userCookie".equals(cookie.getName())) {
                    String userStr = cookie.getValue();
                    user = userService.login(userStr);
                    model.addAttribute("user", user);
                    return bookController.viewHomePage(model, user);
                }
            }
        }
        model.addAttribute("user", user);
        return "Login";
    }

    @PostMapping(value = "/login")
    public String toLogin(@ModelAttribute("user") User user1, Model model,
            HttpServletResponse response,
            HttpServletRequest request) {
        User user = new User();
        user1.setAddress(null);
        
        user1.setId(0);
        user1.setRole(2);
        user1.setSdt(null);
        user1.setUsername(null);
        System.out.println(user1.toString());
        boolean flag = userService.toLogin(user1);
        if (flag) {
            Cookie cookie = new Cookie("userCookie", user1.getEmail());
            user = userService.login(user1.getEmail());
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute("userName", user.getUsername());
            return bookController.viewHomePage(model, user);
        } else {
            return showLogin(model, request);
        }
    }
    
}
