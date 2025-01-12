FROM gradle:8.2.1-jdk17-alpine

COPY . .

RUN gradle build
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/plantas-0.0.1-SNAPSHOT.jar"]