package com.tiborkun.library.dairy;

import java.util.Objects;

public class Book extends Document {

    private final String author;

    public Book(String author, String title, int year, String genre, String publisher) {
        super(title, year, genre, publisher);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }
}
