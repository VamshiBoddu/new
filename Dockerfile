FROM tomcat:8.0

MAINTAINER 10101

USER root

WORKDIR /usr/local/tomcat/webapps

EXPOSE 8080

COPY  target/mvn-hello-world.war  /usr/local/tomcat/webapps/

ADD  https://tomcat.apache.org/tomcat-7.0-doc/appdev/sample/sample.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]
