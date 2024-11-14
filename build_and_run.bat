@echo off
REM Limpiar y Compilar el Servidor
echo ===============================
echo Compilando el Servidor...
echo ===============================
call mvn clean package -Pservidor -DskipTests
if %errorlevel% neq 0 (
    echo Error al compilar el servidor. Saliendo...
    exit /b %errorlevel%
)
echo Renombrando el JAR del servidor...
move /Y target\ServidorJuego-1.0-SNAPSHOT.jar target\ServidorJuego.jar

REM Compilar el Cliente (sin limpiar)
echo ===============================
echo Compilando el Cliente...
echo ===============================
call mvn package -Pcliente -DskipTests
if %errorlevel% neq 0 (
    echo Error al compilar el cliente. Saliendo...
    exit /b %errorlevel%
)
echo Renombrando el JAR del cliente...
move /Y target\ClienteJuego-1.0-SNAPSHOT.jar target\ClienteJuego.jar

REM Verificar la Ubicación de los Archivos JAR
echo.
echo ===============================
echo Los archivos JAR generados se encuentran en el directorio 'target':
dir target

echo.
echo ---------------------------------------------
echo Para ejecutar el servidor, usa:
echo java -jar target\ServidorJuego.jar
echo.
echo Para ejecutar el cliente, usa:
echo java -jar target\ClienteJuego.jar
