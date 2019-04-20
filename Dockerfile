FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/pilot_assistent-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://springboot-mongo:27017/pilot", "-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]



