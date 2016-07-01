package com.epam.newsmanager.dao;

import com.epam.newsmanager.dao.exception.DaoException;
import java.util.Set;

/**
 * Interface for work with persistence objects
 * @param <T> object type
 */
public interface GenericDao<T> {

    /**
     * Create new record from object
     * @param object create new record from this object
     * @return created object
     * @throws DaoException
     */
    Long insert(T object) throws DaoException;//returns new copy

    /**
     * Update object in database
     * @param object to update
     * @throws DaoException
     */
    void update(T object) throws DaoException;

    /**
     * Delete record from database
     * @param id unique identified of object to delete
     * @throws DaoException
     */
    void delete(Long id) throws DaoException;

    /**
     * Return list of all items from database
     * @return list of all items
     * @throws DaoException
     */
    Set<T> getAll() throws DaoException;

    /**
     * Return object with primary key 'key' or null
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws DaoException
     */
    T getById(Long id) throws DaoException;
}
