package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.NewsDao;
import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.entity.News;
import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.entity.dto.SearchNewsDto;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dao implementation for Oracle database and News entity.
 */
public class NewsDaoImpl implements NewsDao {

    private static final String GET_ALL = "SELECT * FROM NEWS";
    private static final String GET_NUMBER_OF_NEWS = "SELECT COUNT(NEWS_ID) FROM NEWS";
    private static final String INSERT_NEWS = "INSERT INTO NEWS (TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) " +
            "VALUES (?,?,?,?,?)";
    private static final String INSERT_NEWS_AUTHORS = "INSERT INTO NEWS_AUTHORS (NEWS_ID, AUTHOR_ID) VALUES (?, ?)";
    private static final String INSERT_NEWS_TAGS = "INSERT INTO NEWS_TAGS (NEWS_ID, TAG_ID) VALUES (?, ?)";
    private static final String UPDATE_NEWS = "UPDATE NEWS SET TITLE = ?, SHORT_TEXT = ?, FULL_TEXT = ?, CREATION_DATE = ?, MODIFICATION_DATE = ? WHERE NEWS_ID = ?";
    private static final String DELETE_NEWS = "DELETE FROM NEWS WHERE NEWS_ID = ?";
    private static final String GET_NEWS_BY_ID = "SELECT * FROM NEWS WHERE NEWS_ID = ?";
    private static final String GET_MOST_COMMENTED = "select news_id, count(*) c from admin.comments group by NEWS_ID order by c desc";
    private StringBuilder searchQuery = new StringBuilder("SELECT n.NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE," +
            "  MODIFICATION_DATE, na.AUTHOR_ID, nt.tag_id " +
            "  FROM NEWS n inner join NEWS_AUTHOR na on na.NEWS_ID = n.NEWS_ID " +
            "inner join news_tag nt on n.news_id = nt.news_id ");

    private static final String NEWS_ID = "NEWS_ID";
    private static final String TITLE = "TITLE";
    private static final String SHORT_TEXT = "SHORT_TEXT";
    private static final String FULL_TEXT = "FULL_TEXT";
    private static final String MODIFICATION_DATE = "MODIFICATION_DATE";
    private static final String CREATION_DATE = "CREATION_DATE";

    private static final Logger LOGGER = Logger.getLogger(NewsDaoImpl.class);

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
    public Long insert(News object) throws DaoException {
        Connection connection = null;
        Long id = new Long(0);
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_NEWS);
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setString(2, object.getShortText());
            preparedStatement.setString(3, object.getFullText());
            preparedStatement.setTimestamp(4, object.getCreationDate());
            preparedStatement.setDate(5, object.getModificationDate());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
                object.setNewsId(id);
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
    public void update(News object) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(UPDATE_NEWS);
            preparedStatement.setString(1, object.getTitle());
            preparedStatement.setString(2, object.getShortText());
            preparedStatement.setString(3, object.getFullText());
            preparedStatement.setTimestamp(4, object.getCreationDate());
            preparedStatement.setDate(5, object.getModificationDate());
            preparedStatement.setLong(6, object.getNewsId());
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
            preparedStatement = connection.prepareStatement(DELETE_NEWS);
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
    public Set<News> getAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<News> newsSet = new HashSet<News>();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                parseResultSet(resultSet, news);
                newsSet.add(news);
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
        return newsSet;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws DaoException
     */
    @Override
    public News getById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        News news = new News();
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_NEWS_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                parseResultSet(resultSet, news);
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
        return news;
    }

    /**
     * Search news according to params in SearchNewsDto object
     *
     * @param searchNews
     * @return
     * @throws DaoException
     */
    @Override
    public Set<News> searchNews(SearchNewsDto searchNews) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        Set<News> newsSet = new HashSet<News>();
        configQuery(searchNews);
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(searchQuery.toString());
            while (resultSet.next()) {
                News news = new News();
                parseResultSet(resultSet, news);
                newsSet.add(news);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return newsSet;
    }

    private void configQuery(SearchNewsDto searchNews){
        if (searchNews.getAuthor() == null) {
            searchQuery.append("where tag_id in (");
            for (Tag tag : searchNews.getTags()) {
                searchQuery.append(tag.getTagId() + ", ");
            }
            searchQuery.deleteCharAt(searchQuery.length() - 2);
            searchQuery.append(")");
        } else {
            if (searchNews.getTags() == null || searchNews.getTags().isEmpty()) {
                searchQuery.append("where author_id = " + searchNews.getAuthor().getAuthorId());
            } else {
                searchQuery.append("where tag_id in (");
                for (Tag tag : searchNews.getTags()) {
                    searchQuery.append(tag.getTagId() + ", ");
                }
                searchQuery.deleteCharAt(searchQuery.length() - 2);
                searchQuery.append(") and author_id = " + searchNews.getAuthor().getAuthorId());
            }
        }
    }

    /**
     * Count all news in database news table
     *
     * @return number of news
     * @throws DaoException
     */
    @Override
    public int getNumberOfNews() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_NUMBER_OF_NEWS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
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
        return count;
    }

    /**
     * Insert set of authors according with news
     *
     * @param newsId  id of news
     * @param authors set of authors
     * @throws DaoException if cannot insert
     */
    @Override
    public void insertNewsAuthors(Long newsId, Set<Author> authors) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_NEWS_AUTHORS);
            for (Author author : authors) {
                preparedStatement.setLong(1, newsId);
                preparedStatement.setLong(2, author.getAuthorId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
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
     * Insert set of tags according with news
     *
     * @param newsId id of news
     * @param tags   set of tags
     * @throws DaoException if cannot insert
     */
    @Override
    public void insertNewsTags(Long newsId, Set<Tag> tags) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(INSERT_NEWS_TAGS);
            for (Tag tag : tags) {
                preparedStatement.setLong(1, newsId);
                preparedStatement.setLong(2, tag.getTagId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
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
     * Get most commented news
     *
     * @return arraylist of news
     * @throws DaoException of cannot get news
     */
    @Override
    public ArrayList<News> getMostCommented() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<News> newsList = null;
        try {
            connection = dataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(GET_MOST_COMMENTED);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Long> newsId = new ArrayList<>();
            while (resultSet.next()) {
                newsId.add(resultSet.getLong(NEWS_ID));
            }
            newsList = getListById(newsId);
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
        return newsList;
    }

    private ArrayList<News> getListById(List<Long> newsId) throws DaoException {
        ArrayList<News> newsList = new ArrayList<News>();
        for (Long id : newsId) {
            newsList.add(getById(id));
        }
        return newsList;
    }


    private void parseResultSet(ResultSet resultSet, News news) throws SQLException {
        news.setNewsId(resultSet.getLong(NEWS_ID));
        news.setTitle(resultSet.getString(TITLE));
        news.setShortText(resultSet.getString(SHORT_TEXT));
        news.setFullText(resultSet.getString(FULL_TEXT));
        news.setCreationDate(resultSet.getTimestamp(CREATION_DATE));
        news.setModificationDate(resultSet.getDate(MODIFICATION_DATE));
    }
}
