# Microservicio-Contenido
Descripción general
Servicio dedicado a la visualización de material didáctico, relacionado con el curso al que va dirigido, el instructor que creó ese material y los estudiantes que pueden acceder al contenido.
Dentro de este servicio hay 1 clase la cual es CModel.java con los siguientes atributos:

Clase cModel:
Integer idContenido: El valor es generado automáticamente como clave primaria.
Integer idInstructor: Este atributo se asocia al instructor que creó el contenido.
String titulo: Este atributo contiene el titulo de el contenido.
String descripción:Este atributo contiene la descripción de el contenido.
String urlContenido:	Este atributo contiene la url de el contenido
String tipoContenido:Este atributo contiene el tipo de contenido 
Date fechaCreacion:Este atributo contiene la fecha en que se creo el contenido.

EndPoints para cController “Contenido”
Método: GET
Ruta: ("")
Descripción: Invoca el servicio contenidoService para obtener una lista completa de objetos del tipo cModel, que representan las entidades de contenido gestionadas por la aplicación.
Respuesta esperada: 

Método: POST
Ruta: ("")
Descripción: Recibe un objeto cModel en el cuerpo de la solicitud, que contiene los datos del contenido que se desea agregar. El método invoca el servicio contenidoService.saveContenido(contenido) para guardar el nuevo contenido en el sistema y devuelve el objeto contenido guardado.

Método: GET
Ruta: ("/{idContenido}")
Descripción: Recibe el idContenido como un parámetro de la ruta (@PathVariable) y utiliza el servicio contenidoService.getContenido(idContenido) para obtener el contenido correspondiente. Devuelve el objeto cModel que representa el contenido con el ID especificado.

Método: PUT
Ruta: ("/{idContenido}")
Descripción: Recibe el objeto cModel con los nuevos datos en el cuerpo de la solicitud y el idContenido como un parámetro en la ruta. Utiliza el servicio contenidoService.saveContenido(contenido) para guardar los cambios en el contenido especificado.

Método: DELETE
Ruta: ("/{idContenido}")
Descripción: Recibe el idContenido como un parámetro de la ruta y utiliza el servicio contenidoService.deleteContenido(idContenido) para eliminar el contenido correspondiente del sistema.
