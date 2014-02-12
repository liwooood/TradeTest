package com.cssweb.trade.test.sslpb.pb;

import com.cssweb.util.Util;
import quote.Pkgheader;

import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenhf on 14-2-4.
 */
public class SslPbTest {
    private static final String SOH = String.valueOf((char)0x01);

    public void test() {



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
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("127.0.0.1", 5000);

            boolean bRet = sslSocket.isConnected();

            sslSocket.startHandshake();

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

            byte[] byteBody = request.getBytes();

            Pkgheader.PkgHeader req_pkgheader = Pkgheader.PkgHeader.newBuilder().setBodysize(byteBody.length)
                    .setVer(1).setEnc(false).setZip(false).setMore(false).setMsgtype(Pkgheader.PkgHeader.MsgType.REQ_TRADE)
                    .setErrcode(0).build();

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

        }catch(Exception  e){
            e.printStackTrace();


        }

    }

    public static void main(String[] args)
    {
        SslPbTest test = new SslPbTest();
        test.test();
    }

}
