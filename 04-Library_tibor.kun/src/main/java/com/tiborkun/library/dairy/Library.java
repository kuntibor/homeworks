package com.tiborkun.library.dairy;

import java.util.ArrayList;
import java.util.List;

public enum Library {
    INSTANCE;

    private static final List<Document> list = new ArrayList<>();

    public void addDocument(Document document) {
        list.add(document);
    }

    public void removeDocument(Document document) {
        list.remove(document);
    }

    public boolean hasDocument(Document document) {
        return list.contains(document);
    }
}
