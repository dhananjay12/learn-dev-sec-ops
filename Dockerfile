FROM eclipse-temurin:latest
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM eclipse-temurin:17.0.4_8-jre-jammy
#EXPOSE 8080
#ARG JAR_FILE=target/*.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]