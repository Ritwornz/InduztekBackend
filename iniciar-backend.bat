@echo off
chcp 65001 > nul
title Induztek - Orquestador de Backend
color 0B

echo =========================================================
echo    INDUZTEK - INICIANDO INFRAESTRUCTURA Y SERVICIOS
echo =========================================================
echo.

:: 1. Verificar si Docker Desktop esta corriendo
docker info >nul 2>&1
if %errorlevel% neq 0 (
    color 0C
    echo [ERROR] Docker Desktop no esta abierto.
    echo Por favor inicia Docker Desktop y vuelve a ejecutar este archivo.
    pause
    exit /b 1
)

:: 2. Paso 1: Iniciar MySQL y Eureka Server
echo [1/3] Levantando Base de Datos (MySQL) y Eureka Server...
docker compose up -d induztek-mysql eureka-server

echo.
echo Esperando 12 segundos a que Eureka y MySQL esten listos...
timeout /t 12 /nobreak > nul

:: 3. Paso 2: Iniciar Microservicios y Gateway
echo.
echo [2/3] Levantando Microservicios y API Gateway...
docker compose up -d ms-usuarios ms-protocolos ms-mediciones api-gateway

echo.
echo Esperando 10 segundos a que los servicios se registren en Eureka...
timeout /t 10 /nobreak > nul

:: 4. Paso 3: Estado de los contenedores
echo.
echo [3/3] Estado actual de los contenedores:
echo ---------------------------------------------------------
docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
echo ---------------------------------------------------------

:: 5. Abrir Eureka en el navegador
echo.
echo Abriendo Eureka Dashboard en tu navegador...
start http://localhost:8761

echo.
echo =========================================================
echo    BACKEND INICIADO CON EXITO
echo    - Eureka:     http://localhost:8761
echo    - Gateway:    http://localhost:8081
echo    - Usuarios:   http://localhost:8081/api/usuarios
echo    - Protocolos: http://localhost:8081/api/protocolos
echo    - Mediciones: http://localhost:8081/api/mediciones
echo =========================================================
echo.
pause
