package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.CommentDao;
import com.epam.newsmanager.entity.Comment;
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
 * Dao implementation for Oracle database and Comment entity.
 */
public class CommentDaoImpl implements CommentDao {

        private static final String INSERT_COMMENT = "INSERT INTO COMMENTS (NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES (?, ?, ?)";
    private static final String UPDATE_COMMENT = "UPDATE COMMENTS SET COMMENT_TEXT = ?";
    private static final String DELETE_COMMENT = "DELETE FROM COMMENTS WHERE COMMENT_ID = ?";
    private static final String GET_BY_ID = "SELECT * FROM COMMENTS WHERE COMMENT_ID = ?";
    private static final String GET_BY_NEWS_ID = "SELECT * FROM COMMENTS WHERE NEWS_ID = ?";
    private static final String GET_ALL = "SELECT * FROM COMMENTS";

    private static final String COMMENT_ID = "COMMENT_ID";
    private static final String NEWS_ID = "NEWS_ID";
    private static final String COMMENT_TEXT = "COMMENT_TEXT";
    private static final String CREATION_DATE = "CREATION_DATE";

    private static final Logger LOGGER = Logger.getLogger(CommentDaoImpl.class);

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
    public Long insert(Comment object) throws DaoException {
        Long id = new Long(0);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_COMMENT);

            preparedStatement.setLong(1, object.getNewsId());
            preparedStatement.setString(2, object.getCommentText());
            preparedStatement.setTimestamp(3, object.getCreationDate());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
                object.setCommentId(id);
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
    public void update(Comment object) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(UPDATE_COMMENT);

            preparedStatement.setString(1, object.getCommentText());
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
            preparedStatement = connection.prepareStatement(DELETE_COMMENT);

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
    public Set<Comment> getAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Comment> comments = new HashSet<Comment>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                parseResultSet(resultSet, comment);
                comments.add(comment);
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
        return comments;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws DaoException
     */
    @Override
    public Comment getById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Comment comment = new Comment();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                parseResultSet(resultSet, comment);
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
        return comment;
    }

    /**
     * Get comment by news id
     *
     * @param newsId news unique identifier
     * @return set of comments
     * @throws DaoException if cannot get news by id
     */
    @Override
    public Set<Comment> getByNewsId(Long newsId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Comment> comments = new HashSet<Comment>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_BY_NEWS_ID);
            preparedStatement.setLong(1, newsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                parseResultSet(resultSet, comment);
                comments.add(comment);
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
        return comments;
    }

    private void parseResultSet(ResultSet resultSet, Comment comment) throws SQLException {
       comment.setCommentId(resultSet.getLong(COMMENT_ID));
       comment.setNewsId(resultSet.getLong(NEWS_ID));
       comment.setCommentText(resultSet.getString(COMMENT_TEXT));
       comment.setCreationDate(resultSet.getTimestamp(CREATION_DATE));
    }
}
