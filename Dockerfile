FROM openjdk:20-jdk
WORKDIR /app
COPY out/artifacts/awsstarter_jar/awsstarter.jar /app/awsstarter.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/awsstarter.jar"]
