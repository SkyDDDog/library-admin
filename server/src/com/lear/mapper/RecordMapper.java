package com.lear.mapper;

import com.lear.entity.database.Record;
import com.lear.util.JdbcUtil;
import com.lear.util.StringUtils;

import java.util.List;

/**
 * 借书记录数据访问层
 * @author 天狗
 */
public class RecordMapper {

    public static boolean insert(Record record) {
        String sql = "insert into record (record_id, isbn, reader_id, borrowing_date, return_date) values (?, ?, ?, ?, ?)";
        return JdbcUtil.executeDML(sql,
                record.getRecordId(),
                record.getIsbn(),
                record.getReaderId(),
                record.getBorrowingDate(),
                record.getReturnDate()
        );
    }

    public static boolean update(Record record) {
        String sql = "update record set isbn = ?, reader_id = ?, borrowing_date = ?, return_date = ? where record_id = ?";
        return JdbcUtil.executeDML(sql,
                record.getIsbn(),
                record.getReaderId(),
                record.getBorrowingDate(),
                record.getReturnDate(),
                record.getRecordId()
        );
    }

    public static boolean delete(String recordId) {
        String sql = "delete from record where record_id = ?";
        return JdbcUtil.executeDML(sql, recordId);
    }

    public static Record selectOne(String recordId) {
        String sql = "select * from record where record_id = ?";
        return JdbcUtil.executeDQL(Record.class, sql, recordId).get(0);
    }

    public static List<Record> selectAll() {
        String sql = "select * from record";
        return JdbcUtil.executeDQL(Record.class, sql);
    }

    public static List<Record> selectCustom(String sql, Object... params) {
        return JdbcUtil.executeDQL(Record.class, sql, params);
    }


}
