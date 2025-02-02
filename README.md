User Registration API

📌 Overview

This project is a Spring Boot-based User Registration and Authentication API. It provides functionalities for:

User registration

User authentication

Role-based access control (RBAC) using Spring Security

API documentation using Swagger

Containerization using Docker and Docker Compose

🚀 Technologies Used

Java 17

Spring Boot (Spring Security, Spring Data JPA, Spring Web)

H2 / PostgreSQL Database

Springfox Swagger (API Documentation)

Docker & Docker Compose

JUnit & Mockito (for Unit Testing)

🛠️ Setup and Installation

1️⃣ Prerequisites

Ensure you have installed:

JDK 17+

Maven

Docker

Postman (for API testing)

2️⃣ Clone the Repository

$ git clone https://github.com/your-repo-url.git
$ cd User_Registration

3️⃣ Build the Project

$ mvn clean package

4️⃣ Run the Application (Without Docker)

$ mvn spring-boot:run

The application should start on http://localhost:8080.

🐳 Running with Docker

1️⃣ Build Docker Image

$ docker build -t user-registration-app .

2️⃣ Start the Application using Docker Compose

$ docker-compose up -d

3️⃣ Stop the Containers

$ docker-compose down

🔗 API Endpoints

Method

Endpoint

Description

Access

POST

/api/users/register

Register a new user

Public

POST

/api/users/validate

Validate user login

Public

GET

/api/users/all

Get all users

Admin

DELETE

/api/users/delete/{email}

Delete a user by email

Admin

📜 Swagger API Documentation

Once the application is running, visit:

http://localhost:8080/swagger-ui/index.html

This provides a user-friendly interface to interact with the API.

🧪 Running Tests

To run unit tests:
