An example of calling servlet via XML Http Request

launch tomcat\system\mysystem.bat
wait until it is fully started
browse to http://localhost:8080/html/index.html 
and press the appeared button more times

How it works:

tomcat\system\MySystem.java 
- starts JVM in which we run not just our application logic represented
  by the method calculate() but also the tomcat webserver

tomcat provides ordinary services, i.e. it picks up html documents from 
the directory bundle tomcat\webapps\ROOT

there is index.html in tomcat\webapps\ROOT\html\ which contains html code and javascript
called by the click on the button, compounding XML Http Request
http://localhost:8080/a?arg=1
this request can be also called from browser (try it, please)
however this is not its mission hence it is not responding with html but javascript 
(alternatively it could return XML)

the responded code is received by tomcat and fllowing configuration in 
tomcat\webapps\ROOT\WEB-INF\web.xml
it knows that this call of servlet, implemented by the class A

the servlet is implemented in 
\tomcat\webapps\ROOT\WEB-INF\classes\A.java

