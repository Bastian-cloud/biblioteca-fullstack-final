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

## Diagrama de dependencias
                                     ┌───────────┐
                                     │   Auth    │
                                     └─────▲─────┘
                                           │
 ┌───────────┐     ┌───────────┐     ┌─────┴─────┐
 │   Multa   │────▶│  Prestamo │───▶│  Usuario  │
 └───────────┘     └─────┬──┬──┘     └───────────┘
              ┌──────────┘  └──────────┐
              ▼                        ▼
        ┌───────────┐           ┌───────────┐
        │   Libro   │           │Inventario │
        └───────────┘           └───────────┘


## Tabla de contratos
| Servicio Origen   | Servicio Destino   | Método | Endpoint                  | Datos esperados (DTO)      |
| :---------------- | :----------------- | :------| :------------------------ | :------------------------- |
| Prestamo-Service  | Usuario-Service    | GET    | /api/usuarios/{id}        | id, nombre, correo         |
| Prestamo-Service  | Libro-Service      | GET    | /api/libros/{id}          | id, titulo, autor          |
| Prestamo-Service  | Inventario-Service | GET    | /api/inventario/{id}      | idLibro, stock, disponible |
| Usuario-Service   | Auth-Service       | POST   | /api/auth/login           | username, password         |

# Biblioteca FullStack Final

## Integrante
- Bastian Mendoza

## Estado del Sistema (Hito 1.5)

| Microservicio | Puerto | DB Name | Funcionalidad |
|---|---:|---|---|
| Libro-Service | 8080 | libros_db | CRUD de libros y catálogo |
| Inventario-Service | 8081 | inventario_db | Control de stock y ubicación de libros |
| Prestamo-Service | 8082 | prestamos_db | Gestión de préstamos y devoluciones |
| Usuario-Service | 8083 | usuarios_db | CRUD de usuarios biblioteca |
| Auth-Service | 8084 | auth_db | Registro de usuarios y login |

---

## Despliegue Técnico

- Instancia: AWS EC2 t3.large (Ubuntu 24.04)
- Contenedores: Docker + Docker Compose
- Comando de inicio:

```bash
docker compose up -d
```

- IDE utilizado: Visual Studio Code Remote SSH
- Backend: Spring Boot + Maven + Java 21
- Base de Datos: MySQL 8.0

---

## Arquitectura

El sistema fue desarrollado bajo una arquitectura de microservicios, donde cada módulo funciona de forma independiente, permitiendo escalabilidad, mantenimiento modular y mejor organización del proyecto.

---

## Repositorio Maestro

https://github.com/Bastian-cloud/biblioteca-fullstack-final

---

# Comunicación entre microservicios (Hito 2)

## Diagrama de dependencias

[Insertar imagen del diagrama con Libro-Service, Inventario-Service, Prestamo-Service, Usuario-Service y Auth-Service]

Ejemplo de flujo:

Usuario → Prestamo-Service → Libro-Service → Inventario-Service

Descripción:

- Usuario realiza solicitud de préstamo
- Prestamo-Service consulta Libro-Service para validar existencia del libro
- Prestamo-Service consulta Inventario-Service para validar stock disponible
- Si existe disponibilidad, se registra el préstamo

---

## Tabla de contratos

| Origen | Destino | Método | Endpoint | DTO |
|------------------|--------------------|------|------------------------|---------------|
| Prestamo-Service | Libro-Service      | GET  | /libros/{id}           | LibroDTO      |
| Prestamo-Service | Inventario-Service | GET  | /inventario/libro/{id} | InventarioDTO |
| Prestamo-Service | Usuario-Service    | GET  | /usuarios/{id}         | UsuarioDTO    |
| Auth-Service     | Usuario-Service    | POST | /usuarios              | UsuarioDTO    |
| Usuario-Service  | Auth-Service       | POST | /login                 | LoginDTO      |

---

## Tecnología utilizada

- Cliente REST: **Feign Client**

Justificación:

Feing Client permite que los microservicios se comuniquen entre sí de una forma más simple y ordenada. En lugar de escribir manualmente código para crear solicitudes HTTP, manejar conexiones o procesar respuestas, se pueden definir interfaces que representan los servicios externos que se quieren consumir. De esta manera, el desarrollador solo necesita indicar qué recurso desea obtener y Feign se encarga automáticamente de realizar la comunicación.

Esto ayuda a reducir la cantidad de código repetitivo, hace que el proyecto sea más fácil de mantener y mejora la legibilidad del sistema. Además, facilita el trabajo cuando la aplicación está compuesta por varios microservicios, ya que permite integrar distintos servicios de manera más rápida y con una estructura más clara.

- Manejo de errores:
    - `@ControllerAdvice`
    - Excepciones personalizadas (`ResourceNotFoundException`, `StockInsuficienteException`, etc.)

- Logs:
    - SLF4J para registrar solicitudes y respuestas entre servicios

- Pruebas de integración:
    - Colección Postman ubicada en:

```text
/postman/hito2-integracion.json
```

---

## Escenario de despliegue

### ☑ Escenario A — Todos los servicios en una sola instancia EC2

Todos los microservicios se encuentran desplegados mediante Docker Compose en una única instancia AWS EC2.

Puertos utilizados:

|      Servicio      |Puerto|
|--------------------|------|
| Libro-Service      | 8080 |
| Inventario-Service | 8081 |
| Prestamo-Service   | 8082 |
| Usuario-Service    | 8083 |
| Auth-Service       | 8084 |
| Multa-Service      | 8085 |
| MySQL              | 3306 |

Security Groups configurados:

- Puerto 22 → SSH
- Puerto 3306 → MySQL
- Puerto 8080 → Libro-Service
- Puerto 8081 → Inventario-Service
- Puerto 8082 → Prestamo-Service
- Puerto 8083 → Usuario-Service
- Puerto 8084 → Auth-Service
- Puerto 8085 → Multa-Service


## Cómo probar la integración

### 1. Levantar todos los servicios

```bash
docker compose up -d
```

### 2. Verificar que los contenedores estén activos

```bash
docker ps
```

### 3. Importar colección Postman

Importar:

```text
/postman/hito2-integracion.json
```

### 4. Probar flujo de préstamo

Realizar una solicitud POST:

```http
POST http://52.73.191.33:8082/prestamo
```

Body:

```json
{
  "libroId": 2,
  "usuarioId": 1,
  "fechaPrestamo": "2025-05-08",
  "fechaDevolucion": "2025-05-15",
  "estado": "ACTIVO"
}
```

### 5. Probar resiliencia

Detener un servicio:

```bash
docker stop libro-app
```

Reintentar la solicitud desde Postman y verificar el manejo de errores.

