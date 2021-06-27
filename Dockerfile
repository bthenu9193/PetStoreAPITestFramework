FROM maven:3.6.0-jdk-8-alpine
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
COPY testngSuite.xml /usr/local/service.testngSuite.xml
COPY testng.xml /usr/local/service.testng.xml
WORKDIR /usr/local/service
RUN mvn -f /usr/local/service/pom.xml clean test -DskipTests=true

