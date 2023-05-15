package com.lear.api.response;

/**
 * 借阅记录响应类
 * @author 天狗
 */
public class BorrowedBook {

    private String isbn;

    private String title;

    private String borrowDate;

    private String returnDate;


    public String getIsbn() {
        return isbn;
    }

    public BorrowedBook setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BorrowedBook setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public BorrowedBook setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
        return this;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public BorrowedBook setReturnDate(String returnDate) {
        this.returnDate = returnDate;
        return this;
    }
}
