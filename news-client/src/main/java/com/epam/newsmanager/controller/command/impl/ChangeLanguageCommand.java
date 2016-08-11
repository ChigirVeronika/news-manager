package com.epam.newsmanager.controller.command.impl;

import com.epam.newsmanager.controller.command.Command;
import com.epam.newsmanager.controller.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Provides internationalization
 */
public class ChangeLanguageCommand implements Command {
    /**
     * Executes request corresponding to concrete command
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return String value of page to forward to
     * @throws CommandException if can't execute request correctly
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);
        session.setAttribute("language", request.getParameter("language"));
        return request.getParameter("page").toString();
    }
}