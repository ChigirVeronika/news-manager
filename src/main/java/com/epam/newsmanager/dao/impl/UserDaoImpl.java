package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.UserDao;
import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.User;
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
 * Dao implementation for Oracle database USERS user and User entity.
 */
public class UserDaoImpl implements UserDao {

    private static final String INSERT_USER = "insert into users (USER_NAME, LOGIN, PASSWORD) values (?,?,?)";
    private static final String UPDATE_USER = "update users set USER_NAME = ?, LOGIN = ?, PASSWORD = ? where user_id = ?";
    private static final String DELETE_USER = "delete from users where user_id = ?";
    private static final String GET_USER_BY_ID = "select * from users where user_id = ?";
    private static final String GET_ALL = "select * from users";

    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private BasicDataSource dataSource;
    private DataSourceUtils dataSourceUtils;

    /**
     * Create new record from object
     *
     * @param object create new record from this object
     * @return created object
     * @throws DaoException
     */
    public Long insert(User object) throws DaoException {
        long id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_USER);

            preparedStatement.setString(1, object.getUserName());
            preparedStatement.setString(2, object.getLogin());
            preparedStatement.setString(3, object.getPassword());
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
    public void update(User object) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(UPDATE_USER);

            preparedStatement.setString(1, object.getUserName());
            preparedStatement.setString(2, object.getLogin());
            preparedStatement.setString(3, object.getPassword());
            preparedStatement.setLong(4, object.getUserId());
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
    public void delete(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(DELETE_USER);

            preparedStatement.setLong(1, id);
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
    public Set<User> getAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<User> users = new HashSet<User>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                parseResultSet(resultSet, user);
                users.add(user);
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
        return users;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws DaoException
     */
    public User getById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = new User();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                parseResultSet(resultSet, user);
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
        return user;
    }

    private void parseResultSet(ResultSet resultSet, User user) throws SQLException {
        user.setUserId(resultSet.getLong(USER_ID));
        user.setUserName(resultSet.getString(USER_NAME));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
