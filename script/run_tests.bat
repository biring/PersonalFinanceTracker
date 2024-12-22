@echo off

REM ### TO RUN ALL UNITS ###
REM 1. Ensure Apache Maven is installed on the computer.
REM 2. Ensure Windows environment variable path is set to Apache Maven bin folder.
REM 3. Open terminal in IntelliJ or Windows command prompt
REM 4. [Optional] Run command "mvn -version" to verify Apache Maven is set up correctly
REM 5. Navigate to script folder in the project. Example = cd C:\Users\birin\Desktop\PersonalFinanceTracker\src\script
REM 6. Run the command = .\run_tests.bat

REM Limit variables created in this script to a local scope
setlocal

REM Change to the directory of your project root where pom.xml is located
cd ..\..

rem Run Maven tests
call mvn test

endlocal
