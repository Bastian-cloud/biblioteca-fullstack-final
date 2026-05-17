FROM maven:3.9-eclipse-temurin-21

WORKDIR /notificacion

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8089

CMD ["java", "-jar", "target/notificacion-0.0.1-SNAPSHOT.jar"]