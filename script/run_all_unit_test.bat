@echo off

REM ### TO RUN ALL UNITS ###
REM 1. Ensure Apache Maven is installed on the computer.
REM 2. Ensure Windows environment variable path is set to Apache Maven bin folder.
REM 3. Open terminal in IntelliJ or Windows command prompt
REM 4. [Optional] Run command "mvn -version" to verify Apache Maven is set up correctly
REM 5. Navigate to script folder in the project. Example = cd C:\Users\birin\Desktop\PersonalFinanceTracker\src\script
REM 6. Run the command = .\run_tests.bat

:: Limit variables created in this script to a local scope
setlocal

:: Start of script message
echo *** Start of run all unit test script ***

:: Get path to the script's directory
cd /d %~dp0
echo This script's path is %cd%

:: Change to the directory of your project root where pom.xml is located
cd ..
echo Expecting pom.xml at %cd%

:: Run all unit test using Maven
call mvn test

:: Check if the Java program exited successfully
IF ERRORLEVEL 1 (
    echo Unit test run unsuccessful
    exit /b 1
)

:: End of script message
echo *** End of run all unit test script ***

:: Disable local scope for variables created in this script
endlocal

:: Exit the script with success code
exit /b 0