FROM eclipse-temurin:17
RUN mkdir /app
COPY /target/EurekaServer-0.0.1.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]