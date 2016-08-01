package com.epam.newsmanager.controller.command.impl;

import com.epam.newsmanager.controller.command.Command;
import com.epam.newsmanager.controller.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Veranika_Chyhir on 8/1/2016.
 */
public class AddCommentCommand implements Command {
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
        return null;
    }
}
