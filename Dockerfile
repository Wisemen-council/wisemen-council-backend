FROM openjdk:8-jdk-alpine

COPY build/libs/*.jar app.jar

CMD java -Dserver.port=$PORT -Xms256m -Xmx256m -jar /app.jar
