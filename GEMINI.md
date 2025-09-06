# GEMINI.md

## Project Overview

This is a Java-based web application that provides a customizable website navigation service. It's built with Spring Boot and uses Maven for dependency management. The frontend is rendered using the Beetl template engine. Key features include a complete backend for managing navigation links and user authentication handled by Apache Shiro. The application is designed to be deployed with Docker or run locally.

**Key Technologies:**

*   **Backend:** Java, Spring Boot, Spring MVC, Spring Data JPA
*   **Frontend:** Beetl, HTML, CSS, JavaScript
*   **Database:** MySQL
*   **Authentication:** Apache Shiro
*   **Build:** Maven

## Building and Running

### Prerequisites

*   Java 8
*   Maven
*   MySQL

### Local Development

1.  **Clone the repository:**
    ```bash
    git clone git@github.com:Nikati/WebStack-XML-Guns.git
    ```

2.  **Database Setup:**
    *   Create a new database named `webstack` with `utf8mb4` character set.
    *   Import the data from `sql/webstack.sql`.

3.  **Configure the application:**
    *   Open `src/main/resources/application.yml`.
    *   Set the `file-upload-path` to a local directory.
    *   Update the database connection details under `spring.datasource`. It is recommended to use environment variables for `DB_IP`, `DB_PORT`, `DB_USERNAME`, and `DB_PASSWORD`.

4.  **Build and run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The application will be available at `http://127.0.0.1:8000`.

### Docker Deployment

The project can be deployed using Docker. Refer to the `Dockerfile` and `README.md` for detailed instructions.

## Development Conventions

*   **Code Style:** The project follows standard Java coding conventions.
*   **API Documentation:** Swagger is used for API documentation and can be accessed at `/swagger-ui.html`.
*   **Testing:** The project contains a `test` directory, but no specific testing frameworks are explicitly defined in the `pom.xml`. It is recommended to add unit and integration tests.

## response 
gemini大模型回复均 采用中文进行回复
