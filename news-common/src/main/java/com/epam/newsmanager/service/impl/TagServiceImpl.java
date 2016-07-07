package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.dao.impl.TagDaoImpl;
import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.service.TagService;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with user objects.
 */
public class TagServiceImpl implements TagService{

    /**
     * Dao for tag dao objects
     */
    private static TagDaoImpl tagDao;

    public TagServiceImpl(TagDaoImpl tagDao) {this.tagDao = tagDao; }

    /**
     * Create new record from object
     *
     * @param tag create new record from this object
     * @return created object
     * @throws ServiceException
     */
    @Override
    public void insertTag(Tag tag) throws ServiceException {
        try{
            tagDao.insert(tag);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update object
     *
     * @param tag to update
     * @throws ServiceException
     */
    @Override
    public void updateTag(Tag tag) throws ServiceException {
        try{
            tagDao.update(tag);
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
    public void deleteTag(Long id) throws ServiceException {
        try{
            tagDao.delete(id);
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
    public Set<Tag> getAllTags() throws ServiceException {
        Set<Tag> tags;
        try{
            tags = tagDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tags;
    }

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    @Override
    public Tag getTagById(Long id) throws ServiceException {
        Tag tag;
        try{
            tag = tagDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tag;
    }

    /**
     * Get tag by name
     *
     * @param tag tag object
     * @return tag object
     * @throws ServiceException if cannot get tag with corresponding name
     */
    @Override
    public Tag getTagByName(Tag tag) throws ServiceException {
        try{
            tag = tagDao.getByName(tag);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tag;
    }

    /**
     * Get set of tags with corresponding news id
     *
     * @param newsId id of news
     * @return set of tags with corresponding news id
     * @throws ServiceException
     */
    @Override
    public Set<Tag> getTagByNewsId(Long newsId) throws ServiceException {
        Set<Tag> tags;
        try{
            tags = tagDao.getByNewsId(newsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tags;
    }
}
