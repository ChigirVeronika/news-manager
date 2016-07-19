package com.epam.newsmanager.controller;

import com.epam.newsmanager.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for login/logout logic handling
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @RequestMapping("/login")
    public ModelAndView toLoginPage(@RequestParam(required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken) {
            model.addObject("user", new User());
            model.setViewName("login");
            if (error != null) {
                model.addObject("error", "login.error");
            }
        } else {
            model.setViewName("redirect:/news/list");
        }

        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login?logout";
    }
}
