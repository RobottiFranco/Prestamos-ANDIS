# Prestamos-ANDIS

La rama main es la rama principal de este repositorio y se encuentra protegida. Para contribuir, por favor, crea una rama (o use la dev) y solicita un pull request.

## Descripción

Este proyecto es una aplicación de gestión de préstamos desarrollada en Spring Boot. Utiliza un archivo JSON (`prestamos.json`) como base de datos en lugar de una base de datos real. La aplicación está diseñada para gestionar préstamos y se ejecuta en un contenedor Docker usando Docker Compose.

## Funcionalidades

- **CRUD de Préstamos**: Crear, leer, actualizar y eliminar préstamos.
- **Uso de JSON**: La información se almacena en un archivo `prestamos.json` solo durante la ejecucion, luego vuelven a su estado original.
- **Docker Compose**: Configuración para ejecutar la aplicación en un contenedor Docker.

## Configuración

### Docker Compose

El archivo `docker-compose.yml` configura el entorno de ejecución de la aplicación. Asegúrate de tener Docker y Docker Compose instalados.

## Uso

### **Construir la imagen Docker y ejecutar los contenedores**:

   ```bash
   docker-compose up --build
```

### **ejecutar posteriormente**:

   ```bash
    docker-compose up
```

### **Detener los contenedores**:

   ```bash
   docker-compose down
```

### **Acceder a la aplicación**:

La aplicación estará disponible en `http://localhost:8080`.

### **Documentación de la API**:

#### GET
http://localhost:8080/prestamos/obtenerTodos
http://localhost:8080/prestamos/obtenerPorId/{id}
http://localhost:8080/prestamos/obtenerPorCliente/{idCliente}
http://localhost:8080/prestamos/ObtenerPagados
http://localhost:8080/prestamos/ObtenerNoPagados

#### POST
http://localhost:8080/prestamos/guardar

#### PUT
http://localhost:8080/prestamos/actualizar/{id}

#### DELETE
http://localhost:8080/prestamos/eliminar/{id}

![image](https://github.com/user-attachments/assets/07ed4517-567b-4c7d-9cd5-956fbf87ebd2)

