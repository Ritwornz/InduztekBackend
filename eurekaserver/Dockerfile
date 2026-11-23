# ============================================================
# Dockerfile - eureka-server
# ============================================================
# Un Dockerfile describe COMO construir la imagen Docker
# de este microservicio. Docker ejecuta estas instrucciones
# de arriba hacia abajo, capa por capa.
#
# ESTRATEGIA MULTI-STAGE BUILD:
#   Stage 1 (build): compila el proyecto con Maven + JDK
#   Stage 2 (runtime): solo el JRE + el .jar resultante
# Resultado: imagen final mucho mas liviana (sin Maven, sin sources)
#
# Los Dockerfiles de ms-productos, ms-clientes y apigateway siguen
# este MISMO patron; aqui esta la explicacion detallada de cada linea.
#
# ------------------------------------------------------------
# PARA INVESTIGAR: OTRAS FORMAS DE HACERLO (opcional)
# ------------------------------------------------------------
#  1. Usar una imagen que ya trae Maven:
#       FROM maven:3.9-eclipse-temurin-21-alpine AS build
#     Asi no hace falta la linea "RUN apk add --no-cache maven"
#     y la version de Maven queda fija (build reproducible).
#
#  2. Usar el Maven Wrapper del proyecto (mvnw):
#     Copiar mvnw y la carpeta .mvn y ejecutar "./mvnw package".
#     Usa exactamente la version de Maven definida en el proyecto.
#     (Si editas en Windows, ojo con los saltos de linea CRLF en mvnw)
#
#  3. Archivo .dockerignore:
#     Crear un archivo ".dockerignore" junto al Dockerfile con:
#         target/
#         .vscode/
#         .idea/
#         *.md
#     Evita enviar archivos innecesarios a Docker al construir.
#
#  4. No ejecutar como root (seguridad):
#     En el STAGE 2, antes de ENTRYPOINT, se puede crear un usuario:
#         RUN addgroup -S spring && adduser -S spring -G spring
#         USER spring
#     Si alguien vulnera la aplicacion, no tendria permisos de root
#     dentro del contenedor.
# ============================================================

# -------------------------------------------------------
# STAGE 1: BUILD - Compilar el proyecto con Maven
# -------------------------------------------------------
# "AS build" le da nombre a esta etapa para referenciarla luego
# eclipse-temurin:21-jdk-alpine = JDK 21 en Alpine Linux (liviano)
FROM eclipse-temurin:21-jdk-alpine AS build

# NUEVO: Alpine no incluye Maven, se instala con su gestor de paquetes apk
# (Alternativa: usar la imagen maven:3.9-eclipse-temurin-21-alpine,
#  ver "PARA INVESTIGAR" arriba)
RUN apk add --no-cache maven

# WORKDIR: directorio de trabajo dentro del contenedor.
# Equivale a hacer "mkdir /app && cd /app"
WORKDIR /app

# Primero copiamos SOLO el pom.xml para aprovechar el cache de Docker.
# Docker cachea cada capa: si pom.xml no cambia, no re-descarga dependencias.
# Esto acelera enormemente las re-compilaciones durante desarrollo.
COPY pom.xml .

# Descarga todas las dependencias Maven (sin compilar aun).
# La bandera -B = batch mode (sin colores, ideal para CI/CD)
# La bandera -q = quiet (menos verbosidad)
RUN mvn dependency:go-offline -B -q

# Ahora copiamos el codigo fuente. Si solo cambia el codigo
# (no el pom.xml), Docker usa el cache de la capa anterior.
COPY src ./src

# Compilar y empaquetar. -DskipTests omite los tests para
# acelerar el build de la imagen (los tests se corren en CI/CD aparte)
RUN mvn package -DskipTests -B -q

# -------------------------------------------------------
# STAGE 2: RUNTIME - Imagen final liviana
# -------------------------------------------------------
# eclipse-temurin:21-jre-alpine = solo el JRE (no JDK completo)
# Alpine Linux es ~5MB. La imagen final queda bastante mas liviana que
# una con JDK completo + Maven (compruebalo con "docker images").
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar SOLO el .jar desde el stage de build.
# El codigo fuente, Maven, y las dependencias de compilacion NO se incluyen.
# La sintaxis --from=build referencia el stage 1 por su nombre.
COPY --from=build /app/target/*.jar app.jar

# EXPOSE documenta que el contenedor usa este puerto.
# No abre el puerto por si solo; docker-compose lo mapea.
EXPOSE 8761

# ENTRYPOINT: el comando que se ejecuta al iniciar el contenedor.
# "java -jar app.jar" arranca la aplicacion Spring Boot.
ENTRYPOINT ["java", "-jar", "app.jar"]
