package com.lear.entity.database.example;

import java.util.Date;

/**
 * 图书数据库排序筛选类
 * @author 天狗
 */
public class BookExample {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer editionNumber;
    private Date publicationDate;
    private String type;

    private String orderByClause;

    public String toSql() {
        StringBuilder builder = new StringBuilder("select * from book where 1=1");
        if (isbn != null) {
            builder.append(" and isbn = ").append(isbn);
        }
        if (title != null) {
            builder.append(" and title = ").append(title);
        }
        if (author != null) {
            builder.append(" and author = ").append(author);
        }
        if (publisher != null) {
            builder.append(" and publisher = ").append(publisher);
        }
        if (editionNumber != null) {
            builder.append(" and edition_number = ").append(editionNumber);
        }
        if (publicationDate != null) {
            builder.append(" and publication_date = ").append(publicationDate);
        }
        if (type != null) {
            builder.append(" and type = ").append(type);
        }
        if (orderByClause != null) {
            builder.append(" order by ").append(orderByClause);
        }
        return builder.toString();
    }

    public BookExample() {
    }

    public String getIsbn() {
        return isbn;
    }

    public BookExample setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookExample setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookExample setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookExample setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Integer getEditionNumber() {
        return editionNumber;
    }

    public BookExample setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
        return this;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public BookExample setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getType() {
        return type;
    }

    public BookExample setType(String type) {
        this.type = type;
        return this;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public BookExample setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    @Override
    public String toString() {
        return "BookExample{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", editionNumber=" + editionNumber +
                ", publicationDate=" + publicationDate +
                ", type='" + type + '\'' +
                ", orderByClause='" + orderByClause + '\'' +
                '}';
    }
}
