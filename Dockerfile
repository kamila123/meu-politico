
# set-up h2
RUN wget http://www.h2database.com/h2-2014-01-18.zip
RUN unzip h2-2014-01-18.zip -d /opt/
ADD h2/h2-server.sh /opt/h2/bin/h2-server.sh
RUN chmod +x /opt/h2/bin/h2-server.sh
ADD h2/h2-conf /opt/h2-conf
RUN mkdir -p /opt/h2-data
RUN rm h2-2014-01-18.zip

FROM java:8
VOLUME /tmp
ADD  meu-politico-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
