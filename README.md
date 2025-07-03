# 📚 API de Gestión de Contenidos Educativos con Spring Boot

# 1. 🚀 Descripción del Proyecto
API REST para gestión de contenidos educativos que permite:
- ✅ Operaciones CRUD sobre contenidos
- 🔄 Dos versiones de API: básica y HATEOAS
- 🗄️ Integración con MySQL en AWS RDS
- 🧪 Generación automática de datos de prueba
- 📖 Documentación interactiva con Swagger
- ⚙️ Despliegue automático en EC2 usando GitHub Actions

---

# 2. 🏗️ Diagrama de Arquitectura
```mermaid
    A[GitHub] --> B[GitHub Actions CI/CD]
    B --> C[EC2 Instance]
    C --> D[Docker Container]
    D --> E[Spring Boot App]
    E --> F[MySQL AWS RDS]
    A --> G[Local Development]
    G --> H[Spring Boot]
    G --> I[H2 Database]
```
# 3. Estructura de Proyecto
## 3. 📂 Estructura del Proyecto

```bash
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── Contenidos
│   │   │           └── Contenido
│   │   │               ├── ContenidoApplication.java         # 🚀 Punto de entrada
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               │   ├── cController.java             # 🎮 Controlador API v1
│   │   │               │   └── cControllerV2.java           # 🔗 Controlador API v2 (HATEOAS)
│   │   │               ├── model
│   │   │               │   └── cModel.java                  # 💾 Entidad de Contenido
│   │   │               ├── repository
│   │   │               ├── service
│   │   │               ├── assembler
│   │   │               │   └── ContenidoModelAssembler.java # 🧩 Ensamblador HATEOAS
│   │   │               └── util
│   │   │                   └── DataLoader.java              # 🧪 Cargador de datos de prueba
│   │   └── resources
│   │       ├── application.properties                       # ⚙️ Configuración principal
│   │       └── data.sql                                     # 📝 Datos iniciales (opcional)
│   └── test
│       └── java
│           └── com
│               └── Contenidos
│                   └── Contenido
│                       ├── controller                       # 🧪 Pruebas de controladores
│                       └── repository                       # 🧪 Pruebas de repositorios
├── .github
│   └── workflows
│       └── main.yml                                         # ⚙️ CI/CD Pipeline
├── Dockerfile                                               # 🐳 Configuración Docker
├── docker-compose.yml                                       # 🐋 Orquestación de contenedores
├── pom.xml                                                  # 📦 Configuración de Maven
└── README.md                                                # 📚 Documentación del proyecto
```
# 4. ⚙️ Configuración Clave
## application.properties
# Configuración AWS RDS
spring.datasource.url=jdbc:mysql://52.22.235.213:3306/dbContenido
spring.datasource.username=admin
spring.datasource.password=DuocUC..2025

# Configuración H2 (pruebas)
spring.jpa.hibernate.ddl-auto=update

## Dockefile
FROM openjdk:17-jdk-slim

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

# 5. 🌐 Endpoints de la API

Método/Endpoint /Descripción

GET	/api/v1/contenido	Obtener todos los contenidos

POST	/api/v1/contenido	Crear nuevo contenido

GET	/api/v1/contenido/{id}	Obtener contenido por ID

PUT	/api/v1/contenido/{id}	Actualizar contenido

DELETE	/api/v1/contenido/{id}	Eliminar contenido

GET	/api/v2/contenido	Versión HATEOAS (todos)

GET	/api/v2/contenido/{id}	Versión HATEOAS (por ID)

GET	/swagger-ui.html Documentación interactiva

# 6. 💾 Modelo de Datos (cModel)
classDiagram
direction RL

    class cModel {
        <<Entity (CONTENIDO)>>
        <<Spring Data JPA>>
        
        🔑 idContenido: Integer
        👤 idInstructor: Integer
        📛 titulo: String
        📝 descripcion: String
        🔗 urlContenido: String
        🏷️ tipoContenido: String
        📅 fechaCreacion: Date
    }
    
    note "🔑 Primary Key\n⏫ Auto Increment\n🔗 URL única\n📅 Fecha automática" as N1
    cModel .. N1

# 7. 🔄 Flujo CI/CD (GitHub Actions)
        
    name: build-and-deploy-jar-only 
        on: [push]
        jobs:
        build-and-test:
        runs-on: ubuntu-latest
        steps:
        - name: Clonar repositorio
        uses: actions/checkout@v4

      - name: Instalar Maven y Java 17
        run: sudo apt-get update && sudo apt-get install -y maven
      
      - name: Compilar proyecto
        run: mvn clean package
      
      - name: Copiar archivos a EC2
        uses: appleboy/scp-action@v0.1.6
        with:
          host: ${{ secrets.IP_SERVER }}
          username: ${{ secrets.USERNAME }}
          key: ${{ secrets.PRIVATE_KEY }}
          source: |
            dist/app.jar
            Dockerfile
            docker-compose.yml
          target: /home/ubuntu/app/
      
      - name: Desplegar en Docker
        uses: appleboy/ssh-action@v1
        with:
          host: ${{ secrets.IP_SERVER }}
          script: |
            cd /home/ubuntu/app
            sudo docker compose down
            sudo docker compose up -d --build

# 8 Pruebas Automatizadas
Estrategia de testing:
🧩 Pruebas unitarias de controladores con MockMvc

🗃️ Pruebas de repositorio con H2

🔗 Pruebas de integración de servicios

Ejemplo de prueba unitaria:

@Test

public void testObtenerContenidoPorID() throws Exception {

cModel mockContenido = new cModel();

mockContenido.setIdContenido(1);

when(contenidoService.getContenido(1)).thenReturn(mockContenido);
    
mockMvc.perform(get("/api/v1/contenido/1")).andExpect(status().isOk()).andExpect(jsonPath("$.idContenido", is(1)));
}

# 9. 📊 Documentación Técnica
    <dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Base de datos -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- HATEOAS -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-hateoas</artifactId>
    </dependency>
    
    <!-- Swagger -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.7.0</version>
    </dependency>
    
    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    </dependencies>

# 10. 🚀 Guía de Despliegue
Requisitos:

☁️ Instancia EC2 con Docker

🗃️ Base de datos MySQL (AWS RDS)

🔑 Secrets configurados en GitHub:

IP_SERVER

USERNAME

PRIVATE_KEY

PORT

Despliegue automático:

Push a rama main

GitHub Actions ejecuta pipeline:

🛠️ Compila el JAR

📦 Copia archivos a EC2

🐳 Reconstruye contenedor Docker