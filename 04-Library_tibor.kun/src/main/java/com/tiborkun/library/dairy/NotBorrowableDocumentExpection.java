package com.tiborkun.library.dairy;

public class NotBorrowableDocumentExpection extends RuntimeException {

    public NotBorrowableDocumentExpection() {
        //
    }

    public NotBorrowableDocumentExpection(String message) {
        super(message);
    }

    public NotBorrowableDocumentExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public NotBorrowableDocumentExpection(Throwable cause) {
        super(cause);
    }

    public NotBorrowableDocumentExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
