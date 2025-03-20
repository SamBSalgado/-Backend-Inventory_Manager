# Inventory Manager - Backend

Este es el repositorio del backend de la aplicación de administración de inventario. Proporciona la API para la gestión de productos, filtrado, ordenación y obtención de métricas de inventario.

## 🚀 Características

- 📦 **Gestor de productos**: agregar, editar, eliminar y ajustar stock.
- 🔍 **Búsqueda y filtrado**: por nombre, categoría y disponibilidad en stock.
- 📊 **Métricas de inventario**: estadísticas globales y por categoría.
- 📑 **Ordenación**: por categoría, nombre, precio, fecha de vencimiento y stock.

## 🛠️ Tecnologías Utilizadas

- [Spring Boot](https://start.spring.io/index.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Java](https://www.oracle.com/java/technologies/downloads/#jdk24-mac)
- [Postman](https://www.postman.com/downloads/)

## ⚙️ Instalación y Configuración

1. **Clonar el repositorio:**
   ```sh
   git clone https://github.com/SamBSalgado/-Backend-Inventory_Manager.git
   ```

2. **Compilar y ejecutar la aplicación:**
   ```sh
   mvn spring-boot:run
   ```

3. **La API estará disponible en:**
   ```
   http://localhost:9090/products
   ```

## 📌 Notas Adicionales
- Asegúrate de que el frontend esté configurado para apuntar a la URL correcta del backend.
- El frontend que consume esta API se encuentra en:
  ```sh
  https://github.com/SamBSalgado/Inventory_Manager.git
  ```
