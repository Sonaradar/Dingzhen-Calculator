package com.sonaradar.calculator.webservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class socket {
    private static String serverdomain = "www.thundersoftware.top";//域名
    private static int serverport = 54300;///服务器端口
    private static int overTime = 5000;///超时设置时间 1s=1000
    //向服务器发送信息，服务器获取到信息本端再接受，返回值是接受到的信息
    public static String Sender(String message){
        try {
            Socket s = new Socket(getip(serverdomain),serverport);
            s.setSoTimeout(overTime);
            //构建
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write(message);
            System.out.println("[CAL-WS]Sending command to server:"+message);
            bw.flush();
            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = "";
            while (true) {
                String info = br.readLine();
                if (!info.isEmpty()) {
                    System.out.println("[CAL-WS]Received command from server:"+info);
                    mess=info;
                    break;
                }
            }
            br.close();
            s.close();
            return mess;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("[CAL-WS]Socket Error UnknownHostException");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[CAL-WS]Socket Error IOException");
        }
        return "";
    }
    ///域名转ip
    public static String getip(String domain) {
        try {
            return InetAddress.getByName(domain).getHostAddress();
        } catch (Exception e) {
            return "";
        }
    }
}
