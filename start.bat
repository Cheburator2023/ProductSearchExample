@echo off
REM Start the backend
echo Starting backend...
start cmd /k "cd /d %~dp0 && gradlew bootRun"

REM Wait for backend to start
timeout /t 10

REM Start the frontend
echo Starting frontend...
start cmd /k "cd /d %~dp0\src\main\frontend && npm install && npm start"

echo Application started. Backend is running on http://localhost:8080 and frontend is running on http://localhost:3000
pause