FROM amazoncorretto:11-alpine-jdk
EXPOSE 8080
COPY target/chatapp-0.0.1-SNAPSHOT.jar chatapp-0.0.1.jar
ENTRYPOINT ["java","-jar","/chatapp-0.0.1.jar"]