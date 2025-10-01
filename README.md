# 🌐 URL Shortener API with Spring Boot + Jenkins CI/CD

A minimal **URL shortener service** built with Spring Boot and fully automated with a Jenkins CI/CD pipeline.  
It allows you to shorten long URLs into 6-character codes and redirect back to the original URL.  
Perfect for learning **Spring Boot backend development** and **Jenkins CI/CD automation**.

---

## 🚀 Features

- **RESTful API**
    - `POST /shorten` → generates short URL
    - `GET /{shortCode}` → redirects to original URL
- **In-Memory Store** using `ConcurrentHashMap` (thread-safe, simple demo without DB)
- **Unit Tests** written with JUnit + MockMvc
- **CI/CD Pipeline** with Jenkins (Checkout → Build → Test → Clean)
- **Tech stack**: Java 17, Spring Boot 3, Maven, Jenkins, (optional Docker/Ngrok)

---

## ⚙️ Getting Started

### 🔧 Prerequisites
- Java 17 installed (`java -version` should show 17)
- Git installed
- Optional: Docker, Jenkins, Ngrok for CI/CD demo

---

### ▶️ Run Locally

```bash
git clone https://github.com/<your-username>/springboot_url_shortener.git
cd springboot_url_shortener

# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

---

trigger: Wed Oct  1 13:07:00 IST 2025
