package com.epam.newsmanager.controller.command.impl;

import com.epam.newsmanager.controller.command.Command;
import com.epam.newsmanager.controller.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Veranika_Chyhir on 8/1/2016.
 */
public class ChangeLanguageCommand implements Command {
    /**
     * Execute request corresponding to concrete command
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return String value of page to forward to
     * @throws CommandException if can't execute request correctly
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String language = request.getParameter("language");
        request.getSession().setAttribute("locale", new Locale(language));

        return request.getSession().getAttribute("page").toString();
    }
}