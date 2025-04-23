# ---------- build stage ----------
    FROM gradle:8.5-jdk17 AS build       # Gradle + JDK
    WORKDIR /app
    
    # Copy project files
    COPY . .
    
    # Build a fat‑jar (adjust the task if you use a different plugin)
    RUN gradle clean shadowJar           
    
    # ---------- runtime stage ----------
    FROM eclipse-temurin:17-jre          # Slim JRE for production
    WORKDIR /app
    
    # Copy the jar produced in the build stage
    COPY --from=build /app/build/libs/*.jar app.jar
    
    # Render sets $PORT for you; default to 8080 locally
    ENV PORT=8080
    EXPOSE 8080
    
    # Run the application
    CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
    