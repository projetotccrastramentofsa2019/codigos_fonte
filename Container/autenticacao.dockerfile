FROM java:8-jdk-alpine
LABEL maintainer="danilodepaulosilva@hotmail.com"
EXPOSE 8080
ARG JAR_FILE=./Autenticacao.jar
ADD ${JAR_FILE} Autenticacao.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Autenticacao.jar"]
