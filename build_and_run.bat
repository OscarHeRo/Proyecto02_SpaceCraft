@echo off
REM Limpia compilaciones previas solo una vez
echo Limpiando compilaciones anteriores...
call mvn clean
if %errorlevel% neq 0 (
    echo Error al limpiar el proyecto. Saliendo...
    exit /b %errorlevel%
)

REM Compilar el Servidor
echo ===============================
echo Compilando el Servidor...
echo ===============================
call mvn package -Pservidor -DskipTests
if %errorlevel% neq 0 (
    echo Error al compilar el servidor. Saliendo...
    exit /b %errorlevel%
)
echo Moviendo el JAR del servidor...
move /Y target\Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target\ServidorJuego-1.0-SNAPSHOT.jar

REM Compilar el Cliente sin limpiar
echo ===============================
echo Compilando el Cliente...
echo ===============================
call mvn package -Pcliente -DskipTests
if %errorlevel% neq 0 (
    echo Error al compilar el cliente. Saliendo...
    exit /b %errorlevel%
)
echo Moviendo el JAR del cliente...
move /Y target\Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target\ClienteJuego-1.0-SNAPSHOT.jar

REM Verificar la Ubicación de los Archivos JAR
echo.
echo ===============================
echo Los archivos JAR generados se encuentran en el directorio 'target':
dir target

echo.
echo ---------------------------------------------
echo Para ejecutar el servidor, usa:
echo java -jar target\ServidorJuego-1.0-SNAPSHOT.jar
echo.
echo Para ejecutar el cliente, usa:
echo java -jar target\ClienteJuego-1.0-SNAPSHOT.jar
echo ---------------------------------------------
pause
