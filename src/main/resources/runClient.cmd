echo %1

echo off


set LIB=.\lib

set CP=%CLASSPATH% 


java -classpath %CP% com.cssweb.tradegatewayclient.TradeGatewayClient %1

