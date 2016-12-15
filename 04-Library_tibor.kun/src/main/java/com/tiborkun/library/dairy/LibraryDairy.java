package com.tiborkun.library.dairy;

import java.util.HashMap;
import java.util.Map;

public enum LibraryDairy {
    INSTANCE;

    private static final Map<Document, String> dairy = new HashMap<>();
    private static final LibraryLogger log = new LibraryLogger();

    public void borrow(Document document, String name) {
        if (Library.INSTANCE.hasDocument(document) && !dairy.containsKey(document)) {
            dairy.put(document, name);
            log.borrowLog(document, name);
        } else {
            throw new NotBorrowableDocumentExpection();
        }
    }

    public void bringBack(Document document) {
        if (!dairy.isEmpty() && dairy.containsKey(document)) {
            log.bringBackLog(document, dairy.get(document));
            dairy.remove(document);
        } else {
            throw new NotBorrowedDocumentExpection();
        }
    }

    public Map<Document, String> getDairy() {
        return dairy;
    }
}
