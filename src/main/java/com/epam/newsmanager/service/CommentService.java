package com.epam.newsmanager.service;

import com.epam.newsmanager.entity.Comment;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with comment object.
 */
public interface CommentService {
    /**
     * Create new record from object
     *
     * @param comment create new record from this object
     * @return created object
     * @throws ServiceException
     */
    void insertComment(Comment comment) throws ServiceException;//returns new copy

    /**
     * Update object in database
     *
     * @param comment to update
     * @throws ServiceException
     */
    void updateComment(Comment comment) throws ServiceException;

    /**
     * Delete record from database
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    void deleteComment(Long id) throws ServiceException;

    /**
     * Return list of all items from database
     *
     * @return list of all items
     * @throws ServiceException
     */
    Set<Comment> getAllComments() throws ServiceException;

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    Comment getCommentById(Long id) throws ServiceException;

    /**
     * Get comment by news id
     *
     * @param newsId news unique identifier
     * @return set of comments
     * @throws ServiceException if cannot get news by id
     */
    Set<Comment> getCommentByNewsId(Long newsId) throws ServiceException;
}
