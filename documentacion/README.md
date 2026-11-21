# Documentación del Backend - Sistema Médico Microservicios

Este documento resume la arquitectura, servicios, tecnologías y comandos para levantar la aplicación backend tanto de forma **nativa** como con **Docker**.

## Estructura del Proyecto

```
sistema-medico-microservices/
├── docker-compose.yml
├── documentacion/
│   ├── README.md
│   ├── arquitectura.md
│   ├── levantar-nativo.md
│   ├── levantar-docker.md
│   ├── variables-entorno.md
│   └── endpoints.md
├── microservice-orquestador/     # Puerto 8080
├── microservice-seguridad/       # Puerto 8081
├── microservice-pacientes/       # Puerto 8082
├── microservice-profesionales/   # Puerto 8083
└── microservice-turnos/          # Puerto 8084
```

## Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java | 21 | Lenguaje de programación |
| Spring Boot | 3.3.4 | Framework backend |
| Spring Data JPA | - | Persistencia de datos |
| MySQL | 8.0 | Base de datos relacional |
| Maven | 3.9.6 | Gestión de dependencias y build |
| JWT (jjwt) | 0.12.6 | Autenticación y autorización (seguridad) |
| Lombok | 1.18.36 | Reducción de código boilerplate |
| Docker | - | Contenerización |
| Nginx | - | Servidor web para el frontend |

## Servicios del Backend

| Servicio | Puerto | Descripción | Base de Datos |
|----------|--------|-------------|---------------|
| Orquestador | 8080 | Coordina llamadas entre microservicios | No usa BD directa |
| Seguridad | 8081 | Autenticación, autorización y JWT | `sistema_turnos` |
| Pacientes | 8082 | Gestión de pacientes | `sistema_turnos` |
| Profesionales | 8083 | Gestión de profesionales y especialidades | `sistema_turnos` |
| Turnos | 8084 | Gestión de turnos médicos | `sistema_turnos` |

## Requisitos Previos (Nativo)

- **JDK 21** instalado
- **Maven 3.9+** instalado
- **MySQL 8.0** instalado y en ejecución
- Base de datos `sistema_turnos` creada en MySQL

## Documentación Relacionada

- [Arquitectura del sistema](arquitectura.md)
- [Levantar de forma nativa](levantar-nativo.md)
- [Levantar con Docker](levantar-docker.md)
- [Variables de entorno](variables-entorno.md)
- [Endpoints principales](endpoints.md)
