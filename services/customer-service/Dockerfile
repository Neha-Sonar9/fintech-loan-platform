FROM eclipse-temurin:17-jre

WORKDIR /app

RUN addgroup --system spring && adduser --system spring --ingroup spring

COPY target/customer-service-0.0.1-SNAPSHOT.jar app.jar

RUN chown spring:spring app.jar

USER spring

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]