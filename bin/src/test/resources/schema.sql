CREATE TABLE IF NOT EXISTS CONTENIDO (
    id_contenido INT AUTO_INCREMENT PRIMARY KEY,
    id_instructor INT,
    titulo VARCHAR(255),
    descripcion VARCHAR(1000),
    url_contenido VARCHAR(255) UNIQUE,
    tipo_contenido VARCHAR(50),
    fecha_creacion DATE
);