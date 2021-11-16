FROM openjdk:8-jdk-alpine

# only for windows git bash (우린 다 windows 쓰니까)
# RUN export TERM=cygwin

# 이렇게 되면 gradle과 src가 모두 필요하므로 dockerfile로 작성하긴 끔찍하다.
# RUN ./gradlew build && java -jar build/libs/gs-spring-boot-docker-0.1.0.jar

# Spring Boot 2.5부터 이렇게 이미지로도 빌드할 수 있다.
# ./gradlew bootBuildImage --imageName=

WORKDIR /

COPY ./build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
