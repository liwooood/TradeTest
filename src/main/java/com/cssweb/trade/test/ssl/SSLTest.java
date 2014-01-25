package com.cssweb.trade.test.ssl;

import com.cssweb.trade.test.tcp.CustomMessage;
import com.cssweb.trade.test.tcp.MsgHeader;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.*;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
/**
 * Created by chenhf on 14-1-24.
 */
public class SSLTest {
    public void test()  {

        try{


        String keyStoreType = "JKS"; // android BKS


        // 密钥库

        KeyStore keyStore = KeyStore.getInstance(keyStoreType);

        String keyStoreFile = "e:/pki/ca/trade.keystore.jks";


        //InputStream keyFileInputStream = SSLTest.class.getResourceAsStream(keyStoreFile);
        FileInputStream keyFileInputStream = new FileInputStream(keyStoreFile);
        String keyStoreFilePassword = "cssweb";
        keyStore.load(keyFileInputStream, keyStoreFilePassword.toCharArray());

        String keyManagerAlgorithm = KeyManagerFactory.getDefaultAlgorithm();

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyManagerAlgorithm);
        String keyStorePassword = "cssweb";

        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();


        // 信任库
        KeyStore trustStore = KeyStore.getInstance(keyStoreType);


        String trustStoreFile = "e:/pki/ca/trade.truststore.jks";

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

        String request = "test;";

        CustomMessage req = new CustomMessage();
        req.setMsgContent(request.getBytes());
        req.setMsgHeader((byte)0, 0, (byte)0);

        DataOutputStream out =  new DataOutputStream(sslSocket.getOutputStream());


        out.write(req.getMsgHeader(), 0, req.getMsgHeader().length);
        out.flush();

        out.write(req.getMsgContent(), 0, req.getMsgContent().length);
        out.flush();

        CustomMessage resp = new CustomMessage();
        DataInputStream in = new DataInputStream(sslSocket.getInputStream());

        byte[] msgHeader = new byte[MsgHeader.MSG_HEADER_SIZE];
        in.read(msgHeader);

        resp.setMsgHeader(msgHeader);


        in.readFully(resp.getMsgContent());

        sslSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        SSLTest test = new SSLTest();

            test.test();

    }
}
