# Task Manager

A web application for managing personal tasks, developed using Java and the Spring framework.

## About the Project

Task Manager allows users to create an account, log in, and manage their personal tasks. Each authenticated user can create tasks, view their task list, change task status, and delete tasks.

The application uses Spring Security for authentication and password encoding. Each task is associated with its owner, ensuring that users can access and manage only their own tasks.

## Features

- User registration and login
- Secure password storage using BCrypt
- Authentication and logout with Spring Security
- Create tasks with a title and description
- View personal tasks
- Mark tasks as completed or not completed
- Delete tasks
- Input validation
- HTML and CSS-based user interface
- H2 in-memory database

## Technologies Used

- **Java 8**
- **Spring Boot 2.7.18**
- **Spring MVC**
- **Spring Security 5.x**
- **Spring Data JPA**
- **Hibernate**
- **Thymeleaf**
- **H2 Database**
- **Maven**
- **HTML5**
- **CSS3**

## Requirements

- Java Development Kit (JDK) 8
- Apache Maven
- IntelliJ IDEA or another Java IDE (optional)

## Getting Started

### 1. Clone the Repository

Replace `YOUR_USERNAME` with your GitHub username.

```bash
git clone https://github.com/Angelina-Hryachko/task-manager
cd task-manager
```

### 2. Open the Project

Open the project in IntelliJ IDEA and allow Maven to import the dependencies.

### 3. Run the Application

Run the `TaskManagerApplication` class from your IDE.

Alternatively, if Maven is installed, execute:

```bash
mvn spring-boot:run
```

### 4. Open the Application

Navigate to:

http://localhost:8080

Register a new account and log in to start managing your tasks.

## Database

The application uses an H2 in-memory database.

Default connection settings:

- **JDBC URL:** `jdbc:h2:mem:taskdb`
- **Username:** `sa`
- **Password:** Empty, unless configured otherwise

The H2 Console is available at:

http://localhost:8080/h2-console

Since the database is stored in memory, its data is lost when the application stops.

## Project Structure

```text
task-manager/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    └── main/
        ├── java/
        │   └── taskmanager/
        │       ├── config/
        │       ├── controller/
        │       ├── model/
        │       ├── repository/
        │       ├── service/
        │       └── TaskManagerApplication.java
        ├── resources/
        │   └── application.properties
        └── webapp/
            └── WEB-INF/
                ├── views/
                │   ├── login.html
                │   ├── register.html
                │   └── tasks.html
                └── css/
                    ├── auth.css
                    └── tasks.css
```

### Main Components

- **config** — Spring Security and application configuration
- **controller** — handles HTTP requests
- **model** — JPA entities representing users and tasks
- **repository** — database access using Spring Data JPA
- **service** — application business logic
- **WEB-INF/views** — HTML view templates
- **WEB-INF/css** — CSS stylesheets
- **application.properties** — application and database configuration

## Security

- Passwords are encoded using BCrypt.
- Spring Security manages user authentication and logout.
- Tasks are associated with individual user accounts.
- Users can manage only their own tasks.

## License

This project was developed for educational purposes.