package com.lear.service;

import com.lear.api.response.BorrowedBook;
import com.lear.entity.database.Book;
import com.lear.entity.database.Record;
import com.lear.mapper.RecordMapper;
import com.lear.util.SnowFlakeUtil;
import com.lear.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 借书记录服务类
 * @author 天狗
 */
public class RecordService {


    public static int insertList(List<Record> recordList) {
        int count = 0;
        for (Record record : recordList) {
            if (insert(record)) {
                count++;
            }
        }
        return count;
    }

    public static List<Record> selectAll() {
        return RecordMapper.selectAll();
    }

    public static boolean insert(Record record) {
        if (StringUtils.isEmpty(record.getRecordId())) {
            record.setRecordId(SnowFlakeUtil.getId());
        }
        return RecordMapper.insert(record);
    }

    public static boolean returnBook(Record record) {
        if (StringUtils.isEmpty(record.getIsbn())) {
            return false;
        }
        if (StringUtils.isEmpty(record.getReaderId())) {
            return false;
        }
        if (!isBorrowed(record.getIsbn())) {
            return false;
        }
        String sql = "select * from record where isbn = ? and reader_id = ?";
        List<Record> recordList = RecordMapper.selectCustom(sql, record.getIsbn(), record.getReaderId());
        if (recordList.size()<=0) {
            return false;
        } else {
            record = recordList.get(0);
            record.setReturnDate(new Date());
            System.out.println(record);
            return RecordMapper.update(record);
        }

    }

    public static boolean isBorrowed(String isbn) {
        String sql = "select * from record where isbn = ? and return_date > ?";
        List<Record> recordList = RecordMapper.selectCustom(sql, isbn, today());
        return recordList.size() > 0;
    }

    public static List<BorrowedBook> borrowedBookList(String readerId) {
        String sql = "select * from record where reader_id = ?";
        List<Record> recordList = RecordMapper.selectCustom(sql, readerId);
        ArrayList<BorrowedBook> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Record record : recordList) {
            Book book = BookService.selectByIsbn(record.getIsbn());
            BorrowedBook borrow = new BorrowedBook();
            borrow.setIsbn(record.getIsbn())
                    .setTitle(book.getTitle())
                    .setBorrowDate(sdf.format(record.getBorrowingDate()))
                    .setReturnDate(sdf.format(record.getReturnDate()));
            result.add(borrow);
        }
        return result;
    }

    public static List<Record> borrowList(String readerId, String isbn) {
        String sql = "select * from record where reader_id = ? and isbn = ?";
        return RecordMapper.selectCustom(sql, readerId, isbn);
    }

    private static String today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

}
