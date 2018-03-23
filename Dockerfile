FROM zeilush/h2
RUN chmod u+x /var/lib/h2/h2.sh
RUN docker logs -f myH2
FROM java:8
VOLUME /tmp
ADD  meu-politico-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
