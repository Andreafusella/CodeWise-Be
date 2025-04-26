FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/codeWise-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8000
ENV JAVA_OPTS=""

CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]