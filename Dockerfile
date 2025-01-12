FROM amazoncorretto:17-alpine-jdk
COPY . .
ENTRYPOINT ["java","-jar","build/libs/plantas-0.0.1-SNAPSHOT.jar"]