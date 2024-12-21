@echo off
REM ### PRE PUSH HOOK ###
REM 1. Should be run before push to remote.
REM 2. Navigate to the script folder in the project.
REM 3. Run the command = .\run_git_pre_push_hook.bat

:: Call script to run unit tests
call run_tests.bat
IF ERRORLEVEL 1 (
    echo "Unit tests failed, aborting."
    exit /b 1
)

:: Call script to increment the build number
call run_increment_build_number.bat
IF ERRORLEVEL 1 (
    echo "Failed to increment build number, aborting."
    exit /b 1
)

:: Add the version file to the staging area after build number increment
git add src/app/common/version.txt
IF ERRORLEVEL 1 (
    echo "Failed to stage changes, aborting."
    exit /b 1
)

:: Commit the version file change with a message
git commit -m "chore(tests): increment build version after successful unit tests"
IF ERRORLEVEL 1 (
    echo "Failed to commit changes, aborting."
    exit /b 1
)

echo "Pre-push hook completed successfully."
