package com.tiborkun.library.dairy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryLogger {

    private static final Logger LOGGER = Logger.getLogger(LibraryLogger.class.getName());

    public void borrowLog(Document document, String name) {
        LOGGER.log(Level.INFO, "{0} was borrowed by {1}. {2}", new Object[]{document.getTitle(), name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)});
    }

    public void bringBackLog(Document document, String name) {
        LOGGER.log(Level.INFO, "{0} was brought back by {1}. {2}", new Object[]{document.getTitle(), name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)});
    }
}
