FROM maven:3.9-eclipse-temurin-21

WORKDIR /categoria

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8087

CMD ["java", "-jar", "target/categoria-0.0.1-SNAPSHOT.jar"]