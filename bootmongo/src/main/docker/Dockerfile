#Official JDK7 image
#FROM java:openjdk-8-jdk
#FROM java:openjdk-8-alpine
FROM java:7
ADD QuickFeedMongoClient-1.0-SNAPSHOT.jar /opt/QuickFeedMongoClient-1.0-RELEASE.jar
#EXPOSE 8080
EXPOSE 31041
ENV MONGODB_DB_NAME feeds
ENV MONGODB_DB_HOST mongo
ENV MONGODB_DB_PORT 27017
ENTRYPOINT ["java", "-jar", "/opt/QuickFeedMongoClient-1.0-RELEASE.jar"]
