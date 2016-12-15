package com.tiborkun.library.dairy;

public class MainClass {
    
    private MainClass(){
    }

    public static void main(String[] args) {
        Document b1 = new Book("Tolkien", "The Two Towers", 1954, "fantasy", "George Allen & Unwin");
        Document b2 = new Book("C.S. Lewis", "The Last Battle", 1956, "fantasy", "HarperCollins");
        Document b3 = new Book("Daniel Defoe", "Robinson Crusoe", 1719, "novel", "W. Taylor");
        Document m1 = new Magazine("Forbes", 2016, 12, "business", "Forbes, Inc.");
        Document m2 = new Magazine("Kiskegyed", 2015, 10, "gossip", "Axel Springer");

        Library.INSTANCE.addDocument(b1);
        Library.INSTANCE.addDocument(b2);
        Library.INSTANCE.addDocument(b3);
        Library.INSTANCE.addDocument(m1);
        Library.INSTANCE.addDocument(m2);

        LibraryDairy.INSTANCE.borrow(m2, "Ákos");
        LibraryDairy.INSTANCE.borrow(b1, "Laci");
        LibraryDairy.INSTANCE.bringBack(m2);
        LibraryDairy.INSTANCE.bringBack(b1);
    }
}
