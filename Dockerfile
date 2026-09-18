# ==========================================
# STAGE 1 - BUILDER
# ==========================================

FROM eclipse-temurin:17-jdk AS builder

WORKDIR /build

# Copy Java source code
COPY MyApp.java .

# Compile Java application
RUN javac MyApp.java

# Create executable JAR
RUN jar cfe myapp.jar MyApp MyApp.class


# ==========================================
# STAGE 2 - FINAL IMAGE
# ==========================================

FROM eclipse-temurin:17-jdk

WORKDIR /app

# Environment variables
ENV APP_ENV=production
ENV APP_PORT=8080

# Copy JAR from Builder stage
COPY --from=builder /build/myapp.jar /app/myapp.jar

# Copy configuration file
COPY config/application.properties /app/application.properties

# Create logs volume
VOLUME /app/logs

# Application port
EXPOSE 8080

# Start Java application
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

# Default argument
CMD ["--server.port=8080"]