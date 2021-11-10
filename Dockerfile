FROM openjdk:8-jdk-alpine
COPY target/pica-location-0.0.1-SNAPSHOT.jar pica-location.jar
ENTRYPOINT ["java", "-jar", "pica-location.jar"]