FROM openjdk:8-jdk-alpine

WORKDIR /piggybank-api

COPY build/libs/piggybank-api-1.0-SNAPSHOT.jar /piggybank-api/piggybank-api-1.0-SNAPSHOT.jar

CMD ["sh", "-c", "java -jar piggybank-api-1.0-SNAPSHOT-all.jar"]
