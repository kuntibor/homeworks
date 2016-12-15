package com.tiborkun.library.dairy;

public class NotBorrowedDocumentExpection extends RuntimeException {

    public NotBorrowedDocumentExpection() {
        //
    }

    public NotBorrowedDocumentExpection(String message) {
        super(message);
    }

    public NotBorrowedDocumentExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public NotBorrowedDocumentExpection(Throwable cause) {
        super(cause);
    }

    public NotBorrowedDocumentExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
