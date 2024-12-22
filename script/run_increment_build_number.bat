@echo off

REM ### TO INCREMENT BUILD NUMBER ###
REM 1. Should be run after unit test execution is successful.
REM 2. Navigate to script folder in the project.
REM 3. Run the command = .\run_inc_build_number.bat

:: Limit variables created in this script to a local scope
setlocal

:: Start of script message
echo *** Start of increment build number script ***

:: Get path to the script's directory
cd /d %~dp0
echo This script's is located in folder %cd%

:: Navigate to project folder
cd ..
echo Expecting project folder is located at %cd%

:: Navigate to classpath (target\classes) folder for IncrementBuild.class
cd target\classes
echo Expecting IncrementBuild.class is located in folder %cd%

:: Run the IncrementBuild class using the package path
:: Since IncrementBuild is in the 'script' package, use: script/IncrementBuild
java -cp "%cd%" script.IncrementBuild

:: Check if the Java program exited successfully
IF ERRORLEVEL 1 (
    echo ERROR detect
    exit /b 1
) else (
    echo Execution of IncrementBuild.class successful
)

:: End of script message
echo *** End of increment build number script ***

:: Disable local scope for variables created in this script
endlocal

:: Exit the script with success code
exit /b 0