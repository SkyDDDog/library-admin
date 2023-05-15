package com.lear.entity.database;

import java.util.Date;

/**
 * 借书记录数据库实体类
 * @author 天狗
 */
public class Record {

    private String recordId;
    private String isbn;
    private String readerId;
    private Date borrowingDate;
    private Date returnDate;

    public Record() {
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId='" + recordId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", readerId='" + readerId + '\'' +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
