@echo off
setlocal

rem Change to the directory of your project root where pom.xml is located
cd /d "C:\Users\birin\Desktop\PersonalFinanceTracker"

rem Run Maven tests
call mvn test

endlocal
