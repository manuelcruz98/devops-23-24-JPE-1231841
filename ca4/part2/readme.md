# DevOps Technical Report | Class Assignment 4 - Part 2

## Part 2 - Containers with Docker

### Introduction
The goal of this assignment is to use Docker to set up a containerized environment to execute our version of the gradle version of the spring basic tutorial application.

### Requirements
1. Use docker-compose to produce two containers:
* web: This container is used to run Tomcat and spring application.
* db: This container is used to execute H2 server database.
2. Publish the images (db and web) to Docker Hub.
3. Use a volume with db container to get a copy of the database file by using the exec to run a shell in the container and copying the database file to the volume.
4. Include all the Docker files in the repository.
5. Tag the repository with the tag ca4-part2.

### Implementation
1. Clone the directory of CA2 part2 to the CA4 part2 directory.
```bash
mkdir -p ca4/part2
cp -r ca2/part2/* ca4/part2
```

2. Create a Dockerfile for the database container.
```dockerfile
FROM gradle:jdk21

WORKDIR /opt/h2

RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar -O h2.jar


EXPOSE 8082
EXPOSE 9092

CMD ["java", "-cp", "h2.jar", "org.h2.tools.Server", "-ifNotExists", "-web", "-webAllowOthers", "-webPort", "8082", "-tcp", "-tcpAllowOthers", "-tcpPort", "9092"]
```
3. Create a Dockerfile for the web container.
```dockerfile
# Começa a partir da imagem oficial do Gradle com OpenJDK 21
FROM gradle:jdk21

# Define o diretório de trabalho
WORKDIR /app

# Copia o código fonte para o diretório de trabalho
COPY . /app

# Executa o comando de build do Gradle para compilar e construir a aplicação
RUN gradle clean build

# Exponha a porta que a aplicação irá usar
EXPOSE 8080

# Define o ponto de entrada que o Docker irá executar ao iniciar o container
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
```

4. Create a docker-compose.yml file to define the services.
```yaml
services:
  db:
    build:
      context: .
      dockerfile: Dockerfile_db
    container_name: h2-db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - h2-data:/opt/h2-data

  web:
    build:
      context: .
      dockerfile: Dockerfile_web
    container_name: spring-web
    ports:
      - "8080:8080"
    depends_on:
      - db

volumes:
  h2-data:
    driver: local
```

5. Change the application.properties file to use the host machine IP address.
```properties
spring.datasource.url=jdbc:h2:tcp://db:9092/./jpadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
```

6. Build the images and run the containers.
```bash
docker-compose up 
```
![H2 Database](images/h2.png)
![Spring Application](images/spring.png)

7. Publish the images to Docker Hub.
* Web image
* Login in Docker
```bash
docker login
```

* Tag the image
```bash
docker tag part2-web mane21/part2-web
```
* Push the image
```bash
docker push man21/part2-web
```
* Now the image is available in here:
  https://hub.docker.com/u/mane21

* DB image
* Login in Docker
```bash
docker login
```

* Tag the image
```bash
docker tag part2-db mane21/part2-db
```
* Push the image
```bash
docker push mane21/part2-db
```

* Now the image is available in here:
  https://hub.docker.com/u/mane21

![Docker Hub](images/dockerhub.png)

8. Tag the repository with the tag ca4-part2.
```bash
git tag ca4-part2
git push origin ca4-part2
```
### Conclusion
In this assignment, we successfully set up a containerized environment using Docker to run a Gradle-based Spring application with an H2 database.

Additionally, we can guarantee that Docker ensures that the application runs consistently across different environments by encapsulating its containers. This, will reduce conflicts and simplifies dependency management.