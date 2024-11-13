# Proyecto02_SpaceCraft

## Compilación y Ejecución del Servidor y Cliente

### Pasos Manuales

1. **Compilar el Servidor**:
    ```sh
    mvn package -Pservidor
    ```

2. **Renombrar el Archivo JAR del Servidor**:
    ```sh
    mv target/Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target/ServidorJuego-1.0-SNAPSHOT.jar
    ```

3. **Compilar el Cliente**:
    ```sh
    mvn package -Pcliente
    ```

4. **Renombrar el Archivo JAR del Cliente**:
    ```sh
    mv target/Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target/ClienteJuego-1.0-SNAPSHOT.jar
    ```

5. **Verificar la Ubicación de los Archivos JAR**:
    ```sh
    ls target
    ```

    Deberías ver algo como esto:
    ```
    ClienteJuego-1.0-SNAPSHOT.jar
    ServidorJuego-1.0-SNAPSHOT.jar
    ```

6. **Ejecutar el Servidor**:
    ```sh
    java -jar target/ServidorJuego-1.0-SNAPSHOT.jar
    ```

7. **Ejecutar el Cliente**:
    ```sh
    java -jar target/ClienteJuego-1.0-SNAPSHOT.jar
    ```

### Scripts de Automatización

#### Script para Windows (`build_and_run.bat`)

```batch
@echo off
REM Compilar el Servidor
mvn package -Pservidor
move target\Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target\ServidorJuego-1.0-SNAPSHOT.jar

REM Compilar el Cliente
mvn package -Pcliente
move target\Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target\ClienteJuego-1.0-SNAPSHOT.jar

REM Verificar la Ubicación de los Archivos JAR
dir target

REM Ejecutar el Servidor
start cmd /k "java -jar target\ServidorJuego-1.0-SNAPSHOT.jar"

REM Ejecutar el Cliente
start cmd /k "java -jar target\ClienteJuego-1.0-SNAPSHOT.jar"
