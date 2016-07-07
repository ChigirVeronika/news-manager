package com.epam.newsmanager.dao;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.Role;

/**
 * This interface is designed to work with persistence object Role
 */
public interface RoleDao extends GenericDao<Role> {

    /**
     * Return object with primary key 'key' or null
     *
     * @param userId primary key of user object
     * @return object with or null
     * @throws DaoException if cannot return user role
     */
    Role getByUserId(Long userId) throws DaoException;
}
