package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.RoleDao;
import com.epam.newsmanager.entity.Role;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Dao implementation for Oracle database and Role entity.
 */
public class RoleDaoImpl implements RoleDao {

    private static final String INSERT_ROLE = "INSERT INTO ROLES (USER_ID, ROLE_NAME) VALUES (?,?)";
    private static final String UPDATE_ROLE = "UPDATE ROLES SET USER_ID = ?, ROLE_NAME = ?";
    private static final String DELETE_ROLE = "DELETE FROM ROLES WHERE USER_ID = ?";
    private static final String GET_BY_USER_ID = "SELECT * FROM ROLES WHERE USER_ID  = ?";
    private static final String GET_ALL = "SELECT * FROM ROLES";

    private static final String USER_ID = "USER_ID";
    private static final String ROLE_NAME = "ROLE_NAME";

    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    private BasicDataSource dataSource;
    private DataSourceUtils dataSourceUtils;

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Create new record from object
     *
     * @param object create new record from this object
     * @return created object
     * @throws DaoException
     */
    @Override
    public Long insert(Role object) throws DaoException {
        long id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_ROLE);
            preparedStatement.setLong(1, object.getUserId());
            preparedStatement.setString(2, object.getRoleName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
                object.setUserId(id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return id;
    }

    /**
     * Update object in database
     *
     * @param object to update
     * @throws DaoException
     */
    @Override
    public void update(Role object) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(UPDATE_ROLE);
            preparedStatement.setLong(1, object.getUserId());
            preparedStatement.setString(2, object.getRoleName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    /**
     * Delete record from database
     *
     * @param id unique identified of object to delete
     * @throws DaoException
     */
    @Override
    public void delete(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(DELETE_ROLE);
            preparedStatement.setLong( 1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    /**
     * Return list of all items from database
     *
     * @return list of all items
     * @throws DaoException
     */
    @Override
    public Set<Role> getAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Role> roles = new HashSet<Role>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                parseResultSet(resultSet, role);
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return roles;
    }

    /**
     * Return object role by user id or null
     *
     * @param userId user id of role object
     * @return object with or null
     * @throws DaoException if cannot return user role
     */
    @Override
    public Role getById(Long userId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Role role = new Role();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                parseResultSet(resultSet, role);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return role;
    }

    private void parseResultSet(ResultSet resultSet, Role role) throws SQLException {
        role.setUserId(resultSet.getLong(USER_ID));
        role.setRoleName(resultSet.getString(ROLE_NAME));
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param userId primary key of user object
     * @return object with or null
     * @throws DaoException if cannot return user role
     */
    @Override
    public Role getByUserId(Long userId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Role role = new Role();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                parseResultSet(resultSet, role);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return role;
    }
}
