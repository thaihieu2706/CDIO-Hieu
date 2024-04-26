package com.example.BookSaleProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;
import com.example.BookSaleProject.Model.Entity.User;
import com.example.BookSaleProject.Model.Service.UserService;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private UserService userService = new UserService();
    private BookController bookController = new BookController();

    @GetMapping(value = "/")
    public String showLogin(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        User user = new User();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userCookie".equals(cookie.getName())) {
                    String userStr = cookie.getValue();
                    user = userService.login(userStr);
                    model.addAttribute("user", user);
                    return bookController.viewHomePage(model, user, request);
                }
            }
        }
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value = "/login")
    public String toLogin(@ModelAttribute("user") User user1, Model model,
            HttpServletResponse response,
            HttpServletRequest request,@RequestParam(value = "rememberme", required = false) Boolean rememberme) {
        User user = new User();
        user1.setAddress(null);
        user1.setId(0);
        user1.setRole(0);
        user1.setSdt(null);
        user1.setUsername(null);
        boolean flag = userService.toLogin(user1);
        if (Boolean.TRUE.equals(rememberme)) {
            if (flag) {
                Cookie cookie = new Cookie("userCookie", user1.getEmail());
                user = userService.login(user1.getEmail());
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
                return bookController.viewHomePage(model,user,request);
            } else {
                return showLogin(model, request);
            }
        }else if (!Boolean.TRUE.equals(rememberme)) {
            if (flag) {
                user = userService.login(user1.getEmail());
                return bookController.viewHomePage(model, user,request);
            }
        } else {
            user = userService.login(user1.getEmail());
            return bookController.viewHomePage(model, user,request);

        }
        return showLogin(model, request);
    }

    @GetMapping(value = "/logout")
    public String logOut(Model model, HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userCookie".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return bookController.viewHomePage(model,new User(),request);
    }

    @GetMapping(value = "/showRegist")
    public String showRegist(Model model, String messages){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        return "Register";
    }

    @PostMapping(value = "/register")
    public String toRegister(Model model,@ModelAttribute("user") User user1,
        @RequestParam(name = "passAgain")String pass) {
        user1.setRole(2);
        user1.setId(0);
        user1.setAddress(null);
        if(pass.equals(user1.getPassword())) {
            if (pass.equals(user1.getPassword())) {
                if (userService.getInvalidAttributes(user1).isEmpty() || userService.getInvalidAttributes(user1) == null) {
                    if (userService.addNew(user1)) {
                        Random random = new Random();
                        int randomNumber = random.nextInt(90000) + 10000;
                        model.addAttribute("code", randomNumber);
                        userService.sendMail(user1.getEmail(), "Code Login for you",
                                randomNumber + "");
                        return "SubmitCode";
                    } else {
                        return showRegist(model, "error");
                    }
                } else {
                    String err = "";
                    for (String error : userService.getInvalidAttributes(user1)) {
                        err = error + " ";
                    }
                    return showRegist(model, err);
                }
            } else {
                String mess = "errorpassword";
                return showRegist(model, mess);
            }
            
        }
        return showRegist(model, "");
    }
}
