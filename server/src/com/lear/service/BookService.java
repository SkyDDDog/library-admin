package com.lear.service;

import com.lear.entity.database.Book;
import com.lear.entity.database.Reader;
import com.lear.entity.database.example.BookExample;
import com.lear.entity.database.example.ReaderExample;
import com.lear.mapper.BookMapper;
import com.lear.mapper.ReaderMapper;
import com.lear.util.SnowFlakeUtil;
import com.lear.util.StringUtils;

import java.io.*;
import java.util.List;

/**
 * 图书服务类
 * @author 天狗
 */
public class BookService {

    public static int insertList(List<Book> bookList) {
        int count = 0;
        for (Book book : bookList) {
            if (insert(book)) {
                count++;
            }
        }
        return count;
    }

    public static Book selectByIsbn(String isbn) {
        return BookMapper.selectOne(isbn);
    }

    public static List<Book> selectAll() {
        return BookMapper.selectAll();
    }

    public static List<Book> selectByExample(BookExample example) {
        return BookMapper.selectCustom(example.toSql());
    }

    public static boolean insert(Book book) {
        if (StringUtils.isEmpty(book.getIsbn())) {
            book.setIsbn(SnowFlakeUtil.getId());
        }
        return BookMapper.insert(book);
    }

    public static boolean update(Book book) {
        return BookMapper.update(book);
    }



}
