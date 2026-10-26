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

## 🎓 Datos académicos

| | |
|---|---|
| **Universidad** | Universidad del Norte Santo Tomás de Aquino (UNSTA) |
| **Asignatura** | BackEnd |
| **Profesor** | Ing. Tulio |

## 👥 Integrantes

| Nombre | Módulos a cargo |
|--------|-----------------|
| Arevalo Santiago | `Cliente`, `Pedido` y enum `EstadoPedido` |
| Cejas Tomás | `Dron`, `Telemetria`, `Ruta` y enum `EstadoDron` |

## 🛠️ Tecnologías

| Tecnología | Uso |
|------------|-----|
| Java 25 | Lenguaje principal |
| Spring Boot 4.1.2 | Framework para la API REST |
| Spring Data JPA (Hibernate) | Acceso a datos y mapeo de entidades |
| PostgreSQL en Neon | Base de datos relacional alojada en la nube |
| Lombok | Generación de getters, setters y constructores |
| Maven | Gestión de dependencias y build |
| Postman | Pruebas de los endpoints |
| pgAdmin | Consulta y verificación de la base de datos |
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

### Estado de las entidades

| Entidad | Estado | Detalle |
|---------|:------:|---------|
| `Cliente` | ✅ | Tabla `cliente` creada |
| `Pedido` | ✅ | Tabla `pedido`, con clave foránea a `cliente` |
| `EstadoPedido` | ✅ | Enum: `PENDIENTE`, `EN_CAMINO`, `ENTREGADO`, `CANCELADO` |
| `Dron` | ⬜ | Pendiente |
| `Telemetria` | ⬜ | Pendiente |
| `Ruta` | ⬜ | Pendiente |
| `EstadoDron` | ⬜ | Enum pendiente: `DISPONIBLE`, `EN_VUELO`, `MANTENIMIENTO` |

## 📁 Estructura del proyecto

```
FlotaDeDrones/
├── docs/                    # Diagramas e imágenes del informe
├── FlotadeDrones/           # Proyecto Spring Boot
│   ├── src/main/java/com/example/FlotadeDrones/
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

> Por ahora solo existe el paquete `model`; el resto se crea a medida que avanza el proyecto.

## ⚙️ Cómo ejecutar el proyecto

### Requisitos previos

- JDK 25
- Maven
- Credenciales de la base de datos en Neon (se piden a los integrantes por privado, no están en el repositorio)

### Pasos

**1. Clonar el repositorio**

```bash
git clone https://github.com/TomIcj/FlotaDeDrones.git
cd FlotaDeDrones/FlotadeDrones
```

**2. Crear `src/main/resources/application.properties`**

Este archivo está excluido del repositorio (`.gitignore`) porque contiene la contraseña. Se crea a partir de `application.properties.example`:

```properties
spring.application.name=FlotadeDrones

spring.datasource.url=jdbc:postgresql://HOST_DE_NEON:5432/neondb?sslmode=require
spring.datasource.username=USUARIO
spring.datasource.password=TU_CONTRASEÑA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Neon exige conexión segura, por eso la URL termina en `?sslmode=require`. Con `ddl-auto=update`, Hibernate crea las tablas a partir de las entidades al iniciar la aplicación.

**3. Ejecutar la aplicación**

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`. La primera conexión puede tardar unos segundos si Neon estaba suspendida.

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
- El dron debe tener al menos un 30 % de batería para ser asignado.
- Al asignar, el pedido pasa a `EN_CAMINO` y el dron a `EN_VUELO`.

## 📓 Bitácora de avance

### Avance 1 — 29/10/2026

- **Qué hicimos:** creamos el proyecto base con Spring Boot, el repositorio y el README inicial.
- **Problemas y soluciones:** sin inconvenientes registrados hasta el momento.
- **Qué sigue:** conectar la aplicación a PostgreSQL y crear la entidad `Cliente`.

### Avance 2 — 25/10/2026

- **Qué hicimos:**
  - Conectamos la aplicación a una base PostgreSQL alojada en **Neon**, de modo que los dos integrantes trabajamos sobre la misma base.
  - Configuramos `application.properties` con la URL, el usuario y la contraseña, y habilitamos `ddl-auto=update` para que Hibernate cree las tablas.
  - Protegimos las credenciales: agregamos `application.properties` al `.gitignore` y dejamos un `application.properties.example`.
  - Agregamos Lombok al `pom.xml`.
- **Problemas y soluciones:**
  - *Credenciales en el repositorio:* una línea del `.gitignore` se había escrito por error en `.gitattributes`, donde no tiene efecto. La movimos al `.gitignore`.
  - *Lombok con JDK 25:* no generaba getters ni setters porque desde el JDK 23 los procesadores de anotaciones ya no se ejecutan automáticamente. Lo resolvimos declarando `annotationProcessorPaths` en el `maven-compiler-plugin` del `pom.xml`.
- **Qué sigue:** crear las entidades JPA.

### Avance 3 — 25/10/2026

- **Qué hicimos:**
  - Creamos el paquete `model` y las entidades `Cliente` y `Pedido`, con el enum `EstadoPedido`.
  - Mapeamos la relación `Pedido` → `Cliente` con `@ManyToOne` y `@JoinColumn`.
  - Verificamos en pgAdmin que se crearon las tablas `cliente` y `pedido`, con la clave foránea de `pedido` hacia `cliente`.
- **Problemas y soluciones:**
  - *Clase duplicada:* al pegar el código en una clase recién creada, el `package` y la declaración quedaron repetidos. Se resuelve seleccionando todo el contenido y pegando encima.
  - *Enum equivocado:* se pegó el contenido de `EstadoDron` dentro de `EstadoPedido`; se corrigió dejando en cada archivo solo su
