# Backend CRUD Librería 

API REST desarrollada con **Spring Boot** para la gestión de libros de una biblioteca.

El backend permite realizar operaciones CRUD (Crear, Consultar, Actualizar y Eliminar) sobre libros, además de búsqueda por título, autor o género.

---

# Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Swagger OpenAPI
- Maven

---

# Arquitectura del proyecto

El backend utiliza una arquitectura por capas:

```
Controller
    |
    ↓
Service
    |
    ↓
Repository
    |
    ↓
Database (MySQL)
```

## Controller

Responsable de recibir las peticiones HTTP y devolver respuestas.

Ejemplo:

```
GET /api/libros
```

---

## Service

Contiene la lógica del negocio.

Funciones principales:

- Generación automática del código del libro.
- Validación de existencia.
- Procesamiento de búsquedas.

---

## Repository

Comunicación con la base de datos mediante Spring Data JPA.

---

## Model

Representa la entidad Libro almacenada en la base de datos.

---

# Instalación y ejecución

## 1. Clonar repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

---

## 2. Configurar base de datos

Crear una base de datos MySQL:

```sql
CREATE DATABASE libreria;
```

Configurar las credenciales en:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/libreria
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update

server.port=8020
```

---

## 3. Ejecutar aplicación

Desde Maven:

```bash
mvn spring-boot:run
```

O ejecutar la clase principal de Spring Boot.

---

# Puerto del servidor

La API queda disponible en:

```
http://localhost:8020
```

---

# Documentación Swagger

La documentación de la API se encuentra en:

```
http://localhost:8020/swagger-ui/index.html
```

Swagger permite:

- Visualizar endpoints.
- Probar peticiones.
- Consultar parámetros.
- Revisar respuestas.

---

# Endpoints disponibles


## Obtener todos los libros

Método:

```
GET
```

Ruta:

```
/api/libros
```

Respuesta:

```json
[
 {
  "id_libro":1,
  "codigoLibro":"A25",
  "titulo":"Clean Code",
  "autor":"Robert Martin",
  "genero":"Programacion",
  "anio":2008
 }
]
```

---

# Crear libro

Método:

```
POST
```

Ruta:

```
/api/libros
```

Entrada:

```json
{
 "titulo":"Clean Code",
 "autor":"Robert Martin",
 "genero":"Programacion",
 "anio":2008
}
```

El sistema genera automáticamente:

```
codigoLibro
```

Formato:

```
A-Z + 2 números
```

Ejemplo:

```
A25
B80
Z12
```

---

# Buscar libros

Método:

```
GET
```

Ruta:

```
/api/libros/buscar
```

Parámetros disponibles:

```
titulo
autor
genero
```

Ejemplo:

```
/api/libros/buscar?titulo=Clean
```

---

# Actualizar libro

Método:

```
PUT
```

Ruta:

```
/api/libros/{codigo}
```

Ejemplo:

```
/api/libros/A25
```

Entrada:

```json
{
 "titulo":"Clean Code",
 "autor":"Robert C. Martin",
 "genero":"Programacion",
 "anio":2008
}
```

---

# Eliminar libro

Método:

```
DELETE
```

Ruta:

```
/api/libros/{codigo}
```

Ejemplo:

```
/api/libros/A25
```

---

# Base de datos

Tabla principal:

```
libro
```

Campos:

| Campo | Tipo |
|---|---|
| id_libro | Integer |
| codigo_libro | VARCHAR |
| titulo | VARCHAR |
| autor | VARCHAR |
| genero | VARCHAR |
| anio | Integer |

---

# Generación de código único

Cada libro recibe un código generado automáticamente.

Proceso:

1. Se genera una letra aleatoria entre A y Z.
2. Se generan dos números aleatorios.
3. Se valida que el código no exista.
4. Se almacena el libro.

Ejemplo:

```
A12
B45
M90
```

---

# Integración con Frontend

El frontend desarrollado en React consume esta API mediante Axios.

Comunicación:

```
React
 |
 | HTTP Requests
 |
Spring Boot API
 |
 |
MySQL
```

---

# Autor

Proyecto CRUD Librería

Desarrollado como proyecto académico utilizando Spring Boot y React.
