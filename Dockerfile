FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar --jasypt.encryptor.password=LIS_KEY"]
EXPOSE 8080
