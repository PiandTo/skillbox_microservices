FROM openjdk:17
# EXPOSE 8080
COPY target/rest_api-0.0.1-SNAPSHOT.jar rest_api.jar
ENTRYPOINT [ "java", "-jar", "rest_api.jar" ]