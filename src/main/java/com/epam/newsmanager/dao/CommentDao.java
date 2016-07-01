package com.epam.newsmanager.dao;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.Comment;

import java.util.Set;

/**
 * This interface is designed to work with persistence object Comment
 */
public interface CommentDao extends GenericDao<Comment> {

    /**
     * Get comment by news id
     *
     * @param newsId news unique identifier
     * @return set of comments
     * @throws DaoException if cannot get news by id
     */
    Set<Comment> getByNewsId(Long newsId) throws DaoException;
}
