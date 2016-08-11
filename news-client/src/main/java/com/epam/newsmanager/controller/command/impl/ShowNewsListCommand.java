package com.epam.newsmanager.controller.command.impl;

import com.epam.newsmanager.controller.command.Command;
import com.epam.newsmanager.controller.exception.CommandException;
import com.epam.newsmanager.service.AuthorService;
import com.epam.newsmanager.service.NewsService;
import com.epam.newsmanager.service.TagService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Show list of news
 */
public class ShowNewsListCommand implements Command {
    private static final Long NEWS_COUNT_PER_PAGE = 3L;

    private NewsService newsService;
    private AuthorService authorService;
    private TagService tagService;
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
