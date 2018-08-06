# Differ, the diff API

### What it is
Differ is an API that exposes endpoints to configure and calculate differences between two given values `left` and `right`.

### Under the hood
Chosen technologies include:

- [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html): no introduction needed, it's Java.
- [Spring Boot 2](https://spring.io/projects/spring-boot): distilled love in form of code
- [Docker](https://docs.docker.com/) and [Docker Compose](https://docs.docker.com/compose/): deployment and local environment setup
- [MySQL](https://dev.mysql.com/): database where the API sits upon
- [Gradle](https://gradle.org/): building and dependency management
- [Diff Utils](https://github.com/wumpz/java-diff-utils): responsible for diff calculation

### Endpoints

Two endpoints are provided by this API.

#### POST /v1/diff/{id}/{side}

Used to create or update a *diffable*. Parameters are the following:

- **id** (path variable) is some random identifier you will use latter to invoke the difference calculation.
- **side** (path variable) is the position of the value you're configuring; possible values are *left*  or *right*.
- **request body**: pass as request body the value you want to associate to the side specified by **side** path variable

Example:
```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d 'this is the  left side' 'http://localhost:8080/v1/diff/abc/left'
```

#### GET /v1/diff/{id}

This endpoint will calculate and return the difference between the *left* and *right* values.

If you call this method on an incomplete *diffable* (missing either right or left value), the API will return a *422 Unprocessable Entity* status code with a comprehensive error message.

If there's no *diffable* with the requested id, a *404 Not Found* status code and comprehensive message are returned.

Example:
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/v1/diff/abc'
```

#### Swagger is available!

For detailed information, including return types and examples, there's a Swagger documentation available under:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Building and running

#### Basic infrastructure

API uses MySQL, which is provided by docker-compose.yml. To start it up use:

`docker-compose down --remove-orphans && docker-compose up --build`

An optional `-d` parameter is accepted if you want to start the container in daemon mode.

#### Launching the API

First, build the project:
```
./gradlew clean build
```
Then launch Spring Boot's jar:
```
java -jar api/build/libs/api-0.0.1-SNAPSHOT.jar
```
