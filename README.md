#  Knowledge Sharing Platform – Backend
Spring Boot based REST API backend for an AI-powered Knowledge Sharing Platform.
This backend handles authentication, article management, AI integration, and secure API communication.

##  Architecture Overview

The backend follows a layered architecture:

- Controller Layer → Handles HTTP requests and responses  
- Service Layer → Contains business logic  
- Repository Layer → Communicates with database using Spring Data JPA  
- Security Layer → JWT-based authentication & authorization  
- AI Integration Layer → OpenAI API integration for content improvement, tag suggestion & summary generation  


## Folder Structure
src/main/java/com/knowledgeplatform/
│
├── controller
├── service
├── serviceImpl
├── repository
├── entity
├── security
└── dto

## 🔹 Key Design Decisions

- Used JWT authentication for stateless security  
- Implemented layered architecture for scalability  
- Used RESTful API design principles  
- Integrated OpenAI using RestTemplate  
- Stored API keys securely using environment variables  
- Separated frontend and backend into independent repositories  

#  AI Usage 

## AI Tools Used
- ChatGPT
- Cursor AI

## How AI Was Used

- Used ChatGPT to understand Spring Boot layered architecture and JWT authentication flow.
- Debugged OpenAI API integration and resolved response parsing issues using ChatGPT.
- Refined prompt engineering for content improvement, tag suggestion, and summary generation.
- Clarified Spring Security filter chain behavior and authentication context handling.
- Verified JPA entity relationships and repository query design.
- Improved REST API endpoint structuring after AI discussion.
- Used Cursor AI for small code suggestions and boilerplate generation.
- Resolved GitHub secret detection issue after understanding security best practices.
- Improved search and filter implementation logic with AI assistance.
- Manually reviewed and corrected all AI-assisted code to ensure accuracy and security.  

##  Manual Review & Corrections

- Secured API key using environment variables  
- Improved JWT validation logic  
- Corrected OpenAI response parsing  

#  AI Features Implemented

✅ Improve Article Content  
→ Fix grammar and enhance clarity  

✅ Suggest Tags  
→ Automatically generate 5 relevant tags  

✅ Generate Summary  
→ Provide concise 2–3 sentence summary  

---

#  Authentication

JWT-based authentication implemented using:

- JwtUtil  
- JwtAuthFilter  
- Spring Security  

All protected APIs require:

Authorization: Bearer <token>

#  Technologies Used

- Java 17  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- MySQL  
- OpenAI API  
- Lombok  
- Maven  

IDE Used:
- STS 4.30.0  

Database Tool:
- MySQL Workbench  


# ⚙ 3️⃣ Setup Instructions

## 📋 Prerequisites

- Java 17  
- Maven  
- MySQL  
- Git  

##  Environment Variables

Create an environment variable:

OPENAI_API_KEY=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



##  Database Setup

1. Create MySQL database:

CREATE DATABASE knowledge_platform;

2. Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/knowledge_platform  
spring.datasource.username=root  
spring.datasource.password=your_password  

openai.api.key=${OPENAI_API_KEY}

##  Run Backend

Inside backend folder:

mvn clean install  
mvn spring-boot:run  

Server runs on:

http://localhost:8080

#  API Endpoints

## Authentication

POST /api/auth/register  
POST /api/auth/login  

## Articles

GET /api/articles  
POST /api/articles  
PUT /api/articles/{id}  
DELETE /api/articles/{id}  

## AI Endpoints

POST /api/ai/improve  
POST /api/ai/suggest-tags  
POST /api/ai/summary  

---

#  Future Improvements

- Role-based authorization  
- Redis caching  
- Docker deployment  
- CI/CD pipeline  
- Extended unit testing  



#  Author

Harshali Patil  

