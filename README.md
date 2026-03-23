# WhatsApp Backend — Java Spring Boot Microservices

A production-inspired WhatsApp backend built with Java Spring Boot Microservices architecture.

## 🏗️ Architecture
```
Client → API Gateway (8080)
           ├── auth-service (8081)
           ├── user-service (8082)
           └── chat-service (8083)
```

## 🚀 Microservices

### 1. auth-service (Port 8081)
- User registration with BCrypt password hashing
- JWT token generation and validation
- Spring Security configuration

### 2. user-service (Port 8082)
- User profile management
- Online/Offline status tracking
- Last seen timestamp

### 3. chat-service (Port 8083)
- 1-to-1 messaging
- Group chat creation and management
- Message status (SENT, DELIVERED, READ)

### 4. api-gateway (Port 8080)
- Single entry point for all services
- Request routing to appropriate microservice

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 17 | Primary language |
| Spring Boot 3.x | Application framework |
| Spring Security | Authentication & Authorization |
| JWT (JJWT 0.11.5) | Token-based authentication |
| Spring Data JPA | Database ORM |
| Hibernate | JPA implementation |
| MySQL 8.0 | Database |
| Spring Cloud Gateway | API Gateway |
| Lombok | Boilerplate reduction |
| Maven | Build tool |

## 📦 Prerequisites

- Java 17+
- MySQL 8.0+
- Maven 3.6+

## ⚙️ Setup & Run

### 1. Clone the repository
```bash
git clone https://github.com/MonikaVats21/WhatsApp-backend.git
```

### 2. Create databases in MySQL
```sql
CREATE DATABASE whatsapp_auth_db;
CREATE DATABASE whatsapp_user_db;
CREATE DATABASE whatsapp_chat_db;
```

### 3. Update application.properties in each service
```properties
spring.datasource.password=your_mysql_password
```

### 4. Run services in order
```bash
# Terminal 1
cd auth-service && mvn spring-boot:run

# Terminal 2
cd user-service && mvn spring-boot:run

# Terminal 3
cd chat-service && mvn spring-boot:run

# Terminal 4
cd api-gateway && mvn spring-boot:run
```

## 🔑 API Endpoints

### Auth Service
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login and get JWT token |
| GET | /api/auth/health | Health check |

### User Service
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/users | Create user profile |
| GET | /api/users/{id} | Get user by ID |
| GET | /api/users | Get all users |
| PUT | /api/users/{id}/status | Update online status |
| PUT | /api/users/{id}/profile | Update profile |

### Chat Service
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/chat/send | Send 1-to-1 message |
| GET | /api/chat/history/{id1}/{id2} | Get chat history |
| PUT | /api/chat/{id}/read | Mark as read |
| GET | /api/chat/unread/{userId} | Get unread messages |
| POST | /api/chat/group | Create group |
| POST | /api/chat/group/send | Send group message |

## 👩‍💻 Author
**Monika Vats** — Java Developer
- GitHub: [@MonikaVats21](https://github.com/MonikaVats21)
