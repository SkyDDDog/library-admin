package com.lear;

import com.lear.api.request.*;
import com.lear.request.BaseRequest;
import com.lear.ui.MainFrame;
import sun.applet.Main;

/**
 * 客户端启动类
 * @author 天狗
 */
public class ClientApplication {

    public static void main(String[] args) {
        new MainFrame();
//        String result = BaseRequest.requestServer("/book/return", new RecordRequest("1000034", "412448357781475328"));
//        System.out.println(result);
    }

}
