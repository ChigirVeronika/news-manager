package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.impl.AuthorDaoImpl;
import com.epam.newsmanager.dao.impl.NewsDaoImpl;
import com.epam.newsmanager.dao.impl.TagDaoImpl;
import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.entity.News;
import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.entity.dto.SearchNewsDto;
import com.epam.newsmanager.service.NewsService;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Set;

public class NewsServiceImpl implements NewsService {

    /**
     * Dao for dao objects
     */
    private static NewsDaoImpl newsDao;
    private static TagDaoImpl tagDao;
    private static AuthorDaoImpl authorDao;

    public NewsServiceImpl(NewsDaoImpl newsDao, TagDaoImpl tagDao, AuthorDaoImpl authorDao) {
        this.newsDao = newsDao;
        this.tagDao = tagDao;
        this.authorDao = authorDao;
    }

    /**
     * Create new record from object
     *
     * @param news    to insert
     * @param authors set of authors to insert
     * @param tags    set of tags to insert
     * @throws ServiceException
     */
    @Override
    public void insertNews(News news, Set<Author> authors, Set<Tag> tags) throws ServiceException {
        try {
            for (Author author : authors) {
                if (authorDao.getByName(author) == null) {
                    author.setAuthorId(authorDao.insert(author));
                }
            }
            for (Tag tag : tags) {
                if (tagDao.getByName(tag) == null) {
                    tag.setTagId(tagDao.insert(tag));
                }
            }
            news.setNewsId(newsDao.insert(news));
            newsDao.insertNewsAuthors(news.getNewsId(), authors);
            newsDao.insertNewsTags(news.getNewsId(), tags);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update object
     *
     * @param news to update
     * @throws ServiceException
     */
    @Override
    public void updateNews(News news) throws ServiceException {
        try {
            newsDao.update(news);
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
    public void deleteNews(Long id) throws ServiceException {
        try {
            newsDao.delete(id);
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
    public Set<News> getAllNews() throws ServiceException {
        Set<News> newsSet;
        try {
            newsSet = newsDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newsSet;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    @Override
    public News getOneNews(Long id) throws ServiceException {
        News news;
        try{
            news = newsDao.getById(id);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
        return news;
    }

    /**
     * Add tags to news
     *
     * @param newsId news unique identifier
     * @param tags   set of tags to add
     * @throws ServiceException
     */
    @Override
    public void addTagsToNews(Long newsId, Set<Tag> tags) throws ServiceException {
        try {
            for (Tag tag : tags) {
                if (tagDao.getByName(tag) == null) {
                    tag.setTagId(tagDao.insert(tag));
                }
            }
            newsDao.insertNewsTags(newsId, tags);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Add authors to news
     *
     * @param newsId  news unique identifier
     * @param authors set of authors to add
     * @throws ServiceException
     */
    @Override
    public void addAuthorsToNews(Long newsId, Set<Author> authors) throws ServiceException {
        try {
            for (Author author : authors) {
                if (authorDao.getByName(author) == null) {
                    author.setAuthorId(authorDao.insert(author));
                }
            }
            newsDao.insertNewsAuthors(newsId, authors);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Get sorted news by comments
     *
     * @return arraylist of news
     * @throws ServiceException
     */
    @Override
    public ArrayList<News> getSortedNews() throws ServiceException {
        ArrayList<News> newsSet;
        try {
            newsSet = newsDao.getMostCommented();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newsSet;
    }

    /**
     * Count all news
     *
     * @return number of news
     * @throws ServiceException
     */
    @Override
    public int countNews() throws ServiceException {
        try {
            return newsDao.getNumberOfNews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Search news according to some criteria
     *
     * @param searchNewsDto
     * @return set of news
     * @throws ServiceException
     */
    @Override
    public Set<News> searchNews(SearchNewsDto searchNewsDto) throws ServiceException {
        Set<News> newsSet;
        try {
            newsSet = newsDao.searchNews(searchNewsDto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return newsSet;
    }
}
