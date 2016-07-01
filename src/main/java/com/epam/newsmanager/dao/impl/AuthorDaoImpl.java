package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.AuthorDao;
import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.Author;
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
 * Dao implementation for Oracle database and Author entity.
 */

public class AuthorDaoImpl implements AuthorDao {
    
    private final static String INSERT_AUTHOR = "INSERT INTO AUTHORS (AUTHOR_NAME, EXPIRED) VALUES (?, ?)";
    private final static String UPDATE_AUTHOR = "UPDATE AUTHORS SET AUTHOR_NAME = ?, EXPIRED = ? WHERE AUTHOR_ID = ?";
    private final static String DELETE_AUTHOR = "DELETE FROM AUTHORS WHERE AUTHOR_ID = ?";
    private final static String GET_BY_ID = "SELECT * FROM AUTHORS WHERE AUTHOR_ID = ?";
    private final static String GET_ALL = "SELECT * FROM AUTHORS";

    private final static String GET_AUTHOR_BY_NAME = "SELECT * FROM AUTHORS WHERE AUTHOR_NAME = ?";
    private final static String GET_AUTHOR_BY_NEWS_ID = "SELECT NEWS_ID, a.AUTHOR_ID, AUTHOR_NAME, EXPIRED " +
            "FROM NEWS_AUTHORS na INNER JOIN AUTHORS a ON na.AUTHOR_ID = a.author_id WHERE na.NEWS_ID = ?";

    private static final String AUTHOR_ID = "AUTHOR_ID";
    private static final String AUTHOR_NAME = "AUTHOR_NAME";
    private static final String EXPIRED = "EXPIRED";

    private static final Logger LOGGER = Logger.getLogger(AuthorDaoImpl.class);

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
    public Long insert(Author object) throws DaoException {
        Long id = new Long(0);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_AUTHOR);

            preparedStatement.setString(1, object.getAuthorName());
            preparedStatement.setTimestamp(2, object.getExpired());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getLong(1);
                object.setAuthorId(id);
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
    public void update(Author object) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(UPDATE_AUTHOR);

            preparedStatement.setString(1, object.getAuthorName());
            preparedStatement.setTimestamp(2, object.getExpired());
            preparedStatement.setLong(3, object.getAuthorId());
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
            preparedStatement = connection.prepareStatement(DELETE_AUTHOR);

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
    @Override
    public Set<Author> getAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Author> authors = new HashSet<>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Author author = new Author();
                parseResultSet(resultSet, author);
                authors.add(author);
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
        return authors;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws DaoException
     */
    @Override
    public Author getById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Author author = new Author();

        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                parseResultSet(resultSet, author);
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
        return author;
    }


    /**
     * Get set of authors by news id
     *
     * @param newsId news unique identifier
     * @return set of authors with corresponding news identifier
     * @throws DaoException
     */
    @Override
    public Set<Author> getByNewsId(Long newsId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Author> authors = new HashSet<Author>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_AUTHOR_BY_NEWS_ID);
            preparedStatement.setLong(1, newsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                parseResultSet(resultSet, author);
                authors.add(author);
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
        return authors;
    }

    /**
     * Get author by his name
     *
     * @param author object
     * @return author with corresponding name
     * @throws DaoException
     */
    @Override
    public Author getByName(Author author) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_AUTHOR_BY_NAME);
            preparedStatement.setString(1, author.getAuthorName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                parseResultSet(resultSet, author);
            } else {
                return null;
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
        return author;
    }

    private void parseResultSet(ResultSet resultSet, Author author) throws SQLException {
        author.setAuthorId(resultSet.getLong(AUTHOR_ID));
        author.setAuthorName(resultSet.getString(AUTHOR_NAME));
        author.setExpired(resultSet.getTimestamp(EXPIRED));
    }
}
