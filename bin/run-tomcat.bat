@echo off
echo.
echo [信息] 使用内嵌Tomcat运行Web工程。
echo.

%~d0
cd %~dp0

cd ..
title %cd%
set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m
call mvn clean spring-boot:run -Dmaven.test.skip=true -U

pause