Welcome to the Order API

There are 2 projects included in the directory.
1. order-exam

    This is the Java API written in Spring Boot, which can be started using 'mvn clean spring-boot:run'. The database configuration(application.yml) which can be found on the resources folder(src/main). 
    Unit Test could be found on the 'src/test'. But there is a database script on the 'resources' directory.
    
2. mx-order-experience-api
   
   This is the Mule API, which has a console.
   The console url is 'http://localhost:8094/console/'. The unit test is implemented in java munit, which is on the directory 'src/test/java'. For running the java munit test, there is a file 'test.sh', which makes it easier to run 'mvn clean test -Dmule.testingMode=true'. Using java munit java requires the 'testingmode' flag, to prevent an issue with Mule license, since we have used dataweave in the project.
   

