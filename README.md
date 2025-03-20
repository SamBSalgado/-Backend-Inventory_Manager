# Inventory Manager - Backend

This is the backend repository of the inventory management application. It provides the API for product management, filtering, sorting, and obtaining inventory metrics.

## 🚀 Characteristics

- 📦 **Product Manager**: add, edit, delete and adjust stock.
- 🔍 **Search and filtering**: by name, category and stock availability.
- 📊 **Inventory metrics**: Global and category statistics.
- 📑 **Sorting**: by category, name, price, expiration date, and stock.

## 🛠️ Technologies Used

- [Spring Boot](https://start.spring.io/index.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Java](https://www.oracle.com/java/technologies/downloads/#jdk24-mac)
- [Postman](https://www.postman.com/downloads/)

## ⚙️ Instalation and Configuration

1. **Clone the repository:**
   ```sh
   git clone https://github.com/SamBSalgado/-Backend-Inventory_Manager.git
   ```

2. **Compile and run the project:**
   ```sh
   mvn spring-boot:run
   ```

3. **The API will be available in:**
   ```
   http://localhost:9090/products
   ```

## 📌 Aditional Notes
- Make sure that the frontend is configured to point to the correct URL of the backend.
- The frontend that consumes this API is located at:
  ```sh
  https://github.com/SamBSalgado/Inventory_Manager.git
  ```
