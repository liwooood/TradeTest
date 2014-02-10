package com.cssweb.trade.test.tcp4;

import com.cssweb.util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by chenhf on 14-2-10.
 */
public class TcpOldTest {
    private static final String SOH = String.valueOf((char)0x01);

    public void login()
    {
        String request = "";
        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=4";
        request += SOH;
        request += "cssweb_funcid=userLogin";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;


        request += "custid=1653037355";
        request += SOH;

        request += "userpass=111111";
        request += SOH;

        request += "idtype=1";
        request += SOH;

        try {
            Socket socket = new Socket("127.0.0.1", 5001);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            int msgHeaderSize = request.length();

            byte[] byteMsgHeaderSize = Util.hton(msgHeaderSize);

            out.write(byteMsgHeaderSize);
            out.write(request.getBytes());


            DataInputStream in = new DataInputStream(socket.getInputStream());
            byte[] respMsgHeader = new byte[4];
            in.readFully(respMsgHeader);

            int msgContentSize = Util.hton(respMsgHeader);
            System.out.println("消息内容大小" + msgContentSize);
           // System.out.println("娑堟伅鍐呭澶у皬锛?+ msgContentSize);
            byte[] msgContent = new byte[msgContentSize];
            in.readFully(msgContent);

            String response = new String(msgContent, "GBK");
            System.out.println("response=" + response);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void  main(String args[])
    {
        TcpOldTest test = new TcpOldTest();
        test.login();
    }

}
