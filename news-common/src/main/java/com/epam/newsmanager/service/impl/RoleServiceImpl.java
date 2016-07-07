package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.impl.RoleDaoImpl;
import com.epam.newsmanager.entity.Role;
import com.epam.newsmanager.service.RoleService;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with role objects.
 */
public class RoleServiceImpl implements RoleService{

    /**
     * Dao for role dao objects
     */
    private static RoleDaoImpl roleDao;

    public RoleServiceImpl(RoleDaoImpl roleDao){this.roleDao = roleDao;}

    /**
     * Create new record from object
     *
     * @param role create new record from this object
     * @return created object
     * @throws ServiceException
     */
    @Override
    public void insertRole(Role role) throws ServiceException {
        try{
            roleDao.insert(role);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update object
     *
     * @param role to update
     * @throws ServiceException
     */
    @Override
    public void updateRole(Role role) throws ServiceException {
        try{
            roleDao.update(role);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Delete record
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    @Override
    public void deleteRole(Long id) throws ServiceException {
        try{
            roleDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Return list of all items
     *
     * @return list of all items
     * @throws ServiceException
     */
    @Override
    public Set<Role> getAllRoles() throws ServiceException {
        Set<Role> roles;
        try{
            roles = roleDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return roles;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param userId primary key of object user
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    @Override
    public Role getRoleById(Long userId) throws ServiceException {
        Role role;
        try{
            role = roleDao.getById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return role;
    }
}
