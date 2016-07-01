package com.epam.newsmanager.service;

import com.epam.newsmanager.entity.Role;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with role object.
 */
public interface RoleService {
    /**
     * Create new record from object
     *
     * @param role create new record from this object
     * @return created object
     * @throws ServiceException
     */
    void insertRole(Role role) throws ServiceException;//returns new copy

    /**
     * Update object
     *
     * @param role to update
     * @throws ServiceException
     */
    void updateRole(Role role) throws ServiceException;

    /**
     * Delete record
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    void deleteRole(Long id) throws ServiceException;

    /**
     * Return list of all items
     *
     * @return list of all items
     * @throws ServiceException
     */
    Set<Role> getAllRoles() throws ServiceException;

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    Role getRoleById(Long id) throws ServiceException;
}
