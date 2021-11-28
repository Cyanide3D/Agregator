package ru.news.Agregator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    public static void info(Object obj, String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(obj.getClass());
        logger.info(message, args);
    }

    public static void error(Object obj, String message, Throwable e) {
        Logger logger = LoggerFactory.getLogger(obj.getClass());
        logger.error(message, e);
    }

    public static void warning(Object obj, String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(obj.getClass());
        logger.warn(message, args);
    }

    public static void warning(Object obj, String message, Throwable e) {
        Logger logger = LoggerFactory.getLogger(obj.getClass());
        logger.warn(message, e);
    }

    public static void error(Object obj, String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(obj.getClass());
        logger.error(message, args);
    }

}
