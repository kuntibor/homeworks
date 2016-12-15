package com.tiborkun.library.dairy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {

    private Document b1;
    private Document b2;
    private Document b3;
    private Document m1;
    private Document m2;

    @Before
    public void setUp() {
        b1 = new Book("Tolkien", "The Two Towers", 1954, "fantasy", "George Allen & Unwin");
        b2 = new Book("C.S. Lewis", "The Last Battle", 1956, "fantasy", "HarperCollins");
        b3 = new Book("Daniel Defoe", "Robinson Crusoe", 1719, "novel", "W. Taylor");
        m1 = new Magazine("Forbes", 2016, 12, "business", "Forbes, Inc.");
        m2 = new Magazine("Kiskegyed", 2015, 10, "gossip", "Axel Springer");

        Library.INSTANCE.addDocument(b1);
        Library.INSTANCE.addDocument(b2);
        Library.INSTANCE.addDocument(m1);
        Library.INSTANCE.addDocument(m2);
    }

    @Test
    public void borrowTest() {
        LibraryDairy.INSTANCE.borrow(b1, "Ákos");
        assertTrue(LibraryDairy.INSTANCE.getDairy().containsKey(b1));
    }

    @Test(expected = NotBorrowableDocumentExpection.class)
    public void borrowExpTest1() {
        LibraryDairy.INSTANCE.borrow(b2, "Tamás");
        LibraryDairy.INSTANCE.borrow(b2, "Juli");
    }

    @Test(expected = NotBorrowableDocumentExpection.class)
    public void borrowExpTest2() {
        LibraryDairy.INSTANCE.borrow(b3, "Laci");
    }

    @Test
    public void bringBackTest() {
        LibraryDairy.INSTANCE.borrow(b2, "Laci");
        LibraryDairy.INSTANCE.bringBack(b2);
        assertFalse(LibraryDairy.INSTANCE.getDairy().containsKey(b2));
    }

    @Test(expected = NotBorrowedDocumentExpection.class)
    public void bringBackExpTest() {
        LibraryDairy.INSTANCE.borrow(m1, "Ákos");
        LibraryDairy.INSTANCE.bringBack(m2);
    }

}
