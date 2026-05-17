FROM maven:3.9-eclipse-temurin-21

WORKDIR /autor

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8088

CMD ["java", "-jar", "target/autor-0.0.1-SNAPSHOT.jar"]