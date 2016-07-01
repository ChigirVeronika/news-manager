package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.impl.AuthorDaoImpl;
import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.service.AuthorService;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with author objects.
 */
public class AuthorServiceImpl implements AuthorService{

    /**
     * Dao for author dao objects
     */
    private AuthorDaoImpl authorDao;

    public AuthorServiceImpl(AuthorDaoImpl authorDao){this.authorDao = authorDao; }

    /**
     * Create new record from object
     *
     * @param author create new record from this object
     * @return created object
     * @throws ServiceException
     */
    @Override
    public void insertAuthor(Author author) throws ServiceException {
        try{
            authorDao.insert(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update object in database
     *
     * @param author to update
     * @throws ServiceException
     */
    @Override
    public void updateAuthor(Author author) throws ServiceException {
        try{
            authorDao.update(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Delete record from database
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    @Override
    public void deleteAuthor(Long id) throws ServiceException {
        try{
            authorDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Return list of all items from database
     *
     * @return list of all items
     * @throws ServiceException
     */
    @Override
    public Set<Author> getAllAuthors() throws ServiceException {
        Set<Author> authors;
        try{
            authors = authorDao.getAll();
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
        return authors;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    @Override
    public Author getAuthorById(Long id) throws ServiceException {
        Author author;
        try{
            author = authorDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return author;
    }

    /**
     * Get set of authors by news id
     *
     * @param newsId news unique identifier
     * @return set of authors with corresponding news identifier
     * @throws ServiceException
     */
    @Override
    public Set<Author> getAuthorByNewsId(Long newsId) throws ServiceException {
        Set<Author> authors;
        try{
            authors = authorDao.getByNewsId(newsId);
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
        return authors;
    }

    /**
     * Get author by his name
     *
     * @param author object
     * @return author with corresponding name
     * @throws ServiceException
     */
    @Override
    public Author getAuthorByName(Author author) throws ServiceException {
        try{
            author = authorDao.getByName(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return author;
    }
}
