package com.lear.ui;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lear.entity.database.Book;
import com.lear.request.BaseRequest;
import com.lear.util.JsonResultParseUtil;
import com.lear.util.StringUtils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainFrame extends JFrame{
    JLabel label1,label2,info;
    JPanel jpanel,jp1,jp2,jp3;
    JTextField text;
    JButton button,stu;

    JButton lend;
    DefaultTableModel tableModel;

    public MainFrame(){
        this.setLayout(new BorderLayout());
        this.setBounds(400, 200, 600, 450);
        this.setTitle("图书查询");

        //窗体最上面的部分
        label1 = new JLabel("图书查询",SwingConstants.CENTER);
        label1.setFont(new Font("楷体",Font.BOLD,40));		//设置字体和大小

        //窗体中间的部分
        label2 = new JLabel("书名:");

        text = new JTextField(15);
        button = new JButton("查询");

        jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jp1 = new JPanel();
        jp2 = new JPanel();		//窗体最下面的部分（及显示查询内容的地方）

        stu = new JButton("用户登录");


        jp1.add(label2);
        jp1.add(text);
        jp1.add(button);
        jp3 = new JPanel();
        jp2.setLayout(new BorderLayout());
        jp3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jp3.add(stu);
        jp2.add(jp3,BorderLayout.SOUTH);

        jpanel.add(jp1,BorderLayout.NORTH);
        jpanel.add(jp2);



        this.add(label1,BorderLayout.NORTH);
        this.add(jpanel);

        myEvent();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void myEvent(){
        // 查询按钮事件
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                findInfo(jp2,text);
            }
        });

        //用户按钮事件
        stu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new LoginFrame();
            }
        });
    }

    private void findInfo(JPanel panel, JTextField text) {
//        ArrayList<Book> bookList = new ArrayList<>();

        String result = BaseRequest.requestServer("/book/all");
        List<Book> bookList = JsonResultParseUtil.parseResultList(result, "book", Book.class);
        // 展示内容
        // 搜索词
        String findName = text.getText();

        if (StringUtils.isEmpty(findName)) {
            int row = bookList.size();
            int col = 3;
            Object[][] demo = new Object[row][col + 1];
            for (Book book : bookList) {
                demo[bookList.indexOf(book)][0] = book.getIsbn();
                demo[bookList.indexOf(book)][1] = book.getTitle();
                demo[bookList.indexOf(book)][2] = book.getAuthor();
            }

            String[] head = {"ISBN","书名","作者"};

            DefaultTableModel tableModel = new DefaultTableModel(demo,head);
            JTable table = new JTable(tableModel);
            table.setEnabled(false);
            JScrollPane scroll = new JScrollPane(table);
            panel.add(scroll);
            panel.revalidate();
        }


    }


}