FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests --fail-never

FROM openjdk:17
COPY --from=build /target/geobyte-logistics-app-0.0.1-SNAPSHOT.jar geobyte-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "geobyte-app.jar"]

