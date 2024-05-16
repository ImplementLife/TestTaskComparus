## Task notes

The test task for Comparus
<br/><https://comparus.de/ua/>
<br/>[Requirements](requirements.pdf)

#### Position: Middle Java Developer

### Tech Stack: 
- Java 17
- Spring Boot
- Lombok
- OpenApi
- Docker
- JUnit

## How to run and use?
### 1. Simply initializing and startup databases in Docker
If you want just run app and have Docker, you can run next cmd command:
```
docker-compose -p implement_life_test_task_databases up -d
```
### 2. Manual setup connect to databases
If you want to connect to other exist databases skip step 1 and do next:
<br/>Before run you need modify [application.yaml](src/main/resources/application.yaml) data-source:
<br/>change or add similar databases configurations

In current revision supported next databases:

- PostgreSQL
- MySQL

You can define driver in the 'strategy' property with next possible values:
- postgres
- mysql

### 3. Run application
Then you can run the application from cmd, use follow command:
```bash
mvn spring-boot:run
```

## OpenApi
Default swagger link: http://localhost:8080/swagger-ui/index.html