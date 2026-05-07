# Proyecto Semestral: [Sistema de Gestión de Biblioteca]

## Integrante
- [Bastian Mendoza]

## Estado del Sistema (Hito 1.5)

| Microservicio           | Puerto | DB Name              | Funcionalidad                          |
| :---------------------- | :----- | :------------------- | :------------------------------------- |
| Libro-Service           | 8080   | libros_db            | CRUD de libros y catálogo              |
| Inventario-Service      | 8081   | inventario_db        | Control de stock y ubicación libros    |
| Prestamo-Service        | 8082   | prestamos_db         | Gestión de préstamos y devoluciones    |
| Usuario-Service         | 8083   | usuarios_db          | CRUD de usuarios biblioteca            |
| Auth-Service            | 8084   | auth_db              | Registro de usuarios y login           |

## Despliegue Técnico

- **Instancia:** AWS EC2 t3.large (Ubuntu 24.04)
- **Contenedores:** Docker + Docker Compose
- **Comando de inicio:** `docker compose up -d`
- **IDE utilizado:** Visual Studio Code Remote SSH
- **Backend:** Spring Boot + Maven + Java 21
- **Base de Datos:** MySQL 8.0

## Arquitectura

El sistema fue desarrollado bajo arquitectura de microservicios, donde cada módulo funciona de forma independiente, permitiendo escalabilidad, mantenimiento modular y mejor organización del proyecto.

## Repositorio Maestro

- [https://github.com/Bastian-cloud/biblioteca-fullstack-final.git]