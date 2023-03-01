"c:\Program Files\Java\jdk1.8.0_231\bin\java" -classpath .;..\xith.jar;xercesimpl.jar -Dorg.xml.sax.parser="org.apache.xerces.parsers.SAXParser" -Djava.library.path="..\bin;." jade.Boot -local-host 158.195.28.155 -host 158.195.28.155 -port 1099 -aclcodecs jamr.jadeacl.xml.XMLACLCodec -container Ball:BallAgent
pause
