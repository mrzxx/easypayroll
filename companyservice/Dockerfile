FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/companyservice-0.0.1-SNAPSHOT.jar companyservice.jar
ENTRYPOINT ["java","-jar","/companyservice.jar"]
