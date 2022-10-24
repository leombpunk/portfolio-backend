FROM openjdk:17-ea-slim-buster 
EXPOSE 8080
ARG JAR_FILE=target/portfolio-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} springapi.jar
ENTRYPOINT [ "java", "-jar", "/springapi.jar" ]