@echo off
del *.class 2> NUL
"c:\Program Files\Java\jdk1.8.0_231\bin\javac" -classpath "..\..\xith.jar;." Main.java
pause
