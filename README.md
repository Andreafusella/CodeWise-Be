![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)

# CodeWise-Be 📚

## Objective

The CodeWise-Be project is a Spring Boot backend application for managing data related to students 🧑‍🎓, teachers 👩‍🏫, courses 📖, exams 📝, resources 📂, attachments 📎, notes 📌, and evaluations ✅.
It enables the creation, visualization, deletion, and association of these entities, and provides functionalities for generating reports in CSV, Excel 📊, and PDF 📄 formats.

## Technologies

The project is developed using the following main technologies:

* **Spring Boot:** Framework for building standalone, production-ready Java applications. 🌱
* **Maven:** Dependency management and build tool. 🛠️
* **Java 21:** Programming language. ☕
* **Spring Data JPA:** For data access and entity management. 💾
* **PostgreSQL:** Relational database. 🐘
* **Lombok:** Library for reducing boilerplate code. ✨
* **OpenCSV:** For generating CSV files. 📊
* **Springdoc OpenAPI:** For API documentation generation (Swagger UI). 📖
* **Dotenv-java:** For loading environment variables from a `.env` file. 🔑
* **iText7 Core:** or generating PDF files. 📄
* **Apache POI:** For generating Excel (XLSX) files. 📈

## Architecture

The project follows a layered architecture typical of Spring Boot applications:

* **Controller Layer:** Handles incoming HTTP requests and returns responses. 🌐
* **Service Layer:** Contains the business logic of the application. ⚙️
* **Repository Layer:** Interacts with the database using Spring Data JPA. 🗃️
* **Model Layer:** Defines the database entities. 🧱
* **DTOs (Data Transfer Objects):** Used to transfer data between different layers. 📦

```
.
├── .idea
├── .mvn
├── db/
│   └── createTable
├── logs/
│   └── application.log
├── src/
│   └── main/
│       ├── java/
│       │   └── com.codeWise.codeWise/
│       │       ├── controller/
│       │       ├── dto/
│       │       ├── exception/
│       │       ├── model/
│       │       ├── repository/
│       │       ├── service/
│       │       ├── util/
│       │       ├── CodeWiseApplication
│       │       └── DataLoadRunner
│       └── resource/
│           ├── application.properties
│           └── banner.txt
├── .env
├── .gitignore
├── Dockerfile
├── pom.xml
└── Readme.md
```

## Security and Authentication

Security and authentication details are not explicitly implemented or required in the current version of the project. 🔒

## Main Features

* Student Management (Create ✨, View 👁️, Delete 🗑️, Associate/Disassociate with Courses 🔗)
* Teacher Management (Create ✨, View 👁️, Delete 🗑️)
* Course Management (Create ✨, View 👁️, Delete 🗑️)
* Exam Management (Create ✨, View 👁️, Delete 🗑️)
* Paper Management (Create ✨, View 👁️, Delete 🗑️)
* Evaluation Management (Create ✨, View 👁️, Delete 🗑️)
* Resource Management (Create ✨, View 👁️, Delete 🗑️)
* Student-Resource Association Management (Create ✨, View 👁️, Delete 🗑️)
* Attachment Management (Create ✨, View 👁️, Delete 🗑️)
* Note Management (Create ✨, View 👁️, Delete 🗑️)

## Additional Features

* Generate a CSV file with teacher data. 📄➡️📊
* Generate a PDF file with detailed information about a single student. 🧑‍🎓➡️📄
* Generate an Excel file with information about courses and associated exams, including the number of enrolled students. 📖📝➡️📈

## Testing

The project contains a basic Spring Boot context test class `CodeWiseApplicationTests` No specific unit or integration tests for individual functionalities are provided. 🧪

## How to Run the Project

1.  Ensure you have installed Java 21 ☕ and Maven 🛠️.
2.  Set up the PostgreSQL 🐘 database and update the `application.properties` file with the correct credentials, or configure the environment variables `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`. 🔑
3.  **Before running the application, initialize the database** by executing the SQL script located in the `db/createTable` folder. Use a PostgreSQL client to create the necessary tables and insert initial data. 🔧
4.  Navigate to the root directory of the CodeWise-Be project. 📁
5.  Build the project with Maven: `mvn clean install` 🏗️
6.  Run the application using the Spring Boot Maven Plugin: `mvn spring-boot:run` ▶️
7.  The application will be available at `http://localhost:8000`.API documentation (Swagger UI) will be available at `http://localhost:8000/swagger-ui.html`. 🌐

## Initial Dataset

The `DataLoadRunner.java` class automatically populates the database with an example dataset when the application starts. SEED 🌱

## Design Patterns Used

From the project structure, you can identify common design patterns typical of Spring Boot applications:

* **MVC (Model-View-Controller):** Although there is no "View" layer, the separation between Controller, Service, and Repository reflects this pattern. 🔄
* **Repository Pattern:** Used for data access. 🗄️
* **Dependency Injection:** Managed by the Spring Framework. 💉

## Final Notes

The project is configured to use Spring Boot DevTools for faster application restarts during development. 🔥
Logging is configured to write both to the console and to a file. 📜
