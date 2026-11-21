# Levantar la Aplicación con Docker y Docker Compose

Esta guía explica cómo levantar todo el backend (MySQL + 5 microservicios) y el frontend usando Docker Compose.

## Requisitos Previos

- **Docker Desktop** instalado y en ejecución en Windows.
- **Docker Compose** (incluido en Docker Desktop).

## Estructura del docker-compose.yml

El archivo `docker-compose.yml` en la raíz define los siguientes servicios:

| Servicio | Imagen / Build | Puerto | Dependencias |
|----------|---------------|--------|--------------|
| mysql | `mysql:8.0` | 3306 | - |
| microservice-orquestador | `./microservice-orquestador/Dockerfile` | 8080 | mysql |
| microservice-seguridad | `./microservice-seguridad/Dockerfile` | 8081 | mysql |
| microservice-pacientes | `./microservice-pacientes/Dockerfile` | 8082 | mysql |
| microservice-profesionales | `./microservice-profesionales/Dockerfile` | 8083 | mysql |
| microservice-turnos | `./microservice-turnos/Dockerfile` | 8084 | mysql |
| frontend | `../sistema-medico-frontend/Dockerfile` | 3000 | todos los microservicios |

> El frontend se busca en la ruta relativa `../sistema-medico-frontend` (fuera de este repositorio).

## Paso 1: Crear archivo de variables de entorno (.env) (opcional)

Crea un archivo `.env` en la raíz del proyecto para personalizar credenciales:

```env
DB_PASSWORD=root
DB_USER=medico
JWT_SECRET=cambiame-en-produccion-minimo-256-bits-para-hs256-algoritmo
```

Si no existe, se usarán los valores por defecto definidos en el `docker-compose.yml`.

## Paso 2: Levantar todos los servicios

Desde la carpeta `sistema-medico-microservices`, ejecuta:

```powershell
docker compose up -d --build
```

Esto:
1. Construye las imágenes de los 5 microservicios y el frontend.
2. Inicia MySQL 8.0 con el volumen persistente `mysql_data`.
3. Deja todos los contenedores ejecutándose en segundo plano.

## Paso 3: Verificar estado de los contenedores

```powershell
docker compose ps
```

Deberías ver todos los servicios con estado `running`.

## Paso 4: Probar la aplicación

| Servicio | URL |
|----------|-----|
| Frontend | `http://localhost:3000` |
| Orquestador | `http://localhost:8080/actuator/health` |
| Seguridad | `http://localhost:8081/actuator/health` |
| Pacientes | `http://localhost:8082/actuator/health` |
| Profesionales | `http://localhost:8083/actuator/health` |
| Turnos | `http://localhost:8084/actuator/health` |
| MySQL | `localhost:3306` |

## Paso 5: Ver logs

```powershell
# Logs de todos los servicios
docker compose logs -f

# Logs de un servicio específico
docker compose logs -f microservice-orquestador
docker compose logs -f mysql
```

## Paso 6: Detener y eliminar contenedores

```powershell
# Detener contenedores (no borra volúmenes)
docker compose stop

# Detener y eliminar contenedores y redes (mantiene volúmenes)
docker compose down

# Detener y eliminar todo incluyendo volúmenes (borra datos de MySQL)
docker compose down -v
```

## Comandos Útiles de Docker

```powershell
# Reconstruir y levantar un solo servicio
docker compose up -d --build microservice-seguridad

# Ver logs en tiempo real de un contenedor
docker logs -f microservice-turnos

# Entrar al contenedor de MySQL
docker exec -it sistema-medico-mysql mysql -u root -p

# Ver imágenes construidas
docker images

# Ver contenedores activos
docker ps