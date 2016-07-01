package com.epam.newsmanager.dao;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.Author;

import java.util.Set;

/**
 * This interface is designed to work with persistence object Author
 */
public interface AuthorDao extends GenericDao<Author> {

    /**
     * Get set of authors by news id
     *
     * @param newsId news unique identifier
     * @return set of authors with corresponding news identifier
     * @throws DaoException
     */
    Set<Author> getByNewsId(Long newsId) throws DaoException;

    /**
     * Get author by his name
     *
     * @param author object
     * @return author with corresponding name
     * @throws DaoException
     */
    Author getByName(Author author) throws DaoException;
}
