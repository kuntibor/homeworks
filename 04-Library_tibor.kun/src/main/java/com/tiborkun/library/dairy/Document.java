package com.tiborkun.library.dairy;

import java.util.Objects;

public class Document {

    private final String title;
    private final int year;
    private final String genre;
    private final String publisher;

    public Document(String title, int year, String genre, String publisher) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + this.year;
        hash = 37 * hash + Objects.hashCode(this.genre);
        hash = 37 * hash + Objects.hashCode(this.publisher);
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
        final Document other = (Document) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        return true;
    }
}
