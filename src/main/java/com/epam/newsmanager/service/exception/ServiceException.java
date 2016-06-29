package com.epam.newsmanager.service.exception;

import com.epam.newsmanager.exception.ProjectException;

/**
 * Exception for service layer classes.
 */
public class ServiceException extends ProjectException {
    private static final long serialVersionUID = 1;

    public ServiceException(Exception e)
    {
        super(e);
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Exception e){
        super(message, e);
    }
}