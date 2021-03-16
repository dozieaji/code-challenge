FROM openjdk:8
EXPOSE 8080
ADD target/code-challenge.jar code-challenge.jar
ENTRYPOINT ["java","-jar","/code-challenge.jar"]