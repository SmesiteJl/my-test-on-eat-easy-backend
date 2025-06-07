FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY build/libs/authentication-service.jar app.jar
COPY build/resources/test/security/ /app/security/

RUN ls -la /app/security/

ENTRYPOINT ["java", "-jar", "app.jar"]