package com.epam.newsmanager.service;

import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.service.exception.ServiceException;

import java.util.Set;

/**
 * Perform service operations with tag object.
 */
public interface TagService {
    /**
     * Create new record from object
     *
     * @param tag create new record from this object
     * @return created object
     * @throws ServiceException
     */
    void insertTag(Tag tag) throws ServiceException;//returns new copy

    /**
     * Update object
     *
     * @param tag to update
     * @throws ServiceException
     */
    void updateTag(Tag tag) throws ServiceException;

    /**
     * Delete record
     *
     * @param id unique identified of object to delete
     * @throws ServiceException
     */
    void deleteTag(Long id) throws ServiceException;

    /**
     * Return list of all items
     *
     * @return list of all items
     * @throws ServiceException
     */
    Set<Tag> getAllTags() throws ServiceException;

    /**
     * Return object with primary key 'key' or null
     *
     * @param id primary key of object
     * @return object with primary key 'key' or null
     * @throws ServiceException
     */
    Tag getTagById(Long id) throws ServiceException;

    /**
     * Get tag by name
     *
     * @param tag tag object
     * @return tag object
     * @throws ServiceException if cannot get tag with corresponding name
     */
    Tag getTagByName(Tag tag) throws ServiceException;

    /**
     * Get set of tags with corresponding news id
     *
     * @param newsId id of news
     * @return set of tags with corresponding news id
     * @throws ServiceException
     */
    Set<Tag> getTagByNewsId(Long newsId) throws ServiceException;
}
