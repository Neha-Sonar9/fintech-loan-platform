FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

RUN groupadd --system spring && \
    useradd --system --gid spring --home-dir /app --shell /usr/sbin/nologin spring

COPY --chown=spring:spring target/customer-service-0.0.1-SNAPSHOT.jar app.jar

USER spring

EXPOSE 8081

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]