FROM java:7
VOLUME /tmp
ADD QuickFeedChronMaster-1.0-SNAPSHOT.jar /opt/QuickFeedChronMaster-1.0-RELEASE.jar
RUN bash -c 'touch /opt/QuickFeedChronMaster-1.0-RELEASE.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/QuickFeedChronMaster-1.0-RELEASE.jar"]