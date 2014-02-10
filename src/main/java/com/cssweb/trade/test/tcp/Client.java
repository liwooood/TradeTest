package com.cssweb.trade.test.tcp;

/**
 * Created by chenhf on 14-1-4.
 */
public class Client {

    private static final String SOH = String.valueOf((char)0x01);

    private String request = "";

    private TcpClientSync connect = new TcpClientSync();

    public void ServiceLogin() throws Exception
    {


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

        connect.send(request, 1);
    }

    public void OpenAccount() throws Exception
    {


        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x101";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;


        request += "account_type=0";
        request += SOH;

        request += "account=1655131032";
        request += SOH;

        //request += "pwd=246135";
        request += "pwd=135246";
        request += SOH;

        request += "op_code=0";
        request += SOH;

        request += "flag=0";
        request += SOH;

        request += "productno=C2-4-20";
        request += SOH;

        request += "note=127.0.0.1-null";
        request += SOH;


        connect.send(request,0x101);


    }

    public void Login() throws Exception
    {
        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x111";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;


        request += "account_type=0";
        request += SOH;

        request += "account=1655131032";
        request += SOH;

        request += "pwd=135246";
        request += SOH;

        //request += "op_code=0";
        //request += SOH;

        request += "flag=0";
        request += SOH;

        request += "productno=C2-4-20";
        request += SOH;

        request += "note=127.0.0.1-null";
        request += SOH;


        connect.send(request,0x111);
    }

    public void GetAccount() throws Exception
    {


        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x400";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;



        request += "account_type=0";
        request += SOH;

        request += "account=1655131032";
        request += SOH;

        //request += "pwd=246135";
        //request += SOH;

        request += "max_results=0";
        request += SOH;


        connect.send(request,0x400);
    }

    // 704
    public void QueryAccount() throws Exception
    {


        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x704";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;

        request += "cssweb_connid=1655131032_1";
        request += SOH;



        request += "account=131032";
        request += SOH;



        request += "flag=1";
        request += SOH;


        connect.send(request,0x704);
    }

    // 705
    public void AcceptAccount() throws Exception
    {


        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x705";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;

        request += "cssweb_connid=1655131032_1";
        request += SOH;



        request += "account=131032";
        request += SOH;

        request += "bank_id=67";
        request += SOH;

        request += "bank_account=310101581020083";
        request += SOH;

        request += "currency_type=14";
        request += SOH;

        request += "type=33";
        request += SOH;

        request += "change=0";
        request += SOH;

        request += "security_pwd=";
        request += SOH;

        request += "bank_pwd=";
        request += SOH;

        request += "sub_account=131032";
        request += SOH;

        connect.send(request,0x705);
    }

    public void f301() throws Exception
    {


        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x301";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;

        request += "cssweb_connid=1655131032_1";
        request += SOH;


        request += "account_type=0";
        request += SOH;

        request += "account=131032";
        request += SOH;


        request += "currency_type=0";
        request += SOH;



        connect.send(request,0x301);
    }
    public void f311() throws Exception
    {


        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x311";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;

        request += "cssweb_connid=1655131032_1";
        request += SOH;


        request += "account_type=0";
        request += SOH;

        request += "account=131032";
        request += SOH;


        request += "currency_type=0";
        request += SOH;



        connect.send(request,0x311);
    }

    public void f2601() throws Exception
    {


        request = "cssweb_sysNo=sywg_yht";
        request += SOH;
        request += "cssweb_busiType=1";
        request += SOH;
        request += "cssweb_funcid=0x2601";
        request += SOH;
        request += "cssweb_route=-1";
        request += SOH;
        request += "cssweb_hardinfo=127.0.0.1";
        request += SOH;
        request += "cssweb_sysVer=iphone";
        request += SOH;

        request += "cssweb_connid=1655131032_1";
        request += SOH;




        request += "account=1655131032";
        request += SOH;


        request += "bs_type=7";
        request += SOH;

        request += "ta_code=001";
        request += SOH;

        request += "security_code=202002";
        request += SOH;

        request += "fund_amount=";
        request += SOH;

        request += "price=";
        request += SOH;

        request += "effect_days=";
        request += SOH;

        request += "apply_amount=1000";
        request += SOH;

        request += "mark=";
        request += SOH;

        request += "MarketOrder_type=";
        request += SOH;

        request += "match_type=";
        request += SOH;

        request += "contract_no=";
        request += SOH;

        request += "contact_name=";
        request += SOH;

        request += "contact_tel=";
        request += SOH;

        request += "Entrust_sign= ";
        request += SOH;

        connect.send(request,0x2601);
    }


    public boolean connect()
    {
        return connect.connect("127.0.0.1", 5004);

    }

    public void close()
    {
        connect.close();
    }




    public static void main(String[] args)
    {


        Client client = new Client();
        client.connect();
        try {
            client.ServiceLogin();
           // client.Login();
            //client.GetAccount();

        } catch (Exception e) {
            e.printStackTrace();
        }

/*
        String cmd = "getAccount";

        try {

            for (int i=0; i<1; i++)
            {
                System.out.println("i=" + i);


                if (cmd.compareToIgnoreCase("open") == 0)
                {
                    client.OpenAccount();

                }

                if (cmd.compareToIgnoreCase("login") == 0)
                {

                    client.Login();
                }

                if (cmd.compareToIgnoreCase("getAccount") == 0)
                {
                    client.GetAccount();
                }

                if (cmd.compareToIgnoreCase("serviceLogin") == 0)
                {

                    client.ServiceLogin();

                }

                if (cmd.compareToIgnoreCase("queryAccount") == 0)
                {
                    client.QueryAccount();
                }

                if (cmd.compareToIgnoreCase("acceptAccount") == 0)
                {
                    client.AcceptAccount();
                }

                if (cmd.compareToIgnoreCase("311") == 0)
                {
                    client.f311();
                }

                if (cmd.compareToIgnoreCase("301") == 0)
                {
                    client.f301();
                }

                if (cmd.compareToIgnoreCase("2601") == 0)
                {
                    client.f2601();
                }
            } // end for
        }catch (Exception e)
        {
            System.out.println(e);
        }
*/
        client.close();
    }
}

