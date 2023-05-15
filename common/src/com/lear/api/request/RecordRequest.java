package com.lear.api.request;

/**
 * 记录请求参数
 * @author 天狗
 */
public class RecordRequest {

    private String isbn;
    private String readerId;
    private String returnDate;

    public RecordRequest() {
    }


    public RecordRequest(String isbn, String readerId) {
        this.isbn = isbn;
        this.readerId = readerId;
    }

    public RecordRequest(String isbn, String readerId, String returnDate) {
        this.isbn = isbn;
        this.readerId = readerId;
        this.returnDate = returnDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "RecordRequest{" +
                "isbn='" + isbn + '\'' +
                ", readerId='" + readerId + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }





}
