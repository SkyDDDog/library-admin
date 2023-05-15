package com.lear.service;

import com.lear.entity.database.Reader;
import com.lear.entity.database.User;
import com.lear.entity.database.example.ReaderExample;
import com.lear.mapper.ReaderMapper;
import com.lear.util.SnowFlakeUtil;
import com.lear.util.StringUtils;

import java.util.List;

/**
 * 读者服务类
 * @author 天狗
 */
public class ReaderService {

    public static boolean insert(Reader reader) {
        if (StringUtils.isEmpty(reader.getReaderId())) {
            reader.setReaderId(SnowFlakeUtil.getId());
        }
        return ReaderMapper.insert(reader);
    }

    public static int insertList(List<Reader> readerList) {
        int count = 0;
        for (Reader reader : readerList) {
            if (insert(reader)) {
                count++;
            }
        }
        return count;
    }

    public static List<Reader> selectAll() {
        return ReaderMapper.selectAll();
    }

    public static Reader selectOne(String readerId) {
        return ReaderMapper.selectOne(readerId);
    }

    public static boolean update(Reader reader) {
        return ReaderMapper.update(reader);
    }

    public static int getLimitByReaderId(String readerId) {
        Reader reader = ReaderMapper.selectOne(readerId);
        if (reader == null) {
            return 0;
        } else {
            return reader.getLimit();
        }
    }

    public static List<Reader> selectByExample(ReaderExample example) {
        return ReaderMapper.selectCustom(example.toSql());
    }



}
