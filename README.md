# Project Description

This is a book-store java application build with Maven as the build tool and uses Spring Boot and Spring data Jpa to save and retieve the data from MySQL database.Docker is used to build the different images of the spring boot application and mysql database to run them in containers.

## Technologies used
1. Spring Boot 2.2.4.RELEASE
2. MySQL database 8.0.19
2. Lombok 1.18.10
3. MapStruct 1.2.0.Final
4. JUnit 4.12
5. Mockito 3.1.0
6. Maven 3.6.2
7. Java 8
8. Docker Community Edition 17.09.0-ce-win33 

## Steps to run docker compose to run the application
1. Take a clone of the repository take it in your system.
2. Go to the project directory to be at the path where docker-compose.yml file is there.
3. Open the bash or windows power shell from the above path.
4. Run the cmd:
   docker-compose up
   This will up the containers for both application and mysql images.
5. To run the different api's, use postman collection attached json file and change the port for running the application by selecting      the port where the application is up using the below cmd:
   docker ps
   Eg: Check the port for the image: shivsapan/book-store:latest i.e. 0.0.0.0:32772->8081/tcp
   So use the port 32772 where the application is accessible to run the api's.
 

## Script files for creating database and tables in MySQL

Please refer to the scripts file present in bookstore-application project
To connect to mysql db execute the below command to create a user with privileges to connect to mysql if it doesn't exist:
CREATE USER 'root'@'localhost' IDENTIFIED BY 'cafu';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';
COMMIT;

### Build

mvn clean install

## Running the tests

mvn clean test

### RUN command

java -jar target/bookstore-application-0.0.1-SNAPSHOT.jar

### Postman collection 
Please refer to book_store.postman_collection.json in bookstore-application project


