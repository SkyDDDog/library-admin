package com.lear.controller;

import com.lear.api.BaseResult;
import com.lear.api.CommonResult;
import com.lear.api.request.*;
import com.lear.entity.database.User;
import com.lear.mapper.UserMapper;
import com.lear.service.UserService;
//import com.lear.util.ApiObjConvertUtil;

import java.io.IOException;
import java.io.OutputStream;

import static com.lear.util.ParamsConvertUtil.params2Obj;

/**
 * 用户处理器
 * @author 天狗
 */
public class UserController {

    public void register(String params, OutputStream outputStream) {
        UserRequest req = params2Obj(params, UserRequest.class);
//        String result = "";
        BaseResult result = new BaseResult();
        if (req == null) {
//            result = "false";
            result.fail();
        } else {
            User user = new User();
            user.setUsername(req.getUsername());
            user.setPassword(req.getPassword());
            if (UserService.register(user)) {
                result.success();
            } else {
                result.fail();
            }
        }

        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void login(String params, OutputStream outputStream) {
        UserRequest req = params2Obj(params, UserRequest.class);
//        String result = "";
        CommonResult result = new CommonResult();
        if (req == null) {
//            result = "false";
            result.fail();
        } else {
            User user = new User();
            user.setUsername(req.getUsername());
            user.setPassword(req.getPassword());
            if (UserService.login(user)) {
                User newUser = UserMapper.getByUsername(req.getUsername());
                if (newUser!=null) {
//                    result = "true,"+newUser.getId();
                    result.success("user", newUser);
                } else {
//                    result = "false";
                    result.fail();
                }
            } else {
//                result = "false";
                result.fail();
            }
        }

        try {
            outputStream.write(result.toResponse().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
