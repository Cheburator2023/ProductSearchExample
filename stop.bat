@echo off
REM Stop the backend
echo Stopping backend...
taskkill /IM "java.exe" /F

REM Stop the frontend
echo Stopping frontend...
taskkill /IM "node.exe" /F

echo Application stopped.
pause