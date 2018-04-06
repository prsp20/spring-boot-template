FROM openjdk:8u151-jre-alpine

WORKDIR /app

COPY application/build/libs/application.jar /app/app.jar

EXPOSE 8000

CMD ["java", "-cp", "/app/app.jar", "-Xmx256m", "org.springframework.boot.loader.PropertiesLauncher"]