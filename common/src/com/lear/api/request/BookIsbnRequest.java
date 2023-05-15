package com.lear.api.request;

/**
 * 图书isbn请求参数
 * @author 天狗
 */
public class BookIsbnRequest {

    private String isbn;

    public BookIsbnRequest() {
    }

    public BookIsbnRequest(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "BookIsbnRequest{" +
                "isbn='" + isbn + '\'' +
                '}';
    }
}
