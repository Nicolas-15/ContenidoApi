readme_content = """\
# Proyecto API de Gestión de Contenidos con Spring Boot

Este proyecto es una API REST desarrollada con Spring Boot para gestionar contenidos educativos. Permite crear, consultar, actualizar y eliminar contenidos con atributos como título, descripción, URL, tipo y fecha de creación. Está conectada a una base de datos MySQL alojada en AWS RDS y ofrece dos versiones de API, una básica y otra con soporte HATEOAS para mejorar la navegabilidad.

---

## Características del Proyecto

- API RESTful implementada con Spring Boot y Spring Data JPA.
- Uso de MySQL como base de datos remota en AWS RDS.
- Entidad `Contenido` con atributos para gestión completa.
- Dos versiones de API:
    - `/api/v1/contenido`: CRUD básico.
    - `/api/v2/contenido`: CRUD con respuestas enriquecidas con HATEOAS.
- Generación automática de datos de prueba en perfil `dev` usando Faker.
- Documentación Swagger UI integrada para probar y explorar los endpoints.

---

## Requisitos de Instalación

Para ejecutar el proyecto localmente, debes tener instalado:

- Java Development Kit (JDK) 17 o superior.
- Maven para gestión de dependencias.
- Base de datos MySQL (puedes usar el servicio AWS RDS con los datos configurados).
- IDE compatible con Java (IntelliJ, Eclipse, VSCode, etc).

---

## Configuración

1. Clona o descarga el repositorio en tu máquina local.

2. Abre el archivo `src/main/resources/application.properties` y actualiza las credenciales y URL de la base de datos:

```properties
spring.datasource.url=jdbc:mysql://52.22.235.213:3306/dbContenido?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true
spring.datasource.username=admin
spring.datasource.password=DuocUC..2025
spring.jpa.hibernate.ddl-auto=update

3. Opcional: El proyecto está configurado para cargar datos de prueba automáticamente en el perfil dev.

Cómo Ejecutar
Ejecuta la aplicación desde tu IDE
La API estará disponible en http://localhost:8080.

Endpoints principales
Versión 1 - Básica (/api/v1/contenido)
GET /api/v1/contenido - Obtiene lista completa de contenidos.

POST /api/v1/contenido - Crea un nuevo contenido.

GET /api/v1/contenido/{idContenido} - Obtiene contenido por ID.

PUT /api/v1/contenido/{idContenido} - Actualiza contenido existente.

DELETE /api/v1/contenido/{idContenido} - Elimina contenido por ID.

Versión 2 - Con HATEOAS (/api/v2/contenido)
Mismos endpoints que la versión 1.

Las respuestas incluyen enlaces HATEOAS para facilitar la navegación de la API.

Modelo principal
La entidad cModel representa los contenidos con estos campos:

Campo	Tipo	Descripción
idContenido	Integer	Identificador único autogenerado
idInstructor	Integer	ID del instructor relacionado
titulo	String	Título del contenido
descripcion	String	Descripción breve
urlContenido	String	URL única del contenido
tipoContenido	String	Tipo (Video, Artículo, Podcast, Documento)
fechaCreacion	Date	Fecha en que se creó el contenido

Documentación Swagger
Puedes explorar y probar la API usando Swagger UI en: http://localhost:8080/swagger-ui.html

Carga de datos de prueba
Al iniciar la aplicación con perfil dev, se cargan automáticamente 10 contenidos de prueba con Faker, simulando datos realistas.
```
# Documentación de Testing - Proyecto API Gestión de Contenidos

Este documento describe las pruebas implementadas para asegurar la calidad del proyecto API de gestión de contenidos desarrollado con Spring Boot.

---

## 1. Pruebas Unitarias del Controlador (cControllerTest)

Se utilizan pruebas unitarias con JUnit 5 y Mockito para validar el comportamiento del controlador REST `cController` sin arrancar el servidor.

- **Objetivo:** Verificar que el controlador responde correctamente a los métodos HTTP: GET, POST, PUT y DELETE.
- **Herramientas:** JUnit 5, Mockito, MockMvc.
- **Características:**
  - Mock de `cService` para simular comportamiento sin conexión a base de datos.
  - Validación del status HTTP, tipo de contenido JSON y contenido del cuerpo.
  - Uso de `ObjectMapper` para convertir objetos Java a JSON en las pruebas POST y PUT.
  - Verificación de que los métodos del servicio son llamados la cantidad esperada de veces.

**Ejemplo de pruebas:**
- `testListaContenido()` verifica que el endpoint GET `/api/v1/contenido` devuelve la lista de contenidos correctamente.
- `testObtenerContenidoPorID()` prueba la respuesta al consultar un contenido específico.
- `testAgregarContenido()` y `testActualizarContenido()` comprueban que POST y PUT funcionan y retornan los datos esperados.
- `testEliminarContenido()` valida que DELETE responde con status OK y llama al método de borrado.

---

## 2. Pruebas de Repositorio JPA (cRepositoryTest)

Pruebas enfocadas en la capa de persistencia, utilizando Spring Boot Test con `@DataJpaTest` y base de datos en memoria H2 para aislar la lógica de acceso a datos.

- **Objetivo:** Validar el correcto funcionamiento de las operaciones CRUD en el repositorio JPA.
- **Características:**
  - Prueba de guardado y recuperación de un contenido.
  - Verificación de listado completo.
  - Prueba de eliminación y confirmación de ausencia posterior.
  - Uso de perfil `test` para cargar configuración especial de base de datos H2.

---

## 3. Test de Contexto de Spring Boot (ContenidoApplicationTests)

Prueba sencilla para verificar que el contexto de Spring Boot se carga correctamente sin errores.

- **Objetivo:** Confirmar que la aplicación arranca sin problemas en entorno de pruebas.
- **Anotaciones:** `@SpringBootTest` y `@ActiveProfiles("test")`.

---

## 4. Configuración de la Base de Datos para Pruebas

Para pruebas, se utiliza una base de datos en memoria H2, configurada en el perfil `test` mediante las siguientes propiedades:

```properties
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql

spring.datasource.oracle.wallet=false
Esta configuración garantiza un entorno limpio para cada ejecución de prueba, evitando impacto en la base de datos de producción.
```
## 5. Ejecución de las pruebas
Desde tu IDE usando las herramientas integradas de ejecución de tests.