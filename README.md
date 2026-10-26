<div align="center">

# 🚁 FlotaDeDrones

**Back end de un sistema de entrega con drones**

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

</div>

---

## 📖 Descripción

Este proyecto es el back end de un sistema de gestión de entregas mediante drones, desarrollado como **trabajo académico**. Los clientes realizan pedidos que el sistema asigna a drones disponibles, validando el peso del pedido contra la capacidad máxima del dron y su nivel de batería. Cada dron reporta su telemetría (ubicación y batería) durante el vuelo, y cada asignación genera una ruta asociada al pedido.

> Alcance: solo back end (API REST). Las pruebas se realizan con Postman.

## 👥 Integrantes

| Nombre | Rol / Módulos a cargo |
|--------|-----------------------|
| [Nombre 1] | Cliente, Pedido, consulta de pedido |
| [Nombre 2] | Dron, Telemetría, asignación y Ruta |

**Asignatura:** [Nombre del curso] · **Profesor:** [Nombre] · **Universidad:** [Nombre]

## 🛠️ Tecnologías

| Tecnología | Uso |
|------------|-----|
| Java 25 | Lenguaje principal |
| Spring Boot | Framework para la API REST |
| Spring Data JPA | Acceso a datos y mapeo de entidades |
| PostgreSQL | Base de datos relacional |
| Maven | Gestión de dependencias y build |
| Postman | Pruebas de los endpoints |
| IntelliJ IDEA | Entorno de desarrollo |

## 🧩 Diagrama UML

![Diagrama UML](docs/diagrama-uml.png)

### Entidades y relaciones

| Relación | Tipo |
|----------|------|
| Telemetría → Dron | Muchos a uno |
| Ruta → Dron | Muchos a uno |
| Ruta → Pedido | Muchos a uno |
| Pedido → Cliente | Muchos a uno |

## 📁 Estructura del proyecto

```
FlotaDeDrones/
├── docs/                    # Diagramas e imágenes del informe
├── FlotadeDrones/           # Proyecto Spring Boot
│   ├── src/main/java/...
│   │   ├── model/           # Entidades JPA y enums
│   │   ├── repository/      # Interfaces JpaRepository
│   │   ├── service/         # Lógica de negocio
│   │   ├── controller/      # Endpoints REST
│   │   ├── dto/             # Objetos de transferencia
│   │   └── exception/       # Manejo de errores
│   ├── src/main/resources/
│   └── pom.xml
└── README.md
```

## ⚙️ Cómo ejecutar el proyecto

### Requisitos previos

- JDK 25
- Maven
- PostgreSQL

### Pasos

**1. Clonar el repositorio**

```bash
git clone https://github.com/TomIcj/FlotaDeDrones.git
cd FlotaDeDrones/FlotadeDrones
```

**2. Crear la base de datos**

```sql
CREATE DATABASE drones_db;
```

**3. Configurar `src/main/resources/application.properties`**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/drones_db
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Ejecutar la aplicación**

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

## 📡 Endpoints

| Estado | Método | Ruta | Descripción |
|:------:|--------|------|-------------|
| ⬜ | `POST` | `/clientes` | Registrar un cliente |
| ⬜ | `GET` | `/clientes` | Listar clientes |
| ⬜ | `POST` | `/drones` | Registrar un dron |
| ⬜ | `GET` | `/drones` | Listar drones |
| ⬜ | `POST` | `/pedidos` | Crear un pedido (estado inicial `PENDIENTE`) |
| ⬜ | `POST` | `/pedidos/{id}/asignar` | Asignar un dron disponible y generar la ruta |
| ⬜ | `POST` | `/drones/{id}/telemetria` | Registrar telemetría de un dron |
| ⬜ | `GET` | `/pedidos/{id}` | Consultar estado del pedido, dron y ruta |

> ✅ implementado · ⬜ pendiente

## 🧠 Reglas de negocio

- Un pedido se crea en estado `PENDIENTE`.
- Solo se asigna un dron en estado `DISPONIBLE`.
- El peso del pedido no puede superar la `capacidadMaxima` del dron.
- El dron debe tener batería suficiente: [definir mínimo, por ejemplo 30 %].
- Al asignar, el pedido pasa a `EN_CAMINO` y el dron a `EN_VUELO`.

## 📓 Bitácora de avance

### Avance 1 — [fecha]

- **Qué hicimos:** creamos el proyecto base con Spring Boot, el repositorio y el README inicial.
- **Problemas y soluciones:** [completar]
- **Qué sigue:** conectar la aplicación a PostgreSQL y crear la entidad `Cliente`.

<!-- Copien este bloque para cada nuevo avance -->

## 📸 Pruebas en Postman

*(Agregar aquí las capturas de cada funcionalidad a medida que se implemente)*

## ✅ Estado del proyecto

- [x] Repositorio y proyecto base
- [ ] Conexión a PostgreSQL
- [ ] Entidades JPA
- [ ] CRUD de Cliente, Dron y Pedido
- [ ] Asignación de dron y generación de ruta
- [ ] Recepción de telemetría
- [ ] Colección de Postman final
- [ ] Presentación
