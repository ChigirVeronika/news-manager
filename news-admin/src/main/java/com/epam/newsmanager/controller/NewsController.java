package com.epam.newsmanager.controller;

import com.epam.newsmanager.service.AuthorService;
import com.epam.newsmanager.service.NewsService;
import com.epam.newsmanager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for news service logic handling
 */
@Controller
@RequestMapping(value="/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;

    private final static Long newsCountPerPage = 3L;


}
