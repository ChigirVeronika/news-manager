package com.epam.newsmanager.dao;

import com.epam.newsmanager.dao.exception.DaoException;
import com.epam.newsmanager.entity.Tag;

import java.util.Set;

/**
 * This interface is designed to work with persistence object Tag
 */
public interface TagDao extends GenericDao<Tag> {

    /**
     * Get tag by name
     *
     * @param tag tag object
     * @return tag object
     * @throws DaoException if cannot get tag with corresponding name
     */
    Tag getByName(Tag tag) throws DaoException;

    /**
     * Get set of tags with corresponding news id
     *
     * @param newsId id of news
     * @return set of tags with corresponding news id
     * @throws DaoException
     */
    Set<Tag> getByNewsId(Long newsId) throws DaoException;
}
