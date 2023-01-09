### Requirements

- Java version 11.

### Sumary

This application was written using Java 11, Spring Boot version 2.7.7 and H2 in memory database. If you want to see the panel for the H2, join to the url *http://localhost:8080/h2/* and change the defaults parameters:

JDBC URL:  jdbc:h2:mem:./testdb  
User Name: bia   
Without Password  

The H2 panel should like this:

![H2 configuration](https://github.com/oscarBauDev/bia-app/blob/main/src/main/resources/images/h2configuration.PNG)



If you want to change these parameters, you might modify them  in the file **application.properties** that is in the directory  *src/main/resources*.

In addition, you can find the initial data in the file **import.sql** in **src/main/resources** directory.



### Run application

You might run the application in your preference IDE. Once in the IDE, run the main class that is AppApplication.java or by the console using the command ./mvnw spring-boot:run.

### Endpoints

- http://localhost:8080/biadata (GET method)

Body example:
```
{
    "meterDate": "2022-10-21",
    "period": "weekly"
}
```

For consuming the end point, you could use:

```
curl --location --request GET 'localhost:8080/biadata' \
--header 'Content-Type: application/json' \
--data-raw '{
    "meterDate": "2022-10-21",
    "period": "weekly"
}'
```




If you have any question, please don't hesitate to contact me: abrilrdev@gmail.com

