FROM eclipse-temurin:21-jdk AS build
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y --no-install-recommends maven ca-certificates && rm -rf /var/lib/apt/lists/*

WORKDIR /app
# Build with the whole repo context so the module sees the parent POM and siblings
COPY . /app

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests -Dmaven.multiModuleProjectDirectory=/app -f /app/pom.xml -pl dispatch-service -am clean package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/dispatch-service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
