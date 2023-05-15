package com.lear.mapper;

import com.lear.entity.database.Reader;
import com.lear.util.JdbcUtil;

import java.util.List;

/**
 * 读者数据访问层
 * @author 天狗
 */
public class ReaderMapper {

    public static boolean insert(Reader reader) {
        String sql = "insert into reader (reader_id, first_name, last_name, address, phone_number, limit) values (?, ?, ?, ?, ?, ?)";
        return JdbcUtil.executeDML(sql, reader.getReaderId(), reader.getFirstName(), reader.getLastName(), reader.getAddress(), reader.getPhoneNumber(), reader.getLimit());
    }

    public static boolean update(Reader reader) {
        String sql = "update reader set first_name = ?, last_name = ?, address = ?, phone_number = ?, limit = ? where reader_id = ?";
        return JdbcUtil.executeDML(sql, reader.getFirstName(), reader.getLastName(), reader.getAddress(), reader.getPhoneNumber(), reader.getLimit(), reader.getReaderId());
    }

    public static boolean delete(String readerId) {
        String sql = "delete from reader where reader_id = ?";
        return JdbcUtil.executeDML(sql, readerId);
    }

    public static Reader selectOne(String readerId) {
        String sql = "select * from reader where reader_id = ?";
        return JdbcUtil.executeDQL(Reader.class, sql, readerId).get(0);
    }

    public static List<Reader> selectAll() {
        String sql = "select * from reader";
        return JdbcUtil.executeDQL(Reader.class, sql);
    }

    public static List<Reader> selectCustom(String sql, Object... params) {
        return JdbcUtil.executeDQL(Reader.class, sql, params);
    }


}
