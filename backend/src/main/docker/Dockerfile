FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Abzal Kalimbetov <abzalkalimbetov@gmail.com>
VOLUME /tmp
ADD poll.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /app.jar"]
