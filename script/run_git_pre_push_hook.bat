@echo off

REM ### PRE PUSH HOOK ###
REM 1. Should be run before push to remote.
REM 2. Navigate to the script folder in the project.
REM 3. Run the command = .\run_git_pre_push_hook.bat

:: Limit variables created in this script to a local scope
setlocal

:: Start of script message
echo +++ Start of pre push script +++

:: Get path to the script's directory
cd /d %~dp0
echo This script is located in folder %cd%

:: Navigate to project folder
cd ..
echo Expecting project root is located in folder %cd%

:: Navigate to script folder
cd script
echo Expecting run_all_unit_test script is located in folder %cd%

:: Call script to run unit tests
call run_all_unit_test.bat
IF ERRORLEVEL 1 (
    echo ERROR generated by run_all_unit_test script
    exit /b 1
) else (
     echo Execution of run_all_unit_test successful
)

:: Get path to the script's directory
cd /d %~dp0
echo This script is located in folder %cd%

:: Navigate to project folder
cd ..
echo Expecting project root is located in folder %cd%

:: Navigate to script folder
cd script
echo Expecting run_increment_build_number script is located in folder %cd%

:: Call script to increment the build number
call run_increment_build_number.bat
IF ERRORLEVEL 1 (
    echo ERROR generated by run_increment_build_number script
    exit /b 1
) else (
    echo Execution of run_increment_build_number successful
)

:: Get path to the script's directory
cd /d %~dp0
echo This script's is located in folder %cd%

:: Navigate to project folder
cd ..
echo Expecting project root is located in folder %cd%

:: Add the version file to the staging area after build number increment
git add app/common/Version.java
IF ERRORLEVEL 1 (
    echo ERROR generated at git add of Version.java
    exit /b 1
) else (
    echo git add of Version.java successful
)

:: Commit the version file change with a message
git commit -m "chore(tests): increment build number after successful unit tests"
IF ERRORLEVEL 1 (
    echo ERROR generated at git commit message for Version.java
    exit /b 1
) else (
    echo git commit message for Version.java successful
)

:: End of script message
echo --- End of pre push script ---

:: Disable local scope for variables created in this script
endlocal

:: Exit the script with success code
exit /b 0