# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . /app
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests package
# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
