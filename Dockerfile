FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/gurukulX-0.0.1-SNAPSHOT.jar gurukulX-0.0.1-SNAPSHOT.jar
EXPOSE 8075 
CMD ["java", "-jar", "gurukulX-0.0.1-SNAPSHOT.jar"]
