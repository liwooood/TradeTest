package com.cssweb.trade.test.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by chenhf on 14-1-4.
 */
public class CustomMessage {

    private MsgHeader msgHeader = new MsgHeader();
    private byte[] msgContent;




    public void setMsgContent(byte[] msgContent) {
        this.msgContent = msgContent;
    }

    public void setMsgHeader(byte msgType, int functionNo, byte zip) {

        msgHeader.setMsgContentSize(msgContent.length);
        msgHeader.setCrc(0);
        msgHeader.setZip(zip);
        msgHeader.setMsgType(msgType);
        msgHeader.setFunctionNo(functionNo);
    }

    public byte[] getMsgHeader() throws IOException {
        return msgHeader.getMsgHeader();
    }

    public MsgHeader getMsgHeaderObj()
    {
        return msgHeader;
    }

    public void setMsgHeader(byte[] msgHeader) throws IOException {

        this.msgHeader.setMsgHeader(msgHeader);

        msgContent = new byte[this.msgHeader.getMsgContentSize()];
    }

    public byte[] getMsgContent() {
        return msgContent;
    }


}
