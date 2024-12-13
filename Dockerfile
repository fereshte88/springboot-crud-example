FROM amazoncorretto:21-alpine-jdk
ADD target/springboot-crud-example-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]