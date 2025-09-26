FROM hseeberger/scala-sbt:eclipse-temurin-11.0.14.1_1.6.2_2.13.8 AS builder
WORKDIR /app

COPY . .

RUN sbt clean compile assembly migrations/assembly

FROM openjdk:17-slim
WORKDIR /app

COPY --from=builder /app/target/scala-2.13/myapp-assembly-*.jar myapp.jar
COPY --from=builder /app/migration/target/scala-2.13/migrations-assembly-*.jar migrations.jar

COPY start.sh ./start.sh
RUN chmod +x ./start.sh

CMD ["./start.sh"]
