package com.epam.newsmanager.service;

import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with category object.
 */
public interface AuthorService {

    /**
     * Create new record from object
     *
     * @param author create new record from this object
     * @return created object
     * @throws ServiceException
     */
    void insertAuthor(Author author) throws ServiceException;//returns new copy

    /**
     * Update object in database
     *
     * @param author to update
     * @throws ServiceException
     */
    void updateAuthor(Author author) throws ServiceException;

    /**
     * Delete record from database
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    void deleteAuthor(Long id) throws ServiceException;

    /**
     * Return list of all items from database
     *
     * @return list of all items
     * @throws ServiceException
     */
    Set<Author> getAllAuthors() throws ServiceException;

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    Author getAuthorById(Long id) throws ServiceException;

    /**
     * Get set of authors by news id
     *
     * @param newsId news unique identifier
     * @return set of authors with corresponding news identifier
     * @throws ServiceException
     */
    Set<Author> getAuthorByNewsId(Long newsId) throws ServiceException;

    /**
     * Get author by his name
     *
     * @param author object
     * @return author with corresponding name
     * @throws ServiceException
     */
    Author getAuthorByName(Author author) throws ServiceException;


}
