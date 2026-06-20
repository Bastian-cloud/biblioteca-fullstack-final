FROM maven:3.9-eclipse-temurin-21

WORKDIR /prestamo-api

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8082

CMD ["java", "-jar", "target/prestamo-api-0.0.1-SNAPSHOT.jar"]