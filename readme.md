# Demo - Gestión de Archivos

Este proyecto es una aplicación desarrollada en **Java** utilizando **Spring Boot** para la gestión de archivos. Permite cargar, filtrar y recuperar archivos almacenados en una base de datos.

## Características

- **Carga de archivos**: Permite subir archivos con metadatos como nombre, tipo y contenido codificado.
- **Filtrado de archivos**: Filtra archivos por fecha de carga, nombre, tipo y orden de subida.
- **Seguridad**: Implementación de autenticación mediante **basic** para proteger los endpoints.
- **Base de datos**: Uso de **H2** como base de datos en memoria para pruebas.
- **Pruebas unitarias**: Cobertura de pruebas con **JUnit 5** y **Mockito**.

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.4.5**
  - Spring Data JPA
- **H2 Database**
- **MapStruct** (para mapeo de entidades)
- **Spring Security**
- **Maven** (gestión de dependencias)
- **JUnit 5** y **Mockito** (pruebas)

## Requisitos previos

- **Java 21** instalado.
- **Maven 3.9.9** instalado.

### 2. Manejo global de excepciones
El proyecto utiliza un `GlobalExceptionHandler` para capturar y manejar excepciones comunes de manera centralizada. Ejemplo de excepciones manejadas:

- **`MethodArgumentTypeMismatchException`**: Devuelve un error 400 con el mensaje "The date format provided is not valid".
- **`IllegalArgumentException`**: Devuelve un error 422 con el mensaje "The request is well-formed but it contains semantic errors".
- **`AuthorizationDeniedException`**: Devuelve un error 403 con el mensaje "Unauthorized error".
- **`Exception`**: Devuelve un error 500 con el mensaje "An unexpected error occurred".

### 3. Seguridad con Spring Security
El proyecto incluye configuración de seguridad con **Spring Security**:

- **Autenticación en memoria**:  
  Se definen dos usuarios:
  - Usuario: `user` con contraseña `user` y rol `ROLE_USER`.
  - Administrador: `admin` con contraseña `admin` y rol `ROLE_ADMIN`.
  
## Configuración del proyecto

1.Construye el proyecto:  

		mvn clean install
	

2.Ejecuta la aplicación:  

		mvn spring-boot:run
    
3.Accede a la aplicación en: 

		http://localhost:8080

## Endpoints principales
Gestión de archivos

		GET /api/mgmt/1/assets Obtiene una lista de archivos filtrados. Parámetros opcionales:  
	
		uploadDateStart (fecha de inicio)
		uploadDateEnd (fecha de fin)
		filename (nombre del archivo)
		filetype (tipo de archivo)
		sortDirection (ASC o DESC)

	

		POST /api/mgmt/1/assets Sube un archivo. Cuerpo de la solicitud:  
		{
		  "filename": "nombre_del_archivo",
		  "contentType": "tipo_de_contenido",
		  "encodedFile": "contenido_codificado"
		}
