FROM openjdk:8-jdk-alpine

MAINTAINER mrzonglunli@gmail.com

COPY target/Webstack-Guns-nkt-1.0.jar app.jar

ENV JVM_OPT -Xms250m -Xmx250m

ENTRYPOINT /usr/bin/java -Djava.security.egd=file:/dev/./urandom ${JVM_OPT} -jar app.jar