package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * LoginController class for handling user login and access control.
 * It provides methods to display the login page, handle unauthorized access,
 * and list all users.
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Displays the login page.
     *
     * @return ModelAndView object containing the view name for the login page.
     */
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     * Displays the user list page for authenticated users.
     *
     * @return ModelAndView object containing the view name and user data.
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     * Handles unauthorized access by displaying an error page.
     *
     * @return ModelAndView object containing the view name and error message.
     */
    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
