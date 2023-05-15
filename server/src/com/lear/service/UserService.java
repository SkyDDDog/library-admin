package com.lear.service;

import com.lear.entity.database.Reader;
import com.lear.entity.database.User;
import com.lear.mapper.ReaderMapper;
import com.lear.mapper.UserMapper;
import com.lear.util.PasswordUtil;
import com.lear.util.SnowFlakeUtil;
import com.lear.util.StringUtils;

import java.util.List;

/**
 * 用户服务类
 * @author 天狗
 */
public class UserService {

    public static boolean register(User user) {
        User u = selectByUsername(user.getUsername());
        if (u!=null) {
            return false;
        }
        String id = SnowFlakeUtil.getId();
        Reader reader = new Reader();
        reader.setReaderId(id);
        user.setId(id);
        return insert(user);
    }

    public static boolean insert(User user) {
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(SnowFlakeUtil.getId());
        }
        user.setPassword(PasswordUtil.generate(user.getPassword()));

        return UserMapper.insert(user);
    }

    public static boolean login(User user) {
        String rawPassword = user.getPassword();
        User loginUser = selectByUsername(user.getUsername());
        if (loginUser==null) {
            return false;
        } else {
            return PasswordUtil.verify(rawPassword, loginUser.getPassword());
        }

    }

    private static User selectByUsername(String username) {
        String sql = "select * from user where username = ?";
        List<User> userList = UserMapper.selectCustom(sql, username);
        if (0 < userList.size()) {
            return userList.get(0);
        } else {
            return null;
        }
    }

}
