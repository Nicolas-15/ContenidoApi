📰 Contenido API – REST Service con Spring Boot + HATEOAS + Docker

API REST creada en Java / Spring Boot para gestionar contenidos (videos, artículos, documentos, podcasts, etc.), con soporte para HATEOAS, persistencia con JPA, generación automática de datos para desarrollo y despliegue con Docker.

🚀 Tecnologías Utilizadas

Java 17

Spring Boot

Spring Web

Spring Data JPA

Spring HATEOAS

Lombok

DataFaker (carga de datos fake en entorno dev)

Docker

Maven

📌 Características Principales

✔ CRUD completo de contenidos
✔ API REST con enlaces HATEOAS
✔ Arquitectura en capas (Controller – Service – Repository – Model)
✔ Carga automática de datos falsos en perfil dev
✔ Proyecto preparado para funcionar en Docker
✔ Código limpio y mantenible

📂 Estructura del Proyecto
src/main/java/com/Contenidos/Contenido
│
├── Controller/
│   └── cControllerV2.java
│
├── Model/
│   └── cModel.java
│
├── Repository/
│   └── cRepository.java
│
├── Service/
│   └── cService.java
│
├── ContenidoModelAssembler.java   → HATEOAS
└── DataLoader.java                → Carga de datos (perfil dev)

🧩 Modelo de Datos (cModel)

Representa un contenido con:

idInstructor

titulo

descripcion

urlContenido

tipoContenido (Video / Artículo / Podcast / Documento)

fechaCreacion

🔗 API REST Endpoints (v2)

Base URL:

/api/v2/contenido

Método	Endpoint	Descripción
GET	/	Lista todos los contenidos (HATEOAS)
POST	/	Crea un nuevo contenido
GET	/{idContenido}	Obtiene un contenido por ID
PUT	/{idContenido}	Actualiza un contenido existente
DELETE	/{idContenido}	Elimina un contenido
🧭 Ejemplo de Respuesta HATEOAS
{
  "idContenido": 1,
  "titulo": "Ejemplo",
  "descripcion": "Texto...",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/v2/contenido/1"
    },
    "contenido": {
      "href": "http://localhost:8080/api/v2/contenido"
    }
  }
}


Esto lo genera ContenidoModelAssembler:

return EntityModel.of(contenido,
    linkTo(methodOn(cControllerV2.class).obtenerContenidoPorID(contenido.getIdContenido())).withSelfRel(),
    linkTo(methodOn(cControllerV2.class).listaContenido()).withRel("contenido")
);

🧪 Carga Automática de Datos (Perfil DEV)

El archivo DataLoader.java genera contenido ficticio usando DataFaker cuando se ejecuta:

spring.profiles.active=dev


Genera 10 contenidos con:

✔ títulos
✔ descripción
✔ URL de contenido fake
✔ fechas aleatorias
✔ tipo de contenido aleatorio

🐳 Ejecución con Docker
1. Build de la imagen
docker build -t springboot-app .

2. Levantar el contenedor
docker-compose up --build


El servicio queda disponible en:

http://localhost:8080/api/v2/contenido

▶️ Ejecución Local (sin Docker)
mvn clean install
mvn spring-boot:run

📌 Mejoras Futuras

Autenticación (Spring Security + JWT)

Paginación y filtros por categoría o tipo

Subida de archivos (videos, PDFs, etc.)

Documentación Swagger

👤 Autor

Nicolás López
Analista Programador – Duoc UC
GitHub: https://github.com/Nicolas-15

Email: nic.lopezp@duocuc.cl
