#FROM openjdk:11-jre-slim
FROM openjdk:18-ea-11-alpine

RUN apk add curl jq

# workspace
WORKDIR /usr/share/udemy

# add .jar files under target from host
ADD target/selenium-docker.jar        selenium-docker.jar
ADD target/selenium-docker-tests.jar  selenium-docker-tests.jar
ADD target/libs                       libs

# add suite files
ADD search-module.xml        search-module.xml
ADD book-flight-module.xml   book-flight-module.xml

# entrypoint - pass browser, hub_host and module variables
#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

# Add healthcheck script
ADD healthcheck.sh			 healthcheck.sh
RUN dos2unix healthcheck.sh
ENTRYPOINT sh healthcheck.sh