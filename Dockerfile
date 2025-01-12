FROM amazoncorretto:17-alpine-jdk
COPY build/libs/plantas-0.0.1-SNAPSHOT.jar plantas-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","build/libs/plantas-0.0.1-SNAPSHOT.jar"]