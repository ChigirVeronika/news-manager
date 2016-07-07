package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.impl.UserDaoImpl;
import com.epam.newsmanager.entity.User;
import com.epam.newsmanager.service.UserService;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with user objects.
 */
public class UserServiceImpl implements UserService{

    /**
     * Dao for user dao objects
     */
    private static UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    /**
     * Create new record in data source with specific params
     *
     * @param user user to insert
     * @return void
     * @throws ServiceException if cannot insert user
     */
    @Override
    public void insertUser(User user) throws ServiceException {
        try {
            userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update record in data source
     *
     * @param user user to update
     * @throws ServiceException if cannot update user
     */
    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Delete record from data source
     *
     * @param id user unique identifier
     * @throws ServiceException if cannot delete user
     */
    @Override
    public void deleteUser(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Get user by id
     *
     * @param id user unique identifier
     * @return user
     * @throws ServiceException
     */
    @Override
    public User getUserById(Long id) throws ServiceException {
        User user;
        try {
            user = userDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    /**
     * Get all users from data source
     *
     * @return list of all users from data source
     * @throws ServiceException if cannot get users
     */
    @Override
    public Set<User> getAllUsers() throws ServiceException {
        Set<User> users;
        try {
            users = userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }
}
