# 📚 Library Management System (Spring Boot)

A complete backend system for managing library operations built with **Spring Boot**, featuring **Spring Security**, **Custom Authentication**, **JPA/Hibernate**, and **REST APIs** for handling books, members, and borrow records.

---

## 🚀 Features
- 🔐 **Spring Security**
  - Basic Auth
  - Custom `UserDetailsService`
  - Role-based access control (ADMIN / USER)
- 📘 **Book Management**
  - Add, update, delete, list books
- 👤 **Member Management**
  - Create, update, delete members (Admin only)
- 📄 **Borrow Records**
  - Borrow/Return books
  - Track active borrow records
- 🗄️ MySQL Database with JPA/Hibernate
- 🧪 Postman-ready API endpoints

---

## 🏗️ Technologies Used
- Java 17  
- Spring Boot 3  
- Spring Security  
- Spring Data JPA  
- MySQL  
- Lombok  
- Maven  
- Postman (API testing)

---

## 📁 Project Structure
src
└── main
├── java/com/samir/librarymanagement
│ ├── config
│ ├── controller
│ ├── entity
│ ├── repository
│ ├── service
│ └── security
└── resources
├── application.properties
└── data.sql (optional)

yaml
Copy code

---

## 🔐 Security Access Rules
| Endpoint                     | Method | Role      |
|------------------------------|--------|-----------|
| `/register`, `/login`        | ANY    | Public    |
| `/api/books/**`              | GET    | USER,ADMIN|
| `/api/books/**`              | POST   | ADMIN     |
| `/api/books/**`              | PUT    | ADMIN     |
| `/api/books/**`              | DELETE | ADMIN     |
| `/api/members/**`            | ANY    | ADMIN     |
| `/api/borrow/**` (POST,PUT)  | USER   | USER      |
| `/api/borrow/**` (GET)       | GET    | ADMIN     |

---

## 🧪 API Testing (Postman)

### ✔ Register
**POST** `/register`  
```json
{
  "userName": "samir",
  "password": "12345",
  "role": "ADMIN"
}
✔ Login (Basic Auth)
Use your username/password in Postman Authorization → Basic Auth

✔ Create Borrow Record
POST /api/borrow/create

json
Copy code
{
  "memberId": 1,
  "bookId": 4,
  "returnDate": "2025-01-15"
}
🛠️ Run Locally
1️⃣ Clone the repo
bash
Copy code
git clone https://github.com/samir22320/library-management-system.git
2️⃣ Configure Database
Edit application.properties:

ini
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
3️⃣ Run the application
arduino
Copy code
mvn spring-boot:run
👨‍💻 Author
Samir Ahmed
Backend Developer — Java & Spring Boot

⭐ Support
If you like this project, give it a ⭐ on GitHub!
