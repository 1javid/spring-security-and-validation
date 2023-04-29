# My Spring Security and Validation Application

This is a Spring Boot application that demonstrates how to build a web application with Spring Security and request validation. It includes examples of how to secure endpoints and validate incoming requests to ensure data integrity and prevent unauthorized access.

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine.

### Prerequisites

- JDK 11 or later installed (pom.xml is set to JDK 17)
- Maven 3.8.7 or later installed

### Installing

1. Clone the repository:

```bash
git clone https://github.com/1javid/spring-security-and-validation.git
```

2. Build the project:

```bash
cd demo/
./mvnw clean install
```

This will compile the application and create an executable JAR file.

3. Run the application:

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

or

```bash
./mvnw spring-boot:run
```

This will start the Spring Boot application on `http://localhost:8080`.

## Usage

To use the application, you can send HTTP requests. You can use a tool like Postman to do this.

For example, to retrieve all the users, send a GET request to `http://localhost:8080/user/communities` (you need to be logged in first).

## Configuration

The application can be configured by modifying the `application.properties` file in the `src/main/resources` directory. You can change the server port, database settings, and other application properties there.

## Built With

- Spring Boot - The web framework used
- Maven - Dependency management

## Authors

- Javid Alakbarli

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
