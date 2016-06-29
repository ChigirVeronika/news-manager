package com.epam.newsmanager.dao.exception;

import com.epam.newsmanager.exception.ProjectException;

/**
 * Exception class for dao layer classes.
 */
public class DaoException extends ProjectException {

    private static final long serialVersionUID = 1;

    public DaoException(Exception e)
    {
        super(e);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}