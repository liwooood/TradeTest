echo on

cls

set LIB=.\lib

set DEP=.\dependency
set CP=%DEP%\protobuf-java-2.4.1.jar;%DEP%\junit-4.11.jar;%DEP%\httpcore-4.3.1.jar;%DEP%\httpclient-4.3.2.jar;%DEP%\hamcrest-core-1.3.jar;%DEP%\commons-logging-1.1.3.jar;%DEP%\commons-codec-1.6.jar;%CLASSPATH%

java -classpath %CP% com.cssweb.trade.test.sslpb.pb.SslPbTest

