FROM openjdk:17-slim

WORKDIR /app
COPY build/libs/plantas-0.0.1-SNAPSHOT.jar /app/plantas-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/app/plantas-0.0.1-SNAPSHOT.jar"]