# Backend para Plataforma de Profesionales

Este es el backend de la plataforma de profesionales, construida con Spring Boot. El backend maneja la lógica de negocio relacionada con los perfiles de los profesionales, incluyendo las operaciones de CRUD (Crear, Leer, Actualizar, Eliminar) y la gestión de imágenes de perfil y galería.

## Estructura del Proyecto

El proyecto está organizado en varias capas y módulos que siguen la arquitectura de **Spring Boot** con principios de **Domain-Driven Design (DDD)**. La estructura principal es:

## Requisitos

Asegúrate de tener instalado:

- **Java 11** o superior
- **Maven** o **Gradle** (dependiendo de tu preferencia para la construcción del proyecto)
- **IDE** de preferencia: IntelliJ IDEA, Eclipse, Visual Studio Code con soporte para Java.

## Instalación

## 1) Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/backend-plataforma-profesionales.git
   ````
## 2) Accede a la carpeta del proyecto:
```bash
   cd backend-plataforma-profesionales
   ```
## 3) Compila el proyecto usando Maven o Gradle.
    ```bash
    mvn clean install
    ```
    o si usas Gradle:
    ```bash
    gradle build
    ```
## 4) Ejecuta la aplicación de Spring Boot:
    ```bash
    mvn spring-boot:run
    ```
    o si usas Gradle:
    ```bash
    gradle bootRun
    ```
## 5) La aplicación estará disponible en: http://localhost:8080
