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
| **Profesor** | Tulio Ruesjas Martín |

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
| Git y GitHub | Control de versiones y trabajo en equipo |

## 🧩 Diagrama UML

![Diagrama UML](docs/diagrama-uml.png)

### Relaciones

| Relación | Tipo | Columna de clave foránea |
|----------|------|--------------------------|
| Pedido → Cliente | Muchos a uno | `pedido.id_cliente` |
| Telemetría → Dron | Muchos a uno | `telemetria.id_dron` |
| Ruta → Dron | Muchos a uno | `ruta.id_dron` |
| Ruta → Pedido | Muchos a uno | `ruta.id_pedido` |

Las relaciones son **unidireccionales**, como indican las flechas del UML: solo el lado "muchos" guarda la referencia al lado "uno".

### Entidades implementadas

**`Cliente`**

| Atributo | Tipo | Notas |
|----------|------|-------|
| `idCliente` | `Integer` | Clave primaria autogenerada |
| `nombre` | `String` | |
| `email` | `String` | |
| `direccion` | `String` | |

**`Pedido`**

| Atributo | Tipo | Notas |
|----------|------|-------|
| `idPedido` | `Integer` | Clave primaria autogenerada |
| `origen` | `String` | |
| `destino` | `String` | |
| `peso` | `double` | |
| `estado` | `EstadoPedido` | Enum guardado como texto |
| `fechaCreacion` | `LocalDateTime` | |
| `cliente` | `Cliente` | `@ManyToOne`, obligatorio |

**`Dron`**

| Atributo | Tipo | Notas |
|----------|------|-------|
| `idDron` | `Integer` | Clave primaria autogenerada |
| `modelo` | `String` | |
| `capacidadMaxima` | `double` | |
| `bateria` | `int` | |
| `estado` | `EstadoDron` | Enum guardado como texto |

**`Telemetria`**

| Atributo | Tipo | Notas |
|----------|------|-------|
| `idTelemetria` | `Integer` | Clave primaria autogenerada |
| `latitud` | `double` | |
| `longitud` | `double` | |
| `bateria` | `int` | |
| `fechaRegistro` | `LocalDateTime` | |
| `dron` | `Dron` | `@ManyToOne`, obligatorio |

**`Ruta`**

| Atributo | Tipo | Notas |
|----------|------|-------|
| `idRuta` | `Integer` | Clave primaria autogenerada |
| `puntosRecorrido` | `String` | Columna `TEXT`, sin límite de 255 caracteres |
| `distanciaKm` | `double` | |
| `fechaAsignacion` | `LocalDateTime` | |
| `dron` | `Dron` | `@ManyToOne`, obligatorio |
| `pedido` | `Pedido` | `@ManyToOne`, obligatorio |

### Enums

| Enum | Valores |
|------|---------|
| `EstadoPedido` | `PENDIENTE`, `EN_CAMINO`, `ENTREGADO`, `CANCELADO` |
| `EstadoDron` | `DISPONIBLE`, `EN_VUELO`, `MANTENIMIENTO` |

### Estado de las entidades

| Entidad | Estado | Responsable |
|---------|:------:|-------------|
| `Cliente` | ✅ | Arevalo Santiago |
| `Pedido` | ✅ | Arevalo Santiago |
| `EstadoPedido` | ✅ | Arevalo Santiago |
| `Dron` | ✅ | Cejas Tomás |
| `Telemetria` | ✅ | Cejas Tomás |
| `Ruta` | ✅ | Cejas Tomás |
| `EstadoDron` | ✅ | Cejas Tomás |

## 📁 Estructura del proyecto

```
FlotaDeDrones/
├── docs/                    # Diagramas y capturas del informe
├── FlotadeDrones/           # Proyecto Spring Boot
│   ├── src/main/java/com/example/FlotadeDrones/
│   │   ├── model/           # Entidades JPA y enums (implementado)
│   │   ├── repository/      # Interfaces JpaRepository (pendiente)
│   │   ├── service/         # Lógica de negocio (pendiente)
│   │   ├── controller/      # Endpoints REST (pendiente)
│   │   ├── dto/             # Objetos de transferencia (pendiente)
│   │   └── exception/       # Manejo de errores (pendiente)
│   ├── src/main/resources/
│   └── pom.xml
└── README.md
```

## ⚙️ Cómo ejecutar el proyecto

### Requisitos previos

- JDK 25
- Maven (o el wrapper `mvnw` incluido en el proyecto)
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

### Abrir el proyecto en IntelliJ

El proyecto Maven está en la subcarpeta `FlotadeDrones/`, no en la raíz del repositorio. Hay que abrir esa carpeta (o su `pom.xml`) como proyecto, o agregar el `pom.xml` con *Add as Maven Project*; de lo contrario IntelliJ no reconoce el código fuente.

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

### Avance 1 — 25/10/2026

**Objetivo:** dejar listo el entorno de trabajo, la conexión a la base de datos y el modelo de datos completo (entidades JPA) a partir del diagrama UML.

#### Qué hicimos

**Entorno y configuración (ambos)**

- Creamos el repositorio en GitHub y el proyecto base con Spring Boot 4.1.2, Maven y Java 25.
- Dependencias del proyecto: Spring Web, Spring Data JPA, PostgreSQL Driver, Validation y Lombok.
- Conectamos la aplicación a una base PostgreSQL alojada en **Neon**, de modo que los dos trabajamos sobre la misma base.
- Configuramos `application.properties` (URL con SSL, usuario, contraseña) y habilitamos `ddl-auto=update` para que Hibernate cree las tablas a partir de las entidades.
- Protegimos las credenciales: `application.properties` está en el `.gitignore` y el repositorio incluye solo `application.properties.example`.
- Configuramos Lombok en el `pom.xml` (dependencia y procesador de anotaciones).
- Armamos el README con la descripción, el UML, las instrucciones de ejecución y esta bitácora.

**Modelo de datos (por integrante)**

| Integrante | Trabajo realizado |
|------------|-------------------|
| Arevalo Santiago | Paquete `model`. Entidades `Cliente` y `Pedido`, enum `EstadoPedido`. Relación `Pedido` → `Cliente` con `@ManyToOne` y `@JoinColumn`. |
| Cejas Tomás | Entidades `Dron`, `Telemetria` y `Ruta`, enum `EstadoDron`. Relaciones `Telemetria` → `Dron`, `Ruta` → `Dron` y `Ruta` → `Pedido`. |

**Decisiones técnicas**

- Los estados son **enums** con `@Enumerated(EnumType.STRING)` en lugar de `String`: evita valores inválidos y deja legibles los datos en la base.
- Los ids son `Integer` con `GenerationType.IDENTITY`, para que sean `null` antes de guardar y los genere la base.
- Las relaciones son **unidireccionales** (solo `@ManyToOne`), siguiendo las flechas del UML.
- `puntosRecorrido` usa `columnDefinition = "TEXT"` porque un `String` común admite solo 255 caracteres.
- Se usa Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`) para reducir el código repetitivo.

#### Resultado

Hibernate creó en Neon las cinco tablas del modelo: `cliente`, `pedido`, `dron`, `ruta` y `telemetria`, con sus claves foráneas, verificadas desde pgAdmin.

<!-- Cuando suban las capturas a docs/capturas/, quiten los comentarios de las líneas siguientes -->
<!-- ![Tablas en pgAdmin](docs/capturas/tablas-pgadmin.png) -->

#### Problemas y soluciones

| Problema | Causa | Solución |
|----------|-------|----------|
| Riesgo de subir la contraseña a GitHub | La línea de `application.properties` se había escrito por error en `.gitattributes`, donde no tiene efecto | La movimos al `.gitignore` y comprobamos con `git status` que el archivo no se rastrea |
| Lombok no generaba getters ni setters con JDK 25 | Desde el JDK 23, Java ya no ejecuta automáticamente los procesadores de anotaciones | Declaramos `annotationProcessorPaths` de Lombok en el `maven-compiler-plugin` del `pom.xml` y activamos *Annotation Processing* en IntelliJ |
| Clase duplicada al crear una entidad | IntelliJ ya genera el `package` y la clase; al pegar el código quedaron repetidos | Seleccionar todo el contenido (`Ctrl + A`) y pegar encima |
| Enum con el contenido equivocado | Se pegó `EstadoDron` dentro de `EstadoPedido` | Dejar en cada archivo solo su enum |
| IntelliJ no reconocía el código ni permitía ejecutar (Tomás) | Se abrió la carpeta raíz del repositorio, no la carpeta con el `pom.xml` | Agregar el `pom.xml` con *Add as Maven Project* y configurar el SDK 25 |
| Error de sincronización de Maven: `Unable to create injector ... MissingImplementation` (Tomás) | IntelliJ usaba una versión de Maven que no correspondía | Configurar el *Maven home path* para usar el wrapper del proyecto o el Maven incluido en IntelliJ |

#### Qué sigue

Crear los repositorios (`JpaRepository`) de las cinco entidades.

### Planificado

- Repositorios de `Cliente`, `Pedido`, `Dron`, `Telemetria` y `Ruta`.
- Servicios con la lógica básica de cada entidad (crear, listar, buscar por id).
- Excepción propia para recursos no encontrados.
- Pruebas de guardado y consulta contra la base.

- Controllers REST y CRUD de Cliente, Dron y Pedido.
- Asignación de dron a pedido y generación de la ruta, con validación de peso y batería.
- Recepción de telemetría.
- Colección de Postman con todas las pruebas y capturas en este README.

<!-- Copien este bloque para cada nuevo avance -->

## 📸 Pruebas y capturas

*(Agregar aquí las capturas a medida que se implemente cada funcionalidad: tablas en pgAdmin y pruebas en Postman)*

## ✅ Estado del proyecto

- [x] Repositorio y proyecto base
- [x] Conexión a PostgreSQL (Neon)
- [x] Entidades JPA y enums
- [ ] Repositorios
- [ ] Servicios
- [ ] Controllers y CRUD de Cliente, Dron y Pedido
- [ ] Asignación de dron y generación de ruta
- [ ] Recepción de telemetría
- [ ] Colección de Postman final
- [ ] Presentación
