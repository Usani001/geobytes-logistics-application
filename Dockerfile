FROM openjdk:17
LABEL authors="decagon"
ADD target/geobyte-logistics-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
EXPOSE 5432
ENTRYPOINT ["java", "-jar", "geobyte-logistics-app-0.0.1-SNAPSHOT.jar"]

