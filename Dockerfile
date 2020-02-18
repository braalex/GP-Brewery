FROM openjdk:11
MAINTAINER java@gpsolutions.com
COPY target/brewery-0.0.1-SNAPSHOT.jar opt/brewery.jar
ENTRYPOINT ["java","-jar","opt/brewery.jar"]