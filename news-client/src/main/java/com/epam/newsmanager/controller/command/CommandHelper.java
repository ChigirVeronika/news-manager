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

    public CommandHelper() {
        commandMap.put(CommandName.ADD_COMMENT, new AddCommentCommand());
        commandMap.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commandMap.put(CommandName.SHOW_ONE_NEWS, new ShowOneNewsCommand());
        commandMap.put(CommandName.SHOW_NEWS_LIST, new ShowNewsListCommand());
        commandMap.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
    }

    public static CommandHelper getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        Command command;
        if (name != null) {
            command = commandMap.get(name);
        }
        else {
           command = commandMap.get(CommandName.UNKNOWN_COMMAND);
        }
        return command;
    }
}
