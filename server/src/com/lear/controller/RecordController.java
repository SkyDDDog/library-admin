package com.lear.controller;

import com.lear.api.CommonResult;
import com.lear.api.request.RecordRequest;
import com.lear.api.response.BorrowedBook;
import com.lear.entity.database.Record;
import com.lear.service.RecordService;
import com.lear.util.ParamsConvertUtil;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 记录处理器
 * @author 天狗
 */
public class RecordController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");

    public void addRecord(String params, OutputStream outputStream) {
        CommonResult result = new CommonResult();
        RecordRequest req = ParamsConvertUtil.params2Obj(params, RecordRequest.class);
        if (req == null) {
            result.fail();
        } else {
            if (RecordService.isBorrowed(req.getIsbn())) {
                result.fail("该书已被借阅");
            } else {
                Record record = new Record();
                record.setIsbn(req.getIsbn());
                record.setReaderId(req.getReaderId());
                try {
                    record.setReturnDate(sdf.parse(req.getReturnDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                record.setBorrowingDate(new Date());
                if (RecordService.insert(record)) {
                    result.success();
                } else {
                    result.fail();
                }
            }
        }

        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook(String params, OutputStream outputStream) {
        CommonResult result = new CommonResult();
        RecordRequest req = ParamsConvertUtil.params2Obj(params, RecordRequest.class);
        if (req == null) {
            result.fail();
        } else {
            Record record = new Record();
            record.setIsbn(req.getIsbn());
            record.setReaderId(req.getReaderId());
            if (RecordService.returnBook(record)) {
                result.success();
            } else {
                result.fail();
            }
        }

        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void isBorrowed(String params, OutputStream outputStream) {
        CommonResult result = new CommonResult();
        RecordRequest req = ParamsConvertUtil.params2Obj(params, RecordRequest.class);
        if (req == null) {
            result.fail();
        } else {
            Record record = new Record();
            record.setIsbn(req.getIsbn());
            record.setReaderId(req.getReaderId());
            if (RecordService.isBorrowed(record.getIsbn())) {
                result.success();
            } else {
                result.fail();
            }
        }

        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrowBookList(String params, OutputStream outputStream) {
        CommonResult result = new CommonResult();
        RecordRequest req = ParamsConvertUtil.params2Obj(params, RecordRequest.class);
        if (req == null) {
            result.fail();
        } else {
            List<BorrowedBook> borrow = RecordService.borrowedBookList(req.getReaderId());
            result.success("info", borrow);
        }

        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
