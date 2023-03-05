"c:\Program Files\Java\jdk-18\bin\java" -classpath .;..\xith.jar;xercesimpl.jar -Dorg.xml.sax.parser="org.apache.xerces.parsers.SAXParser" -Djava.library.path="..\bin;." jade.Boot -local-host 192.168.100.21 -host 192.168.100.21 -port 1099 -local-port 7171 -aclcodecs jamr.jadeacl.xml.XMLACLCodec -container Ball2:BallAgent
pause
