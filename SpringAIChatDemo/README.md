# Spring AI Chat Demo

This document provides a brief overview of the project and how to get started.

## Project Structure
The project is a standard Maven project with the following structure:
- `src/main/java`: Contains the main source code.
  - `controller`: Contains the REST controllers for interacting with the LLMs.
- `src/main/resources`: Contains the application properties and other resources.
- `pom.xml`: The project's Maven configuration file.

## Features
- Connect to OpenAI's GPT models.
- Provides REST endpoints for each LLM.

## Prerequisites
- Java 17 or later.
- Maven 3.6.3 or later.
- An API key for OpenAI.

### Step by Setup and run swagger
1. Clone the repository.
2. Open the project in your favorite IDE.
3. Add your OpenAI API key to `application.properties`.
4. Run the application.
5. Access the Swagger UI at `http://localhost:8080/swagger-ui.html`.

## License
This project is licensed under the MIT License.

## Acknowledgements
- The Spring AI team.
- The developers of OpenAI.

## Contact
For any questions or feedback, please contact at bks.nyk@gmail.com.

---

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.0/maven-plugin/build-image.html)
* [OpenAI](https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.0/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
