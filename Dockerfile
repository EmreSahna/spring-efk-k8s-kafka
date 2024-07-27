FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=build/libs/uniapp.jar
COPY ${JAR_FILE} uniapp.jar
ENTRYPOINT ["java", "-jar", "uniapp.jar"]