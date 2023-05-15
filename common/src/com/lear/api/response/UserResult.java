package com.lear.api.response;

/**
 * 用户登录结果实体类
 * @author 天狗
 */
public class UserResult {

    private String userId;

    public UserResult() {
    }

    public UserResult(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
