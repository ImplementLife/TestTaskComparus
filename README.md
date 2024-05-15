## Task notes

The task for Comparus

<https://comparus.de/ua/>

[Requirements](requirements.pdf)

### Position: Middle Java Developer

### Stack: 
- Java 17
- Spring Boot
- OpenApi
- Lombok
- Docker
- JUnit

## How to run and use?
Before run, you need modify [application.yaml](src/main/resources/application.yaml) data-source

Then you can run the application from cmd, use follow command:
```bash
mvn spring-boot:run
```
### Simply initializing and startup with Docker
If you have Docker, you can run next cmd command:
```bash
docker-compose up -d
```

OpenApi link: http://localhost:8080/swagger-ui/index.html