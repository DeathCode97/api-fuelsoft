# 🔧 API Taller Mecánico

API REST desarrollada con **Java y Spring Boot** para gestionar la lógica de negocio de un taller mecánico: clientes, vehículos, órdenes de servicio, inventario de repuestos y facturación. Está pensada para ser consumida por un front-end en **Angular** y persiste su información en **PostgreSQL**.

## ✨ Características

- Gestión de **clientes** (alta, edición, baja y consulta).
- Registro de **vehículos** asociados a cada cliente.
- Creación y seguimiento de **órdenes de servicio** con estados (`PENDIENTE`, `EN_PROCESO`, `FINALIZADA`, `ENTREGADA`, `CANCELADA`).
- Asignación de **mecánicos** a las órdenes.
- Control de **inventario de repuestos** y descuento de stock al usarse en una orden.
- Catálogo de **servicios** con precios.
- Cálculo de **costos y facturación** por orden (mano de obra + repuestos).
- Historial de servicios por vehículo y por cliente.
- Validación de datos y manejo centralizado de errores.

## 🛠 Tecnologías

| Componente        | Tecnología                          |
|-------------------|-------------------------------------|
| Lenguaje          | Java 25                             |
| Framework         | Spring Boot                         |
| Persistencia      | Spring Data JPA / Hibernate         |
| Base de datos     | PostgreSQL                          |
| Seguridad         | Spring Security + JWT *(opcional)*  |
| Build             | Maven *(o Gradle)*                  |
| Pruebas           | JUnit 5 + Mockito                   |
| Front-end         | Angular (proyecto aparte)           |

- **Controller**: expone los endpoints REST y recibe/devuelve DTOs.
- **Service**: contiene la lógica de negocio.
- **Repository**: acceso a datos con Spring Data JPA.
- **Entity**: modelo de dominio mapeado a tablas.
- **DTO / Mapper**: desacopla el modelo interno de lo que se expone al front.
- **Exception handler**: respuestas de error consistentes con `@RestControllerAdvice`.

