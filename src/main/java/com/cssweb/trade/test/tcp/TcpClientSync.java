package com.cssweb.trade.test.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClientSync {
	private Socket socket = new Socket();
	public static final String SOH = String.valueOf((char)0x01);
	public static final String DATA_CHARSET = "GBK";
	private String request;
	
	public boolean connect(String hostname, int port)
	{
		
		InetSocketAddress addr = new InetSocketAddress(hostname, port);
		try {
			socket.connect(addr);
			return socket.isConnected();
		
		} catch (IOException e) {

			e.printStackTrace();

		}
        return false;
	}
	
	public void close()
	{
		try {
			socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void heartBeat()
    {
        CustomMessage request = new CustomMessage();

        String msgContent = "cssweb_funcid=999999" + SOH;

        request.setMsgContent(msgContent.getBytes());

        request.setMsgHeader((byte)0, 0, (byte)0);

        sendRequest(request);

        CustomMessage response = new CustomMessage();
        recvResponse(response);


        try {
            System.out.println("?????" + new String(response.getMsgContent(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String send(String request, int functionNo)
    {
        System.out.println("请求内容：" + request);

        String response = "";

        CustomMessage req = new CustomMessage();
        req.setMsgContent(request.getBytes());
        req.setMsgHeader((byte)0, functionNo, (byte)0);
        sendRequest(req);

        CustomMessage resp = new CustomMessage();
        recvResponse(resp);


        try {
            System.out.println("应答内容：" + new String(resp.getMsgContent(), "GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return response;
    }


	public boolean sendRequest(CustomMessage customMessage)
	{

        try {
            DataOutputStream out =  new DataOutputStream(socket.getOutputStream());


            out.write(customMessage.getMsgHeader(), 0, customMessage.getMsgHeader().length);
            out.flush();

            out.write(customMessage.getMsgContent(), 0, customMessage.getMsgContent().length);
            out.flush();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
	}

    public boolean recvResponse(CustomMessage customMessage)  {
        try
        {
        DataInputStream in = new DataInputStream(socket.getInputStream());

        byte[] msgHeader = new byte[MsgHeader.MSG_HEADER_SIZE];
        in.read(msgHeader);

        customMessage.setMsgHeader(msgHeader);


        in.readFully(customMessage.getMsgContent());

            return true;
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }


}
