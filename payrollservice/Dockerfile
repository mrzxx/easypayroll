FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/payrollservice-0.0.1-SNAPSHOT.jar payrollservice.jar
ENTRYPOINT ["java","-jar","/payrollservice.jar"]
