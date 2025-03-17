# QuizApp - Microservices Architecture

## Overview
QuizApp is a microservices-based application built using **Spring Boot**. It allows users to generate and take quizzes by fetching questions from a PostgreSQL database. The system follows a **microservices architecture** where:

- **Quiz Service** handles quiz generation and evaluation.
- **Question Service** manages the question bank and provides questions.
- **Service Registry (Eureka)** is used for service discovery.
- **API Gateway (Spring Cloud Gateway)** is used for routing requests.

## Architecture
The application consists of the following microservices:

1. **Quiz Service**  
   - Calls **Question Service** to fetch questions and calculate scores.  
   - Manages quiz creation and submission.  

2. **Question Service**  
   - Stores and manages a database of questions.  
   - Provides APIs to fetch random questions for quizzes.  
   - Evaluates submitted quiz responses and returns the score.  

3. **Service Registry (Eureka Server)**  
   - Registers and discovers microservices dynamically.  
   - Enables load balancing and fault tolerance.  

4. **API Gateway**  
   - Routes requests to appropriate services.  
   - Provides a unified entry point for all microservices.  

## Tech Stack
- **Backend:** Java, Spring Boot  
- **Database:** PostgreSQL  
- **Service Discovery:** Eureka Server  
- **API Gateway:** Spring Cloud Gateway  
- **Inter-Service Communication:** OpenFeign  

## Setup & Installation
### Prerequisites
- Java 23  
- PostgreSQL  
- Maven  
- IntelliJ or any IDE  
- Docker (Optional)  

### Steps to Run
1. **Clone the repository**  
   ```sh
   git clone https://github.com/your-repo/quizapp-microservices.git
   cd quizapp-microservices


API Endpoints
Quiz Service
POST /quiz/generate → Generate a new quiz
POST /quiz/submit → Submit quiz responses
Question Service
GET /questions/allquestions → Fetch all questions
GET /questions/generate → Get random questions

Future Enhancements
Add authentication and user management
Implement caching for performance optimization
Deploy it.
