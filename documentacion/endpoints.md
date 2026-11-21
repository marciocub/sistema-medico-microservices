# Endpoints Principales

Esta es una referencia general de los endpoints expuestos por cada microservicio. Los paths exactos dependen de los controladores implementados en cada servicio.

## Actuator (Salud e información)

Todos los microservicios expenden los endpoints de Spring Boot Actuator:

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| `/actuator/health` | GET | Estado de salud del servicio |
| `/actuator/info` | GET | Información de la aplicación |

## Orquestador (Puerto 8080)

Coordinador de llamadas entre microservicios. Consulta las URLs de los demás servicios definidas en `application.yml`:

- Seguridad: `http://localhost:8081`
- Pacientes: `http://localhost:8082`
- Profesionales: `http://localhost:8083`
- Turnos: `http://localhost:8084`

## Seguridad (Puerto 8081)

Autenticación y autorización basada en JWT.

Endpoints esperados (según implementación):
- `POST /auth/login` - Iniciar sesión y obtener token JWT
- `POST /auth/register` - Registrar nuevo usuario
- `GET /usuarios/{id}` - Obtener usuario por ID

## Pacientes (Puerto 8082)

Gestión de pacientes.

Endpoints esperados:
- `GET /pacientes` - Listar pacientes
- `POST /pacientes` - Crear paciente
- `GET /pacientes/{id}` - Obtener paciente por ID
- `PUT /pacientes/{id}` - Actualizar paciente
- `DELETE /pacientes/{id}` - Eliminar paciente

## Profesionales (Puerto 8083)

Gestión de profesionales médicos y especialidades.

Endpoints esperados:
- `GET /profesionales` - Listar profesionales
- `POST /profesionales` - Crear profesional
- `GET /profesionales/{id}` - Obtener profesional por ID
- `GET /especialidades` - Listar especialidades

## Turnos (Puerto 8084)

Gestión de turnos médicos.

Endpoints esperados:
- `GET /turnos` - Listar turnos
- `POST /turnos` - Crear turno
- `GET /turnos/{id}` - Obtener turno por ID
- `PUT /turnos/{id}` - Actualizar turno
- `DELETE /turnos/{id}` - Cancelar turno

## Frontend (Puerto 3000)

Interfaz de usuario en React + Vite servida por Nginx.

URL: `http://localhost:3000`

> Nota: Los paths exactos de los endpoints REST dependen de los `@RestController` y `@RequestMapping` implementados en cada microservicio. Revisa el código fuente en `src/main/java/com/sistema/...` para conocer los endpoints precisos.
