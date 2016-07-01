package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.TagDao;
import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.Tag;
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
 * Dao implementation for Oracle database and Tag entity.
 */
public class TagDaoImpl implements TagDao {

    private static final String GET_TAG_BY_NEWS_ID = "SELECT NEWS_ID, t.TAG_ID, TAG_NAME FROM " +
            "NEWS_TAGS nt INNER JOIN TAGS t on nt.TAG_ID = t.tag_id WHERE nt.NEWS_ID = ?";
    private static final String INSERT_TAG = "INSERT INTO TAGS (TAG_NAME) VALUES (?)";
    private static final String UPDATE_TAG = "UPDATE TAGS SET TAG_NAME = ? where TAG_ID = ?";
    private static final String DELETE_TAG = "DELETE FROM TAGS WHERE TAG_ID = ?";
    private static final String GET_TAG_BY_ID = "SELECT * FROM TAGS WHERE TAG_ID = ?";
    private static final String GET_TAG_BY_NAME = "SELECT * FROM TAGS WHERE TAG_NAME = ?";
    private static final String GET_ALL = "SELECT * FROM TAGS";

    private static final String TAG_ID = "TAG_ID";
    private static final String TAG_NAME = "TAG_NAME";

    private static final Logger LOGGER = Logger.getLogger(TagDaoImpl.class);

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
    public Long insert(Tag object) throws DaoException {
        Connection connection = null;
        long id = 0;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_TAG);
            preparedStatement.setString(1, object.getTagName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
            {
                id = resultSet.getLong(1);
                object.setTagId(id);
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
    public void update(Tag object) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(UPDATE_TAG);
            preparedStatement.setString(1, object.getTagName());
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
            preparedStatement = connection.prepareStatement(DELETE_TAG);
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
    public Set<Tag> getAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Tag> tags = new HashSet<>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tag tag = new Tag();
                parseResultSet(resultSet, tag);
                tags.add(tag);
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
        return tags;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws DaoException
     */
    @Override
    public Tag getById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Tag tag = new Tag();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_TAG_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                parseResultSet(resultSet, tag);
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
        return tag;
    }


    /**
     * Get tag by name
     *
     * @param tag tag object
     * @return tag object
     * @throws DaoException if cannot get tag with corresponding name
     */
    @Override
    public Tag getByName(Tag tag) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_TAG_BY_NAME);
            preparedStatement.setString(1, tag.getTagName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                parseResultSet(resultSet, tag);
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
        return tag;
    }

    /**
     * Get set of tags with corresponding news id
     *
     * @param newsId id of news
     * @return set of tags with corresponding news id
     * @throws DaoException
     */
    @Override
    public Set<Tag> getByNewsId(Long newsId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Tag> tags = new HashSet<Tag>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_TAG_BY_NEWS_ID);
            preparedStatement.setLong(1, newsId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tag tag = new Tag();
                parseResultSet(resultSet, tag);
                tags.add(tag);
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
        return tags;
    }

    private void parseResultSet(ResultSet resultSet, Tag tag) throws SQLException {
        tag.setTagId(resultSet.getLong(TAG_ID));
        tag.setTagName(resultSet.getString(TAG_NAME));
    }
}
