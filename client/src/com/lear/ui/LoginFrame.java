package com.lear.ui;
import com.alibaba.fastjson.JSONObject;
import com.lear.api.request.*;
import com.lear.entity.database.User;
import com.lear.request.BaseRequest;
import com.lear.util.JsonResultParseUtil;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame extends JFrame{
    JLabel label,name,pass;
    JButton login,register;
//    JButton modify;
    JTextField adminName;
    JPasswordField password;
    JPanel panel,jp1,jp2;


    public LoginFrame(){
        this.setBounds(400, 200, 300, 200);
        this.setTitle("登录系统");
        this.setLayout(new BorderLayout());

        label = new JLabel("登录",SwingConstants.CENTER);
        label.setFont(new Font("楷体",Font.BOLD,30));

        name = new JLabel("账 号");
        pass = new JLabel("密 码");

        adminName = new JTextField(12);
        adminName.setHorizontalAlignment(SwingConstants.CENTER);
        password = new JPasswordField(12);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        password.setEchoChar('*');		//设置回显字符

        panel = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        panel.setLayout(new BorderLayout());

        jp1.add(adminName);
        jp1.add(name);
        jp1.add(password);
        jp1.add(pass);

        panel.add(jp1);

        register = new JButton("注册");
        login = new JButton("登录");
//        modify = new JButton("修改密码");
        jp2.add(register);
        jp2.add(login);
//        jp2.add(modify);
        panel.add(jp2,BorderLayout.SOUTH);

        this.add(label,BorderLayout.NORTH);
        this.add(panel);

        myEvent();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void myEvent(){
        // 注册事件处理
        register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
            }
        });

        // 登录事件处理
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = BaseRequest.requestServer("/user/login", new UserRequest(adminName.getText(), password.getText()));
                JSONObject jsonResult = JSONObject.parseObject(result);
                if(jsonResult.getIntValue("msgCode")==0) {
                    User user = JsonResultParseUtil.parseResult(jsonResult, "user", User.class);
                    new UserFrame(user.getId());
                } else{
                    String info = "你输入的密码不正确，原因可能是：\n" +  "1、忘记密码；\n" + "2、未开启小键盘；\n" + "3、大小写未区分。";
                    JOptionPane.showMessageDialog(null, info,"系统信息",JOptionPane.INFORMATION_MESSAGE);
                }

                LoginFrame.this.dispose();
            }

        });

    }
}
