package com.lear.controller;

import com.lear.api.CommonResult;
import com.lear.api.request.BookIsbnRequest;
import com.lear.entity.database.Book;
import com.lear.service.BookService;
import com.lear.util.ParamsConvertUtil;
import com.lear.util.StringUtils;

import java.io.OutputStream;
import java.util.List;

import static com.lear.util.ParamsConvertUtil.params2Obj;


/**
 * 图书处理器
 * @author 天狗
 */
public class BookController {

    public void selectAll(String params, OutputStream outputStream) {
        CommonResult result = new CommonResult();
        List<Book> book = BookService.selectAll();
        result.success("book", book);
        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectByIsbn(String params, OutputStream outputStream) {
        BookIsbnRequest req = params2Obj(params, BookIsbnRequest.class);
        CommonResult result = new CommonResult();
        if (req==null || StringUtils.isEmpty(req.getIsbn())) {
            result.fail();
        } else {
            Book book = BookService.selectByIsbn(req.getIsbn());
            result.success("book", book);
        }

        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
