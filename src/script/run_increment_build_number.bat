REM ### TO INCREMENT BUILD NUMBER ###
REM 1. Should be run after unit test execution is successful.
REM 2. Navigate to script folder in the project.
REM 3. Run the command = .\run_inc_build_number.bat

@echo off

:: Get path to the script's directory
cd /d %~dp0

:: Navigate to project folder
cd ../..

:: Navigate to classpath (target\classes) folder for IncrementBuild.class
cd target\classes

:: Run the IncrementBuild class using the package path
:: Since IncrementBuild is in the 'script' package, use: script/IncrementBuild
java -cp "%cd%" script.IncrementBuild

:: Check if the Java program exited successfully
IF ERRORLEVEL 1 (
    echo "Failure at increment build number, aborting."
    exit /b 1
)

REM Exit the script
exit /B