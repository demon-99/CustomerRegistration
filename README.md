
# 📌 Customer Registration API

This project is a Spring Boot-based User Registration and Authentication API. It provides functionalities for:

- User registration
- User authentication
- Role-based access control (RBAC) using Spring Security
- API documentation using Swagger
- Containerization using Docker

---


## 🚀 Tech Stack

- **Java 17**
- **Spring Boot** (Spring Security, Spring Data JPA, Spring Web)
- **H2** (In Memory Database)
- **Springdoc OpenAPI** (API Documentation)
- **Docker & Docker Compose**
- **JUnit & Mockito** (for Unit Testing)


## 🛠️ Setup and Installation

### 1️⃣ Prerequisites

Make sure you have the following installed on your system:

- **JDK 17+**: To run the Spring Boot application.
- **Maven**: For building the project.
- **Docker**: For containerization.
- **Postman** (optional): For API testing.




### 2️⃣ Clone the Repository
```bash
$ git clone https://github.com/demon-99/CustomerRegistration.git
$ cd User_Registration
```
### 3️⃣ Build the Project
Build the project using Maven.
```bash
$ mvn clean package
```

### 4️⃣ Run the Application (Without Docker)

To run the application locally without Docker, use:
```bash
$ mvn spring-boot:run
```
The application will start on http://localhost:8080.

### 5️⃣ Running with Docker
Build the Docker image for the application:
```bash
$ docker build -t user-registration-app .
```

Run the application in a Docker container:
```bash
$ docker run -p 8080:8080 user-registration-app
```
The application will start on http://localhost:8080.

## 🔗 API Endpoints

| Method  | Endpoint                   | Description            | Access  |
|---------|-----------------------------|------------------------|---------|
| **POST**  | `/api/users/register`        | Register a new user     | Public  |
| **POST**  | `/api/users/validate?email=johndoe@example.com&password=securepassword`        | Validate user login     | Public  |
| **GET**   | `/api/users/all`             | Get all users           | Admin   |
| **DELETE** | `/api/users/delete/{email}`  | Delete a user by email  | Admin   |

## 📜 Swagger API Documentation

Once the application is running, visit the following URL to interact with the API through a user-friendly interface:

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/v3/api-docs
## 🧪 Running Tests
To run unit tests, execute the following command:
```bash
$ mvn test
```
