# Variables de Entorno

Los microservicios Spring Boot leen las siguientes variables de entorno. Si no están definidas, se usan los valores por defecto mostrados a continuación.

## Variables Comunes (Todos los servicios que usan BD)

| Variable | Valor por defecto | Descripción |
|----------|-------------------|-------------|
| `DB_HOST` | `localhost` (nativo) / `mysql` (Docker) | Host de la base de datos MySQL |
| `DB_PORT` | `3306` | Puerto de MySQL |
| `DB_NAME` | `sistema_turnos` | Nombre de la base de datos |
| `DB_USER` | `root` | Usuario de MySQL |
| `DB_PASSWORD` | `root` | Contraseña de MySQL |

## Variables del Servicio de Seguridad

| Variable | Valor por defecto | Descripción |
|----------|-------------------|-------------|
| `JWT_SECRET` | `cambiame-en-produccion-minimo-256-bits-para-hs256-algoritmo` | Secreto para firmar tokens JWT (mínimo 256 bits) |
| `CORS_ORIGINS` | `http://localhost:5173,http://localhost:3000` | Orígenes permitidos para CORS |

## Ejemplo de archivo .env

Crea un archivo `.env` en la raíz del proyecto (`sistema-medico-microservices/.env`):

```env
DB_PASSWORD=mi_password_seguro
DB_USER=medico
JWT_SECRET=mi-secreto-jwt-de-almenos-256-bits-para-produccion
CORS_ORIGINS=http://localhost:5173,http://localhost:3000
```

## Cómo usar las variables

### En ejecución nativa (PowerShell)

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="sistema_turnos"
$env:DB_USER="root"
$env:DB_PASSWORD="root"
$env:JWT_SECRET="mi-secreto-seguro"
java -jar target/*.jar
```

### En Docker Compose

Las variables se inyectan automáticamente desde el archivo `.env` o desde el entorno del sistema, tal como está definido en `docker-compose.yml`.
