# URL Shortener API with Spring Boot and Jenkins CI/CD

This project is a classic backend service that provides a fully functional URL shortening API. It's built with Spring Boot and includes a complete Continuous Integration (CI) pipeline managed by Jenkins.

## 🚀 Project Overview

This application serves two main purposes:
1.  **Shorten URLs:** It accepts a long URL and returns a unique, 6-character short code.
2.  **Redirect URLs:** It accepts a short code and redirects the user to the original long URL.

The entire development process is managed using Agile principles, with tasks and user stories tracked on a GitHub Projects Kanban board.

---

## ✨ Features

-   **RESTful API:** Clean, simple endpoints for shortening and redirecting URLs.
-   **In-Memory Storage:** Uses a simple, thread-safe `ConcurrentHashMap` for high-speed lookups without needing a database.
-   **Automated CI Pipeline:** The `Jenkinsfile` defines a complete pipeline that automatically builds and tests the application on every code change.
-   **Unit Tested:** Includes comprehensive unit tests to ensure the reliability of both API endpoints.

---

## 🛠️ Technologies Used

-   **Backend:** Java 17, Spring Boot
-   **Build Tool:** Maven
-   **CI/CD:** Jenkins, Docker
-   **Project Management:** Agile (Kanban), GitHub Projects

---

## ⚙️ How to Run Locally

1.  Clone the repository:
    ```bash
    git clone [https://github.com/Pratishtha274/springboot_url_shortener.git](https://github.com/Pratishtha274/springboot_url_shortener.git)
    ```
2.  Navigate into the project directory:
    ```bash
    cd springboot_url_shortener
    ```
3.  Run the application using the Maven wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
The application will be running at `http://localhost:8080`.

---

## API Usage

### Shorten a URL
-   **Method:** `POST`
-   **Endpoint:** `/shorten`
-   **Body:** The long URL as plain text (e.g., `https://www.google.com`)

### Redirect to Original URL
-   **Method:** `GET`
-   **Endpoint:** `/{shortCode}` (e.g., `/aB1cD2`)
