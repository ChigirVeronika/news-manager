package com.epam.newsmanager.controller.exception;

/**
 * Exception for controller commands.
 */
public class CommandException extends Exception{
    private static final long serialVersionUID = 1;

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message, Exception e){
        super(message, e);
    }
}
