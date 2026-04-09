# 🚀 Blog Post Management System (Backend)

A robust and scalable **Blog Post Management System Backend API** built using Spring Boot. This project handles authentication, blog operations, and image uploads efficiently, following clean architecture and real-world best practices.

---

## 📌 Overview

This backend system provides a complete REST API for managing blog posts with secure authentication.

### 🔥 Key Highlights:
- 🔐 JWT Authentication
- 📝 CRUD Operations for Blog Posts
- 🖼️ Image Upload Support
- 👤 User Authorization
- ⚡ Clean & Scalable Architecture

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot  
- **Security:** Spring Security + JWT  
- **Database:** PostgreSQL  
- **Build Tool:** Maven  
- **File Upload:** Multipart API 

---

## 📂 Project Structure

```bash
blog_post_management_system_backend/
│── src/
│ ├── controller/ # REST Controllers
│ ├── service/ # Business Logic
│ ├── repository/ # Database Layer
│ ├── entity/ # Entities / Models
│ ├── dto/ # Data Transfer Objects
│ ├── security/ # JWT & Security Config
│ └── config/ # App Configuration
│
│── resources/
│ ├── application.properties
│
│── pom.xml
│── README.md

```

---

## ✨ Features

### 🔐 Authentication & Authorization
- User Registration & Login
- JWT Token-based Authentication
- Secure API Access

### 📝 Blog Management
- Create Post
- Update Post
- Delete Post
- Get All Posts
- Get Single Post

### 🖼️ Image Upload
- Upload images with posts
- Store file path in database
- Multipart file handling

### ⚡ Security
- Protected APIs
- Token validation filter
- Unauthorized access protection

---

## 📡 API Endpoints

### 🔑 Auth APIs

| Method | Endpoint              | Description        |
|--------|----------------------|--------------------|
| POST   | `/api/auth/register` | Register user      |
| POST   | `/api/auth/login`    | Login user         |

---

### 📝 Post APIs

| Method | Endpoint              | Description        |
|--------|----------------------|--------------------|
| GET    | `/api/posts`         | Get all posts      |
| GET    | `/api/posts/{id}`    | Get single post    |
| POST   | `/api/posts`         | Create post        |
| PUT    | `/api/posts/{id}`    | Update post        |
| DELETE | `/api/posts/{id}`    | Delete post        |

---

## ⚙️ Installation & Setup

### 🔧 Prerequisites
- Java 17+
- Maven
- PostgreSQL

---

### 🚀 Steps to Run

```bash
# Clone the repository
git clone https://github.com/ARASIF1-6/blog_post_management_system_backend.git

# Navigate into project
cd blog_post_management_system_backend

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

```

## ⚙️ Configuration

Update your application.properties:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/blog_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key
jwt.expiration=86400000

```

## 🧪 Testing APIs

Use:
1. Postman

## 👉 Add header:
```bash
Authorization: Bearer <your_token>
```

## 📸 Example Request (Create Post)
```bash
POST /api/posts
Content-Type: multipart/form-data

Body:
- title: "My Blog"
- content: "This is content"
- image: (file)
```

## 🧠 Key Concepts Used
1. Dependency Injection
2. RESTful API Design
3. JWT Authentication Flow
4. Exception Handling
