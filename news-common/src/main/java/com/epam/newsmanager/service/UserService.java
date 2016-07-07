package com.epam.newsmanager.service;

import com.epam.newsmanager.entity.User;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with user object.
 */
public interface UserService {

    /**
     * Create new record in data source with specific params
     *
     * @param user user to insert
     * @return void
     * @throws ServiceException if cannot insert user
     */
    void insertUser(User user) throws ServiceException;

    /**
     * Update record in data source
     *
     * @param user user to update
     * @throws ServiceException if cannot update user
     */
    void updateUser(User user) throws ServiceException;

    /**
     * Delete record from data source
     *
     * @param id user unique identifier
     * @throws ServiceException if cannot delete user
     */
    void deleteUser(Long id) throws ServiceException;

    /**
     * Get user by id
     *
     * @param id user unique identifier
     * @return user
     * @throws ServiceException
     */
    User getUserById(Long id) throws ServiceException;

    /**
     * Get all users from data source
     *
     * @return list of all users from data source
     * @throws ServiceException if cannot get users
     */
    Set<User> getAllUsers() throws ServiceException;
}
