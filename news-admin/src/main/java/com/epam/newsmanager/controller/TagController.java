package com.epam.newsmanager.controller;

import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.service.TagService;
import com.epam.newsmanager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

/**
 * Controller for news service logic handling
 */
@Controller
@RequestMapping(value="/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value="/manage")
    public String manageAuthors(Model model) throws ServiceException {
        Set<Tag> tags = tagService.getAllTags();
        model.addAttribute("tags", tags);
        model.addAttribute("tag", new Tag());
        return "tagManager";
    }

    @RequestMapping(params="update", method= RequestMethod.POST)
    public String updateTag(@ModelAttribute("tag")Tag tag) throws ServiceException {
        tagService.updateTag(tag);
        return "redirect:/tag/manage";
    }

    @RequestMapping(params="delete", method = RequestMethod.POST)
    public String deleteTag(@ModelAttribute("tag")Tag tag) throws ServiceException {
        tagService.deleteTag(tag.getTagId());
        return "redirect:/tag/manage";
    }

    @RequestMapping(params="save", method = RequestMethod.POST)
    public String addTag(@ModelAttribute("tag")Tag tag) throws ServiceException {
        tagService.insertTag(tag);
        return "redirect:/tag/manage";
    }
}
