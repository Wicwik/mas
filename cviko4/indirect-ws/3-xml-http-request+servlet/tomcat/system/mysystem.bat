@echo off
set CATALINA_HOME=%~dp0..
echo %CATALINA_HOME%
cd ..
"c:\Program Files\Java\jdk1.8.0_231\bin\java" -classpath system;bin\bootstrap.jar;bin\commons-daemon.jar;bin\tomcat-juli.jar MySystem
