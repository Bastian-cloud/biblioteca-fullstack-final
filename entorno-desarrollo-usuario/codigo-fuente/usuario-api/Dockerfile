FROM maven:3.9-eclipse-temurin-21

WORKDIR /usuario-api

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8083

CMD ["java", "-jar", "target/usuario-api-0.0.1-SNAPSHOT.jar"]