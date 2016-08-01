package com.epam.newsmanager.controller.command;

import com.epam.newsmanager.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Command pattern realization for all commands.
 */
public final class CommandHelper {
    private final static CommandHelper instance = new CommandHelper();

    private final Map<CommandName, Command> commandMap = new HashMap<>();

    public CommandHelper(){
        commandMap.put(CommandName.ADD_COMMENT, new AddCommentCommand());
        commandMap.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commandMap.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
    }

    public static CommandHelper getInstance() {
        return instance;
    }
}
