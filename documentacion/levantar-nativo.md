# Levantar la Aplicación de Forma Nativa (Sin Docker)

Esta guía explica cómo ejecutar el backend y la base de datos directamente en tu máquina, sin usar contenedores.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

| Herramienta | Versión mínima | Comando para verificar |
|-------------|----------------|------------------------|
| Java (JDK) | 21 | `java -version` |
| Maven | 3.9+ | `mvn -version` |
| MySQL Server | 8.0 | `mysql --version` |

## Paso 1: Configurar MySQL

1. Inicia el servicio de MySQL.
2. Crea la base de datos:

```sql
CREATE DATABASE sistema_turnos;
CREATE USER 'medico'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON sistema_turnos.* TO 'medico'@'localhost';
FLUSH PRIVILEGES;
```

> Por defecto los microservicios se conectan como usuario `root` con contraseña `root`, pero puedes cambiarlo con variables de entorno.

## Paso 2: Compilar cada microservicio

Desde la raíz del proyecto (`sistema-medico-microservices`), entra a cada carpeta y compila:

```powershell
# Orquestador
cd microservice-orquestador
mvn clean package -DskipTests

# Seguridad
cd ../microservice-seguridad
mvn clean package -DskipTests

# Pacientes
cd ../microservice-pacientes
mvn clean package -DskipTests

# Profesionales
cd ../microservice-profesionales
mvn clean package -DskipTests

# Turnos
cd ../microservice-turnos
mvn clean package -DskipTests
```

Esto generará los archivos `.jar` en cada carpeta `target/`.

## Paso 3: Ejecutar cada microservicio

Abre una terminal separada para cada servicio y ejecuta:

```powershell
# Orquestador (puerto 8080)
cd microservice-orquestador
java -jar target/*.jar

# Seguridad (puerto 8081)
cd microservice-seguridad
java -jar target/*.jar

# Pacientes (puerto 8082)
cd microservice-pacientes
java -jar target/*.jar

# Profesionales (puerto 8083)
cd microservice-profesionales
java -jar target/*.jar

# Turnos (puerto 8084)
cd microservice-turnos
java -jar target/*.jar
```

## Paso 4: Ejecutar con variables de entorno (opcional)

Puedes sobrescribir la configuración por defecto exportando variables antes de iniciar cada servicio:

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="sistema_turnos"
$env:DB_USER="root"
$env:DB_PASSWORD="root"
$env:JWT_SECRET="tu-secreto-jwt-de-almenos-256-bits"
```

## Paso 5: Verificar que los servicios estén arriba

Cada microservicio expone un endpoint de salud:

```powershell
# Orquestador
Invoke-RestMethod http://localhost:8080/actuator/health

# Seguridad
Invoke-RestMethod http://localhost:8081/actuator/health

# Pacientes
Invoke-RestMethod http://localhost:8082/actuator/health

# Profesionales
Invoke-RestMethod http://localhost:8083/actuator/health

# Turnos
Invoke-RestMethod http://localhost:8084/actuator/health
```

Debes recibir un JSON con `"status": "UP"`.

## Resumen de Puertos Nativos

| Servicio | Puerto | URL de salud |
|----------|--------|--------------|
| Orquestador | 8080 | `http://localhost:8080/actuator/health` |
| Seguridad | 8081 | `http://localhost:8081/actuator/health` |
| Pacientes | 8082 | `http://localhost:8082/actuator/health` |
| Profesionales | 8083 | `http://localhost:8083/actuator/health` |
| Turnos | 8084 | `http://localhost:8084/actuator/health` |
| MySQL | 3306 | `jdbc:mysql://localhost:3306/sistema_turnos` |

## Detener los servicios

Presiona `Ctrl + C` en cada terminal donde esté ejecutándose un microservicio, o detén el servicio de MySQL si ya no lo necesitas.
