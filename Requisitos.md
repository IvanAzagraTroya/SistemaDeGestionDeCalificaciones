# Requisitos:
## Gestión de alumnado: 
- obtener datos de un único alumno o de todo el grupo según se requiera

- modificación de datos o indicar si el alumno no está disponible

- Consultas ordenadas por número de lista o alfabético

- Clase Alumno -> dni, nombre, apellidos, email, 
tlf en formato (NNN-NNNNNN), si ha perdido la evaluación continua,
fecha de matriculación en formato DD/MM/AAAA

- Un alumno no puede ser eliminado si está en una evaluación, se sugiere indicar si está disponible o no

- categorías de evaluación: herencia práctica, examen, ejercicio, exposición

- las evaluaciones tendrán que ser gestionadas en un repositorio aparte en el que se pueda añadir o modificar las mismas

- Prueba de evaluación (heredará de examen): fecha en formato DD/MM/AAAA HH:MM:SS, descripción, calificacion independiente por alumno para esto se deberá hacer la comprobación de disponibilidad y evaluación continua.
	
## Evaluación:
- Vista de las evaluaciones, se podrán listar, consultar pero no modificar si ya han sido creadas, podrán ser eliminadas

- La evaluación deberá almacenar la nota máxima, mínima, media, %aprobados, %suspensos

- La vista mostrará al alumnado por orden de nota (nota con dos decimales formato español)
		
- Exportado de calificación a un fichero markdown

- Fichero markdown con rasgos de estilo (encabezados, listas negrita, etc)
		
- El usuario podrá elegir el directorio donde almacenar el archivo

- El informe terminará con la fecha de generación, tiempo que ha tardado en generarse en formato DD/MM/AAAA HH:MM:SS en SS(estos segundos de tiempo en generarse)

- Capacidad de exportación en formato json en fichero backup

### ATENCIÓN A LA ENTRADA DE DATOS, EXCEPCIONES, REGEX, ETC
	
### Realización de test unitarios
	
### Diagramas de clase ✅
	
### Respetar principios SOLID, arquitecturas y patrones

### Uso de github

### Nombramiento del programa -> $ java -jar calificaciones.jar

### Todos los datos deberán estar formateados a formatos oficiales de España

### No debe ser case-sensitive (Da igual si se usan mayúsculas o minusculas no deberá afectar)

- Creación de inicializador de datos para pruebas.

- Gestión de test por maven o graddle

### En la salida de informe debe salir el nombre de los integrantes

- Readme que explique el proyecto

- Código comentado y uso de JavaDoc(JDoc). Además de los gitignore adecuados y que siga el flujo de trabajo GitFlow. No se deben incluir los ejecutables si no se deben poder crear los jar desde maven. Usa .gitignore bien.
	
## Documentación en PDF que describa el proceso de solución.
- Requisitos funcionales, no funcionales y de información
- Diagrama de casos de uso
- Diagrama de clases
- arquitectura/s utilizadas
- patrones de diseño y ejemplo de la aplicación
- principios solid aplicados con ejemplos
- pruebas unitarias
