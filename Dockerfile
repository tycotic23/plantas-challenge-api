FROM amazoncorretto:17-alpine-jdk
COPY . .
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/plantas-0.0.1-SNAPSHOT.jar"]