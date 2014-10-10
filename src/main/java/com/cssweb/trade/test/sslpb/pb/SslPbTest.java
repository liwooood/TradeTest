package com.cssweb.trade.test.sslpb.pb;

import com.cssweb.util.Util;
import quote.Pkgheader;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenhf on 14-2-4.
 */
public class SslPbTest {
    private static final String SOH = String.valueOf((char)0x01);
    private SSLSocket sslSocket = null;

    public void connect() {
        try{

            String keyStoreType = "JKS"; // android BKS


            // 密钥�?

            KeyStore keyStore = KeyStore.getInstance(keyStoreType);

            String keyStoreFile = "E:\\Trade\\TradeTest\\src\\main\\resources\\trade_ssl.jks";


            //InputStream keyFileInputStream = SSLTest.class.getResourceAsStream(keyStoreFile);
            FileInputStream keyFileInputStream = new FileInputStream(keyStoreFile);
            String keyStoreFilePassword = "cssweb";
            keyStore.load(keyFileInputStream, keyStoreFilePassword.toCharArray());

            String keyManagerAlgorithm = KeyManagerFactory.getDefaultAlgorithm();

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyManagerAlgorithm);
            String keyStorePassword = "cssweb";

            keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();


            // 信任�?
            KeyStore trustStore = KeyStore.getInstance(keyStoreType);


            String trustStoreFile = "E:\\Trade\\TradeTest\\src\\main\\resources\\trade_ssl.jks";

            //InputStream trustFileInputStream = SSLTest.class.getResourceAsStream(trustStoreFile);
            FileInputStream trustFileInputStream = new FileInputStream(trustStoreFile);
            String trustStoreFilePassword = "cssweb";
            trustStore.load(trustFileInputStream, trustStoreFilePassword.toCharArray());

            String trustManagerAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerAlgorithm);
            trustManagerFactory.init(trustStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            // SSLContext
            String sslContextProvider = "TLS";
            SSLContext sslContext = SSLContext.getInstance(sslContextProvider);
            sslContext.init(keyManagers, trustManagers, null);


            // SSLSocket
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        /*
        System.setProperty("javax.net.ssl.keyStore", "E:/PKI/CA/test.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "cssweb");

        System.setProperty("javax.net.ssl.trustStore", "E:/PKI/CA/test.keystore");
        System.setProperty("javax.net.ssl.trustStorePassword", "cssweb");

        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
*/
           // sslSocket  = (SSLSocket) sslSocketFactory.createSocket("221.226.125.220", 5121);
          //  sslSocket  = (SSLSocket) sslSocketFactory.createSocket("114.141.180.149", 8001);

            //sslSocket  = (SSLSocket) sslSocketFactory.createSocket("114.141.180.137", 8009);
            sslSocket  = (SSLSocket) sslSocketFactory.createSocket("127.0.0.1", 5000);

            boolean bRet = sslSocket.isConnected();

            sslSocket.startHandshake();











        }catch(Exception  e){
            e.printStackTrace();


        }

    }

    public void close() throws IOException {
        sslSocket.close();
    }



    public void f304230() throws IOException {
        String request = "";

        request = "cssweb_sysNo=njzq_jlp";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=304230";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=aphone";
        request += SOH;
        request += "cssweb_note=ZRSJ:18621763219";
        request += SOH;


        request += "FID_KHH=121000003650";
        request += SOH;

        request += "FID_JYMM=123321";
        request += SOH;

        request += "FID_YYB=1210";
        request += SOH;



        byte[] byteBody = request.getBytes();



        Pkgheader.PkgHeader req_pkgheader = Pkgheader.PkgHeader.newBuilder().setBodysize(byteBody.length)
                .setVer(1).setEnc(false).setZip(false).setMore(false).setMsgtype(Pkgheader.PkgHeader.MsgType.REQ_TRADE)
                .setErrcode(4).build();

        int req_pkgheader_length = req_pkgheader.getSerializedSize();
        //request请求字符�?
        byte[] req_pkg = new byte[req_pkgheader_length + byteBody.length];
        //byte[] byteBody = req.toByteArray();
        byte[] byteHeader = req_pkgheader.toByteArray();

        System.arraycopy(byteHeader, 0, req_pkg, 0, byteHeader.length);
        System.arraycopy(byteBody, 0, req_pkg, byteHeader.length, byteBody.length);

        DataOutputStream out = new DataOutputStream(sslSocket.getOutputStream());
        out.write(req_pkg);
        out.flush();

        DataInputStream in = new DataInputStream(sslSocket.getInputStream());
        byte[] res_pkgheader = new byte[req_pkgheader_length];
        in.readFully(res_pkgheader, 0, req_pkgheader_length);

        Pkgheader.PkgHeader res_pkgheader_ = Pkgheader.PkgHeader.parseFrom(res_pkgheader);

        int res_pkgbody_length = res_pkgheader_.getBodysize();
        byte[] res_pkgbody = new byte[res_pkgbody_length];
        in.readFully(res_pkgbody, 0, res_pkgbody_length);

        if (res_pkgheader_.getZip())
        {
            byte[] msgContent = Util.decompress(res_pkgbody);
            String response = new String(msgContent, "GBK");
            System.out.println("response=" + response);

        }
        else
        {

            String response = new String(res_pkgbody, "GBK");
            System.out.println("response=" + response);
        }
    }

    public void f204501() throws IOException {
        String request = "";

        request = "cssweb_sysNo=njzq_jlp";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=204501";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=aphone";
        request += SOH;
        request += "cssweb_note=ZRSJ:18621763219";
        request += SOH;


        request += "FID_KHH=000001045608";
        request += SOH;

        request += "FID_GDH=A680455524";
        request += SOH;

        request += "FID_JYS=SH";
        request += SOH;

        request += "FID_JYLB=1";
        request += SOH;

        request += "FID_ZQDM=600000";
        request += SOH;

        request += "FID_WTSL=100";
        request += SOH;

        request += "FID_WTJG=19.00";
        request += SOH;

        request += "FID_JYMM=123321";
        request += SOH;

        request += "FID_TOKEN=";
        request += SOH;

        request += "FID_DDLX=";
        request += SOH;

        request += "FID_WTPCH=";
        request += SOH;

        request += "FID_HYH=";
        request += SOH;

        request += "FID_DFXW=";
        request += SOH;

        byte[] byteBody = request.getBytes();



        Pkgheader.PkgHeader req_pkgheader = Pkgheader.PkgHeader.newBuilder().setBodysize(byteBody.length)
                .setVer(1).setEnc(false).setZip(false).setMore(false).setMsgtype(Pkgheader.PkgHeader.MsgType.REQ_TRADE)
                .setErrcode(4).build();

        int req_pkgheader_length = req_pkgheader.getSerializedSize();
        //request请求字符�?
        byte[] req_pkg = new byte[req_pkgheader_length + byteBody.length];
        //byte[] byteBody = req.toByteArray();
        byte[] byteHeader = req_pkgheader.toByteArray();

        System.arraycopy(byteHeader, 0, req_pkg, 0, byteHeader.length);
        System.arraycopy(byteBody, 0, req_pkg, byteHeader.length, byteBody.length);

        DataOutputStream out = new DataOutputStream(sslSocket.getOutputStream());
        out.write(req_pkg);
        out.flush();

        DataInputStream in = new DataInputStream(sslSocket.getInputStream());
        byte[] res_pkgheader = new byte[req_pkgheader_length];
        in.readFully(res_pkgheader, 0, req_pkgheader_length);

        Pkgheader.PkgHeader res_pkgheader_ = Pkgheader.PkgHeader.parseFrom(res_pkgheader);

        int res_pkgbody_length = res_pkgheader_.getBodysize();
        byte[] res_pkgbody = new byte[res_pkgbody_length];
        in.readFully(res_pkgbody, 0, res_pkgbody_length);

        if (res_pkgheader_.getZip())
        {
            byte[] msgContent = Util.decompress(res_pkgbody);
            String response = new String(msgContent, "GBK");
            System.out.println("response=" + response);

        }
        else
        {

            String response = new String(res_pkgbody, "GBK");
            System.out.println("response=" + response);
        }
    }

    public void f304103() throws IOException {
        String request = "";


            request = "cssweb_sysNo=njzq_jlp";
            request += SOH;
            request += "cssweb_busiType=1";
            request += SOH;
            request += "cssweb_funcid=304103";
            request += SOH;
            request += "cssweb_route=-1";
            request += SOH;
            request += "cssweb_hardinfo=127.0.0.1";
            request += SOH;
            request += "cssweb_sysVer=iphone";
            request += SOH;
            request += "cssweb_note=ZRSJ:18621763219";
            request += SOH;


            request += "FID_KHH=000001045608";
            request += SOH;

            request += "FID_SORTTYPE=1";
            request += SOH;

            request += "FID_YYB=0011";
            request += SOH;

        byte[] byteBody = request.getBytes();



        Pkgheader.PkgHeader req_pkgheader = Pkgheader.PkgHeader.newBuilder().setBodysize(byteBody.length)
                .setVer(1).setEnc(false).setZip(false).setMore(false).setMsgtype(Pkgheader.PkgHeader.MsgType.REQ_TRADE)
                .setErrcode(4).build();

        int req_pkgheader_length = req_pkgheader.getSerializedSize();
        //request请求字符�?
        byte[] req_pkg = new byte[req_pkgheader_length + byteBody.length];
        //byte[] byteBody = req.toByteArray();
        byte[] byteHeader = req_pkgheader.toByteArray();

        System.arraycopy(byteHeader, 0, req_pkg, 0, byteHeader.length);
        System.arraycopy(byteBody, 0, req_pkg, byteHeader.length, byteBody.length);

        DataOutputStream out = new DataOutputStream(sslSocket.getOutputStream());
        out.write(req_pkg);
        out.flush();

        DataInputStream in = new DataInputStream(sslSocket.getInputStream());
        byte[] res_pkgheader = new byte[req_pkgheader_length];
        in.readFully(res_pkgheader, 0, req_pkgheader_length);

        Pkgheader.PkgHeader res_pkgheader_ = Pkgheader.PkgHeader.parseFrom(res_pkgheader);

        int res_pkgbody_length = res_pkgheader_.getBodysize();
        byte[] res_pkgbody = new byte[res_pkgbody_length];
        in.readFully(res_pkgbody, 0, res_pkgbody_length);

        if (res_pkgheader_.getZip())
        {
            byte[] msgContent = Util.decompress(res_pkgbody);
            String response = new String(msgContent, "GBK");
            System.out.println("response=" + response);

        }
        else
        {

            String response = new String(res_pkgbody, "GBK");
            System.out.println("response=" + response);
        }
    }

    public void f190101() throws IOException {
        String request = "";


        request = "cssweb_sysNo=njzq_jlp";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=190101";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=web";
        request += SOH;
        request += "cssweb_note=ZRSJ:18621763219";
        request += SOH;


        request += "FID_KHH=121000003650";
        request += SOH;

        request += "FID_YYB=";
        request += SOH;

        request += "FID_JYMM=123321";
        request += SOH;

        byte[] byteBody = request.getBytes();



        Pkgheader.PkgHeader req_pkgheader = Pkgheader.PkgHeader.newBuilder().setBodysize(byteBody.length)
                .setVer(1).setEnc(false).setZip(false).setMore(false).setMsgtype(Pkgheader.PkgHeader.MsgType.REQ_TRADE)
                .setErrcode(4).build();

        int req_pkgheader_length = req_pkgheader.getSerializedSize();
        //request请求字符�?
        byte[] req_pkg = new byte[req_pkgheader_length + byteBody.length];
        //byte[] byteBody = req.toByteArray();
        byte[] byteHeader = req_pkgheader.toByteArray();

        System.arraycopy(byteHeader, 0, req_pkg, 0, byteHeader.length);
        System.arraycopy(byteBody, 0, req_pkg, byteHeader.length, byteBody.length);

        DataOutputStream out = new DataOutputStream(sslSocket.getOutputStream());
        out.write(req_pkg);
        out.flush();

        DataInputStream in = new DataInputStream(sslSocket.getInputStream());
        byte[] res_pkgheader = new byte[req_pkgheader_length];
        in.readFully(res_pkgheader, 0, req_pkgheader_length);

        Pkgheader.PkgHeader res_pkgheader_ = Pkgheader.PkgHeader.parseFrom(res_pkgheader);

        int res_pkgbody_length = res_pkgheader_.getBodysize();
        byte[] res_pkgbody = new byte[res_pkgbody_length];
        in.readFully(res_pkgbody, 0, res_pkgbody_length);

        if (res_pkgheader_.getZip())
        {
            byte[] msgContent = Util.decompress(res_pkgbody);
            String response = new String(msgContent, "GBK");
            System.out.println("response=" + response);

        }
        else
        {

            String response = new String(res_pkgbody, "GBK");
            System.out.println("response=" + response);
        }
    }

    public void f337001() throws IOException {
        String request = "";


        request = "cssweb_sysNo=gtja_yht";
        request += SOH;
        request += "cssweb_busiType=0";
        request += SOH;
        request += "cssweb_funcid=337001";
        request += SOH;
        request += "cssweb_route=999";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;
        request += "cssweb_note=ZRSJ:18621763219";
        request += SOH;

        request += "funcid=337001";
        request += SOH;
        request += "identity_type=";
        request += SOH;
        request += "op_branch_no=";
        request += SOH;
        request += "op_entrust_way=";
        request += SOH;
        request += "op_station=";
        request += SOH;
        request += "branch_no=";
        request += SOH;

        request += "cif_account=";
        request += SOH;
        request += "password=";
        request += SOH;
        request += "user_token=";
        request += SOH;
        request += "cssweb_os=";
        request += SOH;
        request += "ip=";
        request += SOH;
        request += "source=";
        request += SOH;
        request += "futu_user_token=";
        request += SOH;

        request += "reuse=";
        request += SOH;
        request += "client_id=";
        request += SOH;
        request += "pd_pro_cls=4";
        request += SOH;
        request += "pd_pro_type=";
        request += SOH;
        request += "pd_sec_cls=";
        request += SOH;
        request += "prod_name=";
        request += SOH;
        request += "prod_code=";
        request += SOH;

        request += "pd_invest_type=";
        request += SOH;
        request += "pd_end_pro_type=";
        request += SOH;
        request += "pd_beg_inv_origin_secu=";
        request += SOH;
        request += "pd_end_inv_origin_secu=";
        request += SOH;
        request += "pd_beg_inv_origin=";
        request += SOH;
        request += "pd_end_inv_origin=";
        request += SOH;
        request += "pd_beg_new_nav=";
        request += SOH;
        request += "pd_end_new_nav=";
        request += SOH;
        request += "pd_inv_style=";
        request += SOH;
        request += "pd_en_pro_sta=";
        request += SOH;
        request += "pd_rec_lvl=";
        request += SOH;

        request += "pd_risk_lvl=";
        request += SOH;
        request += "pd_ord_by=";
        request += SOH;
        request += "pd_cur_page=1";
        request += SOH;
        request += "pd_ret_rows=20";
        request += SOH;

        request += "pd_en_sys_sou=";
        request += SOH;

        request += "pd_fuzzy_info=";
        request += SOH;

        request += "pd_run_cycle=";
        request += SOH;

        request += "pd_unit_yld_rmb_low=";
        request += SOH;

        request += "pd_unit_yld_rmb_up=";
        request += SOH;

        request += "pd_exp_rate_per_sevenday_low=";
        request += SOH;

        request += "pd_exp_rate_per_sevenday_up=";
        request += SOH;

        request += "pd_exp_yld_rmb_low=";
        request += SOH;


        request += "pd_exp_yld_rmb_up=";
        request += SOH;

        request += "pd_pro_scale_low=";
        request += SOH;

        request += "pd_pro_scale_up=";
        request += SOH;

        request += "pd_exp_gains_up=";
        request += SOH;

        request += "pd_exp_gains_down=";
        request += SOH;

        request += "pd_sell_sta=";
        request += SOH;

        request += "pd_pro_deadline=";
        request += SOH;

        request += "pd_risk_type=";
        request += SOH;

        byte[] byteBody = request.getBytes();



        Pkgheader.PkgHeader req_pkgheader = Pkgheader.PkgHeader.newBuilder().setBodysize(byteBody.length)
                .setVer(1).setEnc(false).setZip(false).setMore(false).setMsgtype(Pkgheader.PkgHeader.MsgType.REQ_TRADE)
                .setErrcode(4).build();

        int req_pkgheader_length = req_pkgheader.getSerializedSize();
        //request请求字符�?
        byte[] req_pkg = new byte[req_pkgheader_length + byteBody.length];
        //byte[] byteBody = req.toByteArray();
        byte[] byteHeader = req_pkgheader.toByteArray();

        System.arraycopy(byteHeader, 0, req_pkg, 0, byteHeader.length);
        System.arraycopy(byteBody, 0, req_pkg, byteHeader.length, byteBody.length);

        DataOutputStream out = new DataOutputStream(sslSocket.getOutputStream());
        out.write(req_pkg);
        out.flush();

        DataInputStream in = new DataInputStream(sslSocket.getInputStream());
        byte[] res_pkgheader = new byte[req_pkgheader_length];
        in.readFully(res_pkgheader, 0, req_pkgheader_length);

        Pkgheader.PkgHeader res_pkgheader_ = Pkgheader.PkgHeader.parseFrom(res_pkgheader);

        int res_pkgbody_length = res_pkgheader_.getBodysize();
        byte[] res_pkgbody = new byte[res_pkgbody_length];
        in.readFully(res_pkgbody, 0, res_pkgbody_length);

        if (res_pkgheader_.getZip())
        {
            byte[] msgContent = Util.decompress(res_pkgbody);
            String response = new String(msgContent, "GBK");
            System.out.println("��ѹ��response=" + response);

        }
        else
        {

            String response = new String(res_pkgbody, "GBK");
            System.out.println("ԭʼresponse=" + response);
        }
    }

    public static void main(String[] args)
    {
        SslPbTest test = new SslPbTest();

        try {
            test.connect();
            test.f190101();
            //test.f337001();
            //test.f304230();
            //test.f190101();
            //test.f204501();
            //test.f304103();
            test.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
