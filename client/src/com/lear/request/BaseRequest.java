package com.lear.request;

import java.io.*;
import java.net.Socket;
import com.lear.constant.ServerMessage;
import com.lear.util.ParamsConvertUtil;

/**
 * 基础tcp请求封装类
 * @author 天狗
 */
public class BaseRequest {

    public static String requestServer(String path) {
        return requestServer(path, "");
    }

    public static String requestServer(String path, Object obj) {
        String params = ParamsConvertUtil.obj2Params(obj);
        return requestServer(path, params);
    }

    public static String requestServer(String path, String params) {
        String url = path+params;
        StringBuilder resultBuilder = new StringBuilder();
        System.out.println(url);
        try {
            //1.创建socket对象
            Socket socket=new Socket(ServerMessage.HOST, ServerMessage.PORT);

            //2.发送登录信息
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write(url);
            printWriter.flush();
            socket.shutdownOutput();
            //3.接收服务器响应
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            //为输入端添加缓冲
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String info="";
            while ((info=bufferedReader.readLine())!=null)  {
                resultBuilder.append(info);
            }

            //关闭资源
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }

//    public static String requestServer(String path, String... params) {
//        StringBuilder sb = new StringBuilder();
//        StringBuilder urlBuilder = new StringBuilder(path);
//        urlBuilder.append('?');
//        for (int i = 0; i < params.length-1; i++) {
//            urlBuilder.append(params[i]).append('&');
//        }
//        urlBuilder.append(params[params.length-1]);
//
//
//        try {
//            //1.创建socket对象
//            Socket socket=new Socket(ServerMessage.HOST, ServerMessage.PORT);
//
//            //2.发送登录信息
//            OutputStream outputStream = socket.getOutputStream();
//            PrintWriter printWriter = new PrintWriter(outputStream);
//            printWriter.write(urlBuilder.toString());
//            printWriter.flush();
////            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
////            dataOutputStream.writeUTF(urlBuilder.toString());
////            dataOutputStream.flush();
//            socket.shutdownOutput();
//            //3.接收服务器响应
//            InputStream inputStream = socket.getInputStream();
//            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
//            //为输入端添加缓冲
//            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//            String info="";
//            while ((info=bufferedReader.readLine())!=null)  {
//                sb.append(info);
//            }
//
//            //关闭资源
//            inputStream.close();
//            inputStreamReader.close();
//            bufferedReader.close();
//            printWriter.close();
////            dataOutputStream.close();
//            outputStream.close();
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }

}
