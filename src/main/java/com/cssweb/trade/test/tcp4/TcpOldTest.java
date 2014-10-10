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

        request = "cssweb_sysNo=njzq_jlp";
        request += SOH;

        request += "cssweb_busiType=2";
        request += SOH;

        request += "cssweb_funcid=335104";
        request += SOH;

        request += "cssweb_route=3";
        request += SOH;

        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;

        request += "cssweb_sysVer=iphone";
        request += SOH;

        request += "cssweb_note=ZRSJ:13918135116:1743137763";
        request += SOH;


        request += "identity_type=";
        request += SOH;

        request += "op_branch_no=1030";
        request += SOH;

        request += "op_entrust_way=8";
        request += SOH;

        request += "op_station=127.0.0.1";
        request += SOH;

        request += "branch_no=1030";
        request += SOH;

        request += "client_id=103030023821";
        request += SOH;

        request += "fund_account=1030000044";
        request += SOH;

        request += "password=123456";
        request += SOH;

        request += "password_type=2";
        request += SOH;

        request += "user_token=";
        request += SOH;

        request += "query_mode=";
        request += SOH;

        request += "position_str=";
        request += SOH;

        request += "request_num=1000";
        request += SOH;

        request += "exchange_type=";
        request += SOH;

        request += "stock_account=";
        request += SOH;

        request += "stock_code=";
        request += SOH;

        request += "serial_no=0";
        request += SOH;

        request += "sort_direction=1";
        request += SOH;

        request += "report_no=0";
        request += SOH;

        request += "asset_prop=7";
        request += SOH;



        try {
            Socket socket = new Socket("221.226.125.220", 9001);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            int msgHeaderSize = request.getBytes().length; // 由于字符集的原因，千万不能用String length()
            System.out.println("msgHeaderSize = " + msgHeaderSize);

            byte[] byteMsgHeaderSize = Util.hton(msgHeaderSize);

            out.write(byteMsgHeaderSize);
            out.write(request.getBytes());

            System.out.println("开始接收。。。");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            byte[] respMsgHeader = new byte[4];
            in.readFully(respMsgHeader);

            int msgContentSize = Util.ntoh(respMsgHeader);
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
