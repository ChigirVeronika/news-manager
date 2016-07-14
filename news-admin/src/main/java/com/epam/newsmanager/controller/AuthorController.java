package com.epam.newsmanager.controller;

import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.service.AuthorService;
import com.epam.newsmanager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

/**
 * Controller for author service logic handling
 */
@Controller
@RequestMapping(value="/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value="/manage")
    public String manageAuthors(Model model) throws ServiceException {
        Set<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("author", new Author());
        return "authorManager";
    }

    @RequestMapping(params="update", method= RequestMethod.POST)
    public String updateAuthor(@ModelAttribute("author")Author author) throws ServiceException {
        authorService.updateAuthor(author);
        return "redirect:/author/manage";
    }

    @RequestMapping(params = "expired", method = RequestMethod.POST)
    public String expireAuthor(@ModelAttribute("author")Author author) throws ServiceException {
        authorService.markExpired(author.getAuthorId());
        return "redirect:/author/manage";
    }

    @RequestMapping(params = "save", method = RequestMethod.POST)
    public String addAuthor(@ModelAttribute("author")Author author) throws ServiceException {
        authorService.insertAuthor(author);
        return "redirect:/author/manage";
    }
}
