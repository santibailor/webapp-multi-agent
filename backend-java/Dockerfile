# ---- BUILD STAGE ----
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY backend-java/pom.xml .
COPY backend-java/src ./src
# Copy openapi.yaml to /docs/ so pom.xml's ${project.basedir}/../docs/openapi.yaml resolves correctly
COPY docs/openapi.yaml /docs/openapi.yaml

# Download dependencies first (leverages Docker cache)
RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline -B --no-transfer-progress 2>/dev/null || true

# Build the JAR (skip tests; tests are run in CI separately)
RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests -B --no-transfer-progress

# Extract layers for optimised Spring Boot startup
RUN java -Djarmode=layertools -jar target/*.jar extract --destination /build/layers

# ---- RUNTIME STAGE ----
FROM eclipse-temurin:21-jre-jammy AS runtime

# Security: run as non-root
RUN groupadd --system helix && useradd --system --gid helix helix
USER helix

WORKDIR /app

# Copy Spring Boot layers in order (most stable first)
COPY --from=builder /build/layers/dependencies/ ./
COPY --from=builder /build/layers/spring-boot-loader/ ./
COPY --from=builder /build/layers/snapshot-dependencies/ ./
COPY --from=builder /build/layers/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-Djava.security.egd=file:/dev/./urandom", \
  "org.springframework.boot.loader.launch.JarLauncher"]
