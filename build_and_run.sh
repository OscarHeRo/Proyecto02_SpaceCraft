#!/bin/bash

# Compilar el Servidor
mvn package -Pservidor
mv target/Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target/ServidorJuego-1.0-SNAPSHOT.jar

# Compilar el Cliente
mvn package -Pcliente
mv target/Proyecto02_SpaceCraft-1.0-SNAPSHOT.jar target/ClienteJuego-1.0-SNAPSHOT.jar

# Verificar la Ubicación de los Archivos JAR
ls target

# Ejecutar el Servidor
gnome-terminal -- bash -c "java -jar target/ServidorJuego-1.0-SNAPSHOT.jar; exec bash"

# Ejecutar el Cliente
gnome-terminal -- bash -c "java -jar target/ClienteJuego-1.0-SNAPSHOT.jar; exec bash"