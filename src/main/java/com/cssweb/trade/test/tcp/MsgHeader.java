package com.cssweb.trade.test.tcp;

import java.io.*;

/**
 * Created by chenhf on 14-1-4.
 */
public class MsgHeader {
    private int msgContentSize;
    private int crc;
    private byte zip;
    private byte msgType;
    private int functionNo;

    public final static int MSG_HEADER_SIZE = 14;
    //private byte[] msgHeader = new byte[MSG_HEADER_SIZE];


    public int getMsgContentSize() {

        return msgContentSize;
    }

    public void setMsgContentSize(int msgContentSize) {
        this.msgContentSize = msgContentSize;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public byte getZip() {
        return zip;
    }

    public void setZip(byte zip) {
        this.zip = zip;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public int getFunctionNo() {
        return functionNo;
    }

    public void setFunctionNo(int functionNo) {
        this.functionNo = functionNo;
    }

    public int getMsgHeaderSize()
    {
        return MSG_HEADER_SIZE;
    }

    public byte[] getMsgHeader() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);

       byte[] temp = toByteArray(msgContentSize, 4);
        out.write(temp, 0, 4);
       // out.writeInt(msgContentSize);


        out.writeInt(crc);
        out.writeByte(zip);
        out.writeByte(msgType);
        out.writeInt(functionNo);



        byte[] msgHeader = buf.toByteArray();

        out.close();
        buf.close();



        return msgHeader;
    }

    public void setMsgHeader(byte[] msgHeader) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(msgHeader);
        DataInputStream in = new DataInputStream(buf);

        byte[] size = new byte[4];
        in.readFully(size);
        msgContentSize = toInt(size);

        crc = in.readInt();
        zip = in.readByte();
        msgType = in.readByte();
        functionNo = in.readInt();
        in.close();
        buf.close();
    }

    public static byte[] toByteArray(int iSource, int iArrayLen) {
        byte[] bLocalArr = new byte[iArrayLen];
        for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
            bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
        }
        return bLocalArr;
    }

    public static int toInt(byte[] bRefArr) {
        int iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bRefArr.length; i++) {
            bLoop = bRefArr[i];
            iOutcome += (bLoop & 0xFF) << (8 * i);
        }
        return iOutcome;
    }
}
