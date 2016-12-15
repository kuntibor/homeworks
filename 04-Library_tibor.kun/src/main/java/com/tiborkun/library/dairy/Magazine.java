package com.tiborkun.library.dairy;

public class Magazine extends Document {

    private final int issueNumber;

    public Magazine(String title, int year, int issueNumber, String genre, String publisher) {
        super(title, year, genre, publisher);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.issueNumber;
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
        final Magazine other = (Magazine) obj;
        if (this.issueNumber != other.issueNumber) {
            return false;
        }
        return true;
    }
}
