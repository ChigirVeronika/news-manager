package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.impl.CommentDaoImpl;
import com.epam.newsmanager.entity.Comment;
import com.epam.newsmanager.service.CommentService;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with comment objects.
 */
public class CommentServiceImpl implements CommentService {

    /**
     * Dao for comment dao objects
     */
    private static CommentDaoImpl commentDao;

    public CommentServiceImpl(CommentDaoImpl commentDao){this.commentDao = commentDao;}

    /**
     * Create new record from object
     *
     * @param comment create new record from this object
     * @return created object
     * @throws ServiceException
     */
    @Override
    public void insertComment(Comment comment) throws ServiceException {
        try{
            commentDao.insert(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update object in database
     *
     * @param comment to update
     * @throws ServiceException
     */
    @Override
    public void updateComment(Comment comment) throws ServiceException {
        try{
            commentDao.update(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Delete record from database
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    @Override
    public void deleteComment(Long id) throws ServiceException {
        try{
            commentDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Return list of all items from database
     *
     * @return list of all items
     * @throws ServiceException
     */
    @Override
    public Set<Comment> getAllComments() throws ServiceException {
        Set<Comment> comments;
        try{
            comments = commentDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    @Override
    public Comment getCommentById(Long id) throws ServiceException {
        Comment comment;
        try{
            comment = commentDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comment;
    }

    /**
     * Get comment by news id
     *
     * @param newsId news unique identifier
     * @return set of comments
     * @throws ServiceException if cannot get news by id
     */
    @Override
    public Set<Comment> getCommentByNewsId(Long newsId) throws ServiceException {
        Set<Comment> comments;
        try{
            comments = commentDao.getByNewsId(newsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return comments;
    }
}
