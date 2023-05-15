package com.lear.ui;


import com.alibaba.fastjson.JSONObject;
import com.lear.request.BaseRequest;
import com.lear.api.request.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class RegisterFrame extends JFrame{

    JPanel panel;

    JTextField text;
    JPasswordField password1,password2;

    JLabel title,user,pass1,pass2;

    JButton button;

    public RegisterFrame(){
        this.setBounds(400,200,300,200);
        this.setTitle("注册");
        this.setLayout(new BorderLayout());

        title = new JLabel("注册",SwingConstants.CENTER);
        title.setFont(new Font("楷体",Font.BOLD,30));

        panel = new JPanel();

        text = new JTextField(15);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        password1 = new JPasswordField(15);
        password1.setEchoChar('*');
        password1.setHorizontalAlignment(SwingConstants.CENTER);
        password2 = new JPasswordField(15);
        password2.setEchoChar('*');
        password2.setHorizontalAlignment(SwingConstants.CENTER);

        user = new JLabel("用         户");
        pass1 = new JLabel("密         码");
        pass2 = new JLabel("确认密码");

        button = new JButton("点击注册");

        panel.add(text);
        panel.add(user);
        panel.add(password1);
        panel.add(pass1);
        panel.add(password2);
        panel.add(pass2);

        this.add(title,BorderLayout.NORTH);
        this.add(panel);
        this.add(button,BorderLayout.SOUTH);

        myEvent();

		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void myEvent(){
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                String userPassword1 = password1.getText();
                String userPassword2 = password2.getText();

                if(userPassword1.equals(userPassword2)){
                    String result = BaseRequest.requestServer("/user/register", new UserRequest(text.getText(), userPassword1));
                    JSONObject jsonResult = JSONObject.parseObject(result);
                    if(jsonResult.getIntValue("msgCode")==0) {
                        new LoginFrame();
                        RegisterFrame.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "该用户名已存在，请更换用户名！","系统信息",JOptionPane.WARNING_MESSAGE);
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "两次密码不一致，请重新输入密码！！！","系统信息",JOptionPane.WARNING_MESSAGE);
                }

            }

        });
    }

}