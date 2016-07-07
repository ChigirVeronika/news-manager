package com.epam.newsmanager.dao;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.entity.News;
import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.entity.dto.SearchNewsDto;

import java.util.ArrayList;
import java.util.Set;

/**
 * This interface is designed to work with persistence object News
 */
public interface NewsDao extends GenericDao<News> {

    /**
     * Search news according to params in SearchNewsDto object
     *
     * @return
     * @throws DaoException
     */
    Set<News> searchNews(SearchNewsDto searchNews) throws DaoException;

    /**
     * Count all news in database news table
     *
     * @return number of news
     * @throws DaoException
     */
    int getNumberOfNews() throws DaoException;

    /**
     * Insert set of authors according with news
     *
     * @param newsId id of news
     * @param authors set of authors
     * @throws DaoException if cannot insert
     */
    void insertNewsAuthors(Long newsId, Set<Author> authors) throws DaoException;

    /**
     * Insert set of tags according with news
     *
     * @param newsId id of news
     * @param tags set of tags
     * @throws DaoException if cannot insert
     */
    void insertNewsTags(Long newsId, Set<Tag> tags) throws DaoException;

    /**
     * Get most commented news
     *
     * @return arraylist of news
     * @throws DaoException of cannot get news
     */
    ArrayList<News> getMostCommented() throws DaoException;
}
