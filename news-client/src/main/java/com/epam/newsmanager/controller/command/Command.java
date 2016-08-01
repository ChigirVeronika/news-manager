package com.epam.newsmanager.controller.command;

import com.epam.newsmanager.controller.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for business logic classes.
 */
public interface Command {

    /**
     * Execute request corresponding to concrete command
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return String value of page to forward to
     * @throws CommandException if can't execute request correctly
     */
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException;
}

