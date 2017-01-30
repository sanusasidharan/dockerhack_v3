FROM java:7
VOLUME /tmp
ADD QuickFeedRSSPoller-1.0-SNAPSHOT.jar /opt/QuickFeedRSSPoller-1.0-RELEASE.jar
RUN bash -c 'touch /opt/QuickFeedRSSPoller-1.0-RELEASE.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/QuickFeedRSSPoller-1.0-RELEASE.jar"]
