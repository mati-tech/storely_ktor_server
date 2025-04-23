# ---------- build stage ----------
    FROM gradle:8.5-jdk17 AS build       
    WORKDIR /app
    COPY . .
    RUN gradle clean shadowJar           
    
    # ---------- runtime stage ----------
    FROM eclipse-temurin:17-jre          
    WORKDIR /app
    # copy the fat‑jar from the build stage
    COPY --from=build /app/build/libs/*.jar app.jar
    
    # Render sets $PORT; expose it for local runs too
    ENV PORT=8080
    EXPOSE 8080
    
    CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
    