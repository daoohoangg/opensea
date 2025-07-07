# syntax=docker/dockerfile:1

###########################
# Stage 1: Download Deps
###########################
FROM eclipse-temurin:21-jdk-jammy as deps

WORKDIR /app

COPY --chmod=755 mvnw mvnw
COPY .mvn/ .mvn/
COPY pom.xml .

RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw dependency:go-offline -B -DskipTests

###########################
# Stage 2: Build JAR
###########################
FROM deps as build

COPY src ./src

RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw package -B -DskipTests && \
    mv target/*.jar app.jar

###########################
# Stage 3: Extract Layers
###########################
FROM build as extract

RUN java -Djarmode=layertools -jar app.jar extract --destination extracted

###########################
# Stage 4: Final Runtime
###########################
FROM eclipse-temurin:21-jre-jammy as final

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

USER appuser
WORKDIR /app

COPY --from=extract /app/extracted/dependencies/ ./
COPY --from=extract /app/extracted/spring-boot-loader/ ./
COPY --from=extract /app/extracted/snapshot-dependencies/ ./
COPY --from=extract /app/extracted/application/ ./

EXPOSE 8083

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
