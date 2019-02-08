package com.gayko.bookstore.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandEnum {
    REGISTER,
    LOGIN,
    LOGOUT,
    USERS,
    USERORDERS,
    STORE,
    ITEMS,
    REMOVEITEM,
    ORDEREDITEMS,
    UPLOADITEMS,
    ADDORDER,
    REMOVEORDER,
    READFEEDBACK,
    WRITEFEEDBACK,
    CHANGESTATUS,
    CHANGESTATUSVALUE,
    REMOVEUSER,
    NEWS,
    READNEWS,
    ADDCOMMENT,
    ADDNEWS,
    CHANGEROLE,
    CHANGEROLEVALUE;

    public static CommandEnum getCommand(String command) {
        final Logger logger = LogManager.getLogger(CommandEnum.class.getName());
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.info("Command does not found!");
        }
        return null;
    }
}
