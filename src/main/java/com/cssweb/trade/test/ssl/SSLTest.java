package com.cssweb.trade.test.ssl;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
/**
 * Created by chenhf on 14-1-24.
 */
public class SSLTest {
    public void test() throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        String keyStoreType = "JKS"; // android BKS

        // 密钥库

        KeyStore keyStore = KeyStore.getInstance(keyStoreType);

        String keyStoreFile = "trade.keystore.jks";
        FileInputStream keyFileInputStream = new FileInputStream(keyStoreFile);
        String keyStoreFilePassword = "cssweb";
        keyStore.load(keyFileInputStream, keyStoreFilePassword.toCharArray());

        String keyManagerAlgorithm = "X509";
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyManagerAlgorithm);
        String keyStorePassword = "cssweb";

        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();


        // 信任库
        KeyStore trustStore = KeyStore.getInstance(keyStoreType);

        String trustStoreFile = "trade.truststore.jks";
        FileInputStream trustFileInputStream = new FileInputStream(trustStoreFile);
        String trustStoreFilePassword = "cssweb";
        keyStore.load(trustFileInputStream, trustStoreFilePassword.toCharArray());

        String trustManagerAlgorithm = "X509";
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerAlgorithm);
        trustManagerFactory.init(trustStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

        // SSLContext
        String sslContextProvider = "TLS";
        SSLContext sslContext = SSLContext.getInstance(sslContextProvider);
        sslContext.init(keyManagers, trustManagers, null);


        // SSLSocket
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("172.16.3.23",8001);

        sslSocket.close();
    }


    public static void main(String[] args)
    {
        SSLTest test = new SSLTest();
        try {
            test.test();
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
}
