# Arquitectura del Sistema

## Diagrama de Servicios

```
+---------------------+      +---------------------+      +---------------------+
|   Frontend (React)  |      |   Nginx (Proxy)     |      |   MySQL 8.0         |
|   Puerto 3000       |      |   Puerto 80         |      |   Puerto 3306       |
+---------------------+      +---------------------+      +---------------------+
         |                           |                           ^
         v                           |                           |
+---------------------+      +---------------------+      +---------------------+
| Orquestador 8080    | ---> | Seguridad 8081      | ---> | sistema_turnos      |
|                     |      | Pacientes 8082      |      |                     |
+---------------------+      | Profesionales 8083  |      +---------------------+
                             | Turnos 8084         |
                             +---------------------+
```

## Flujo de Comunicación

1. **Frontend** se comunica con los microservicios a través de sus respectivos puertos (8080-8084).
2. **Orquestador (8080)** conoce las URLs de los otros servicios:
   - Seguridad: `http://localhost:8081`
   - Pacientes: `http://localhost:8082`
   - Profesionales: `http://localhost:8083`
   - Turnos: `http://localhost:8084`
3. **Seguridad (8081)** gestiona autenticación y emite tokens JWT.
4. **Pacientes (8082)**, **Profesionales (8083)** y **Turnos (8084)** acceden a la base de datos compartida `sistema_turnos` en MySQL 8.0.

## Red Docker

Cuando se usa Docker Compose, todos los servicios están en la red bridge `sistema-medico-network` y se resuelven por nombre de contenedor (ej. `mysql`, `microservice-seguridad`, etc.).

## Volúmenes

- `mysql_data`: Persistencia de datos de MySQL en `/var/lib/mysql`.
