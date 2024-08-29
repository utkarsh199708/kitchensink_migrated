Migration of 'kitchensink' Application to Spring Boot
Overview
This project involves migrating the 'kitchensink' JBoss application to Spring Boot, leveraging Java 21 and modern technologies. The objective is to transition from JBoss to a more lightweight and manageable framework while ensuring functionality and performance improvements.

Project Details
Original Application: 'kitchensink' (JBoss)
Target Framework: Spring Boot
Java Version: 21
Spring Boot Version: 3.3.2
Dependencies:
Spring Boot Web
MongoDB
Thymeleaf
Spring Security
slf4j -api
spring-boot-starter-data-mongodb
JWT
actuator
prometheus
starter test
jakarta servlet api
Docker

Goals
Migrate from JBoss to Spring Boot: Convert the application to use Spring Boot for simplified configuration and deployment.
Update Java Version: Utilize Java 21 features and optimizations.
Switch to MongoDB (Optional Stretch Goal): Replace the existing relational database with MongoDB for improved scalability and flexibility.
Steps for Migration
Setup Project Structure

Initialize a new Spring Boot project using Spring Initializr or a similar tool.
Configure the pom.xml  with required dependencies.
Port Application Code

Controllers: Convert JBoss controllers to Springboot controllers .
1.Map the controllers as per the kitchensin application.
2.Create separate controllers for rendering the view and authentication.

Services:
1. Migrate business logic to Springboot services.
2.Create Separate service to load the user and for further validation.
Repositories: 
Update data access layers to use Spring Data MongoDB (if opting for MongoDB).
Exceptions:
For handling exceptions.Also keep exception classes as sergregated as possible.
Validators:
For Validating the checks.Also keep validation classes as sergregated as possible.
Entity:
Create two entities Member and User one for validation and other for application requirements.
Use the annotaions inside it as per mongodb.

Monitoring.
Add actuator.
Add prometheus dependencies.
Create prometheus.yml and make changes inside application.properties.

Configuration

Security: Implement security using Spring Security, configuring JWT authentication and custom exception handling.
1.Write code for filter,websecurity,request,response,JWTcontroller to generate and validate the token.
2.Ensure that url that dont need authenticationare in requestmatcher.
3.Also token needs to be validated for each subsequent request after validation.

Database
1.Install mongodb.
2.Configure collections and schema as per the application code.
3.Import dependencies in your code.
4.Migrate your repository and Entity and enter url port and schema in application.properties
5.check connection from application to mongodb.

Thymeleaf:
1.Import Thymeleaf dependencies.
2.Create directory for thymeleaf.
3.Create html file inside it and ensure token is sent for each request other than signup.
4.Ensure that data is rendered inside datatable and data that is being sent is being stored in DB.

Testing:
1.Write unit and integration tests to ensure the migrated application behaves as expected.
2.Use Spring Boot testing features to validate the application.
Documentation

Update the README with details about the new setup, including any changes to environment variables or configuration settings.

Deployment
1.Intall docker
2.Create dockerfile containg all the details of environmentand commands that need to be executed.
3.Create dockerignore for files that do not need to be there in the build.
4.Test deployment in a staging environment before rolling out to production.
5.Run below command to run the application.
docker build -t kitchensink_migrated
docker run -d -p 8081:8081 --name kitchensink-container kitchensink_migrated

Open a web browser and navigate to http://localhost:8081.

Contribution
If you have suggestions or improvements, please submit a pull request or open an issue on GitHub.