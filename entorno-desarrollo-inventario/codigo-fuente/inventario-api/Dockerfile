FROM maven:3.9-eclipse-temurin-21

WORKDIR /inventario-api

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8081

CMD ["java", "-jar", "target/inventario-api-0.0.1-SNAPSHOT.jar"]