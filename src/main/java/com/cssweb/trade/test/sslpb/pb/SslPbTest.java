package com.cssweb.trade.test.sslpb.pb;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenhf on 14-2-4.
 */
public class SslPbTest {

    public List<HashMap<String, String>> traSslConection(String request) throws Exception {
        List<HashMap<String, String>> list = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try{
            byte[] byteBody = request.getBytes();
            Pkgheader.PkgHeader req_pkgheader = Pkgheader.PkgHeader.newBuilder().setBodysize(byteBody.length)
                    .setVer(ver).setEnc(enc).setZip(zip).setMore(more).setMsgtype(Pkgheader.PkgHeader.MsgType.REQ_TRADE)
                    .setErrcode(errcode).build();
            int req_pkgheader_length = req_pkgheader.getSerializedSize();
            //request请求字符串
            byte[] req_pkg = new byte[req_pkgheader_length + byteBody.length];
            //byte[] byteBody = req.toByteArray();
            byte[] byteHeader = req_pkgheader.toByteArray();

            System.arraycopy(byteHeader, 0, req_pkg, 0, byteHeader.length);
            System.arraycopy(byteBody, 0, req_pkg, byteHeader.length, byteBody.length);

            out = new DataOutputStream(socket.getOutputStream());
            out.write(req_pkg);
            out.flush();

            in = new DataInputStream(socket.getInputStream());
            byte[] res_pkgheader = new byte[req_pkgheader_length];
            in.readFully(res_pkgheader, 0, req_pkgheader_length);
            Pkgheader.PkgHeader res_pkgheader_ = Pkgheader.PkgHeader.parseFrom(res_pkgheader);

            int res_pkgbody_length = res_pkgheader_.getBodysize();
            byte[] res_pkgbody = new byte[res_pkgbody_length];
            in.readFully(res_pkgbody, 0, res_pkgbody_length);
            if(res_pkgheader_.getZip()) {
                res_pkgbody = Connection.decompress(res_pkgbody);
            }
            //Log.i("=================res_pkgbody size = ", res_pkgbody.length + ">>>>>>>>>>>");
            ByteArrayInputStream inputStream;
            inputStream = new ByteArrayInputStream(res_pkgbody);
            final String res = read(inputStream);
            list = getResponse(res);

        }catch(Exception  e){
            e.printStackTrace();
            Log.i("4444444444444444444444444", "444444444444444444444444444444444444455 SSlConnection Socket is closed 第二次发请求");

        }

    }

    public static void main(String[] args)
    {

    }

}
