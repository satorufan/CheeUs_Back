FROM openjdk:17
ENV APP_HOME=/user/cheeus/
WORKDIR ${APP_HOME}
COPY build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]