package com.epam.newsmanager.controller;

import com.epam.newsmanager.entity.Comment;
import com.epam.newsmanager.service.CommentService;
import com.epam.newsmanager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for comment service logic handling
 */
@Controller
@RequestMapping(value="/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value="save")
    public String addComment(@ModelAttribute("comment")Comment comment) throws ServiceException {
        commentService.insertComment(comment);
        return "redirect:/news/message/"+comment.getNewsId();
    }

    @RequestMapping(value="/delete/{newsId}/{commentId}")
    public String deleteComment(@PathVariable("newsId")Long newsId,
                                @PathVariable("commentId")Long commentId) throws ServiceException{
        commentService.deleteComment(commentId);
        return "redirect:/news/message/"+newsId;
    }
}
