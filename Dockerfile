# This section will fail the ops-conftest check
#FROM eclipse-temurin:latest
#EXPOSE 8080
#ARG JAR_FILE=target/*.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM eclipse-temurin:17.0.4_8-jre-jammy
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /home/myapp/app.jar
ENTRYPOINT ["java","-jar","/home/myapp/app.jar"]