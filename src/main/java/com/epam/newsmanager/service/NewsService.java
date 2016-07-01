package com.epam.newsmanager.service;

import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.entity.News;
import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.entity.dto.SearchNewsDto;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Set;

/**
 * Perform service operations with news object.
 */
public interface NewsService {

    /**
     * Create new record from object
     *
     * @param news to insert
     * @param authors set of authors to insert
     * @param tags set of tags to insert
     * @throws ServiceException
     */
    void insertNews(News news, Set<Author> authors, Set<Tag> tags) throws ServiceException;//returns new copy

    /**
     * Update object
     *
     * @param news to update
     * @throws ServiceException
     */
    void updateNews(News news) throws ServiceException;

    /**
     * Delete record
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    void deleteNews(Long id) throws ServiceException;

    /**
     * Return list of all items
     *
     * @return list of all items
     * @throws ServiceException
     */
    Set<News> getAllNews() throws ServiceException;

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    News getOneNews(Long id) throws ServiceException;

    /**
     * Add tags to news
     *
     * @param newsId news unique identifier
     * @param tags   set of tags to add
     * @throws ServiceException
     */
    void addTagsToNews(Long newsId, Set<Tag> tags) throws ServiceException;

    /**
     * Add authors to news
     *
     * @param newsId  news unique identifier
     * @param authors set of authors to add
     * @throws ServiceException
     */
    void addAuthorsToNews(Long newsId, Set<Author> authors) throws ServiceException;

    /**
     * Get sorted news by comments
     *
     * @return arraylist of news
     * @throws ServiceException
     */
    ArrayList<News> getSortedNews() throws ServiceException;

    /**
     * Count all news
     *
     * @return number of news
     * @throws ServiceException
     */
    int countNews() throws ServiceException;

    /**
     * Search news according to some criteria
     *
     * @param searchNewsDto
     * @return set of news
     * @throws ServiceException
     */
    Set<News> searchNews(SearchNewsDto searchNewsDto) throws ServiceException;


}
