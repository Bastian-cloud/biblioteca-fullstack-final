FROM maven:3.9-eclipse-temurin-21

WORKDIR /auth-api

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8084

CMD ["java","-jar","target/auth-api-0.0.1-SNAPSHOT.jar"]