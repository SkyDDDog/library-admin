package com.lear.mapper;

import com.lear.entity.database.Book;
import com.lear.util.JdbcUtil;

import java.util.List;

/**
 * 书籍数据访问层
 * @author 天狗
 */
public class BookMapper {

    public static boolean insert(Book book) {
        String sql = "insert into book (isbn, title, author, publisher, edition_number, publication_date, type) values (?, ?, ?, ?, ?, ?, ?)";
        return JdbcUtil.executeDML(sql,
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getEditionNumber(),
                book.getPublicationDate(),
                book.getType()
        );
    }

    public static boolean update(Book book) {
        String sql = "update book set title = ?, author = ?, publisher = ?, edition_number = ?, publication_date = ?, type = ? where isbn = ?";
        return JdbcUtil.executeDML(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getEditionNumber(),
                book.getPublicationDate(),
                book.getType(),
                book.getIsbn()
        );
    }

    public static boolean delete(String isbn) {
        String sql = "delete from book where isbn = ?";
        return JdbcUtil.executeDML(sql, isbn);
    }

    public static Book selectOne(String isbn) {
        String sql = "select * from book where isbn = ?";
        List<Book> list = JdbcUtil.executeDQL(Book.class, sql, isbn);
        if (list.size()==0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public static List<Book> selectAll() {
        String sql = "select * from book";
        return JdbcUtil.executeDQL(Book.class, sql);
    }

    public static List<Book> selectCustom(String sql, Object... params) {
        return JdbcUtil.executeDQL(Book.class, sql, params);
    }

}
