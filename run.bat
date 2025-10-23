@echo off
chcp 1251
cd src\main\java
javac -encoding windows-1251 -d . com\mycompany\*.java
jar cfe ..\..\..\pizza-app.jar com.mycompany.App com\mycompany\*.class
cd ..\..\..
java -Dfile.encoding=CP866 -jar pizza-app.jar
pause