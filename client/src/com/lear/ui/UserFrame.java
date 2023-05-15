package com.lear.ui;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lear.api.request.BookIsbnRequest;
import com.lear.api.request.RecordRequest;
import com.lear.api.response.BorrowedBook;
import com.lear.entity.database.Book;
import com.lear.request.BaseRequest;
import com.lear.util.JsonResultParseUtil;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


/**
 * 用户借书等操作页面界面
 * @author 天狗
 */
public class UserFrame extends JFrame{

    private String userId;

    JButton info,lend,returnBook;
    JLabel label;
    JPanel panel,panelButton,panelInfo,panelInfo1,panelInfo2,panelInfo3;

    CardLayout card;

    // 借书按钮面板信息所用变量
    JButton ensure,find;
    JTextField bookNum;
    JPanel jp1,jp2,jp3;
    JTextField textField;
    DefaultTableModel tableModel;
    JTable table ;
    public UserFrame(String userId){
        this.userId = userId;
        card = new CardLayout();

        this.setBounds(300,200,600,450);
        this.setTitle("借阅信息");

        label = new JLabel("借阅信息",SwingConstants.CENTER);
        label.setFont(new Font("楷体",Font.BOLD,30));

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panelInfo = new JPanel();
        panelButton = new JPanel();
        panelInfo1 = new JPanel();
        panelInfo1.setLayout(new BorderLayout());
        panelInfo2 = new JPanel();
        panelInfo2.setLayout(new BorderLayout());
        panelInfo3 = new JPanel();
        panelInfo3.setLayout(new BorderLayout());
        panelInfo.setLayout(card);
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));

        info = new JButton("借阅信息");
        lend = new JButton("借书");
        returnBook = new JButton("还书");

        panelButton.add(info);
        panelButton.add(lend);
        panelButton.add(returnBook);

        panelInfo.add(panelInfo1,"panelInfo1");
        panelInfo.add(panelInfo2,"panelInfo2");
        panelInfo.add(panelInfo3,"panelInfo3");


        panel.add(panelButton,BorderLayout.NORTH);
        panel.add(panelInfo);

        this.add(label,BorderLayout.NORTH);
        this.add(panel);

        myEvent();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 信息显示
    class  NumFindInfo {
        public NumFindInfo(JPanel panel,JTextField text){
            System.out.println(text.getText());
            String result = BaseRequest.requestServer("/book/isbn", new BookIsbnRequest(text.getText()));
            System.out.println(result);
            Book book = JsonResultParseUtil.parseResult(result, "book", Book.class);
            Object[][] demo = new Object[1][4];
            demo[0][0] = book.getIsbn();
            demo[0][1] = book.getTitle();
            demo[0][2] = book.getAuthor();
            String[] head = {"编号","书名","作者"};
            tableModel = new DefaultTableModel(demo, head);

            table = new JTable(tableModel);
            JScrollPane scroll = new JScrollPane(table);

            panel.add(scroll);
            panel.revalidate();

        }
    }

    public void myEvent(){
        // 借书
        lend.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // 借书按钮所示卡片信息
                ensure = new JButton("确认借阅");
                find = new JButton("查找");
                bookNum = new JTextField(10);

                jp1 = new JPanel();
                jp2 = new JPanel();
                jp3 = new JPanel();
                jp1.add(bookNum);
                jp1.add(find);
                jp2.add(ensure);
                panelInfo1.add(jp1,BorderLayout.NORTH);
                panelInfo1.add(jp2,BorderLayout.SOUTH);
                // 查找事件处理
                find.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new NumFindInfo(panelInfo1,bookNum);
                    }

                });

                // 确定借阅事件处理
                ensure.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        String isbn = bookNum.getText();
                        Date date = new Date();
                        date.setTime(date.getTime() + 15*24*60*60*1000);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String returnDate = sdf.format(date);

                        String result = BaseRequest.requestServer("/book/lend", new RecordRequest(isbn, userId, returnDate));
                        System.out.println(result);
                    }

                });

                panelInfo1.validate();
                card.show(panelInfo, "panelInfo1");
            }

        });

        // 借阅信息
        info.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();

                RecordRequest req = new RecordRequest();
                req.setReaderId(userId);
                String result = BaseRequest.requestServer("/book/record", req);
                List<BorrowedBook> infoList = JsonResultParseUtil.parseResultList(result, "info", BorrowedBook.class);
                for (BorrowedBook info : infoList) {
                    ArrayList<String> al = new ArrayList<String>();
                    al.add(info.getIsbn());
                    al.add(info.getTitle());
                    al.add(info.getBorrowDate());
                    al.add(info.getReturnDate());

                    array.add(al);
                }

                int row = array.size();
                int column = array.get(0).size();
                Object[][] demo = new Object[row][column + 1];

                for(int i = 0; i < row; i++) {
                    for (int j = 0; j <= column + 2; j++) {
                        if (j < column) {
                            demo[i][j] = array.get(i).get(j);
                        }
                    }
                }

                String[] head = {"编号","书名","借书时间","应还日期"};

                DefaultTableModel tableModelInfo = new DefaultTableModel(demo,head);
                JTable tableInfo = new JTable(tableModelInfo);
                JScrollPane s = new JScrollPane(tableInfo);

                panelInfo2.add(s);
                panelInfo2.revalidate();


                card.show(panelInfo, "panelInfo2");
            }
        });

        // 还书
        returnBook.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // 借书按钮所示卡片信息
                ensure = new JButton("确认还书");
                bookNum = new JTextField(10);

                jp1 = new JPanel();
                jp1.add(bookNum);
                jp1.add(ensure);
                panelInfo3.add(jp1,BorderLayout.NORTH);

                // 确定还书事件处理
                ensure.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        String result = BaseRequest.requestServer("/book/return", new RecordRequest("1000034", "412448357781475328"));
                        System.out.println(result);
                    }

                });
                panelInfo3.revalidate();
                card.show(panelInfo, "panelInfo3");
            }

        });
    }
}