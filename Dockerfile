#Using base image containing Java runtime
FROM openjdk:8-jdk-alpine

#Maintainer info
LABEL MAINTAINER="Shivani Malhotra"

#Workdirectory in the container
WORKDIR /

VOLUME ["/log"]

ARG JAR_FILE=target/bookstore-application-0.0.1-SNAPSHOT.jar

#Copy jar file to the docker image
ADD ${JAR_FILE} book-store.jar

#Run the application
ENTRYPOINT ["java","-jar","/book-store.jar"]

#Exposing port to the outside world
EXPOSE 8081