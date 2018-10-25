#-------------------------------------------------------#
#                    BUILD CONTAINER                    #
#-------------------------------------------------------#

# base build image
FROM maven:3.5-jdk-8 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy other files
COPY ./src ./src

# set necessary env vars
ENV OpenWeatherAppid=a313baa1d8465b05b329b9a7296b9bb1
ENV KeyWeatherbit=bc42023a26cc4c87969db5168b905dd1

# build for release
RUN mvn package

#-------------------------------------------------------#
#                   RUNNING CONTAINER                   #
#-------------------------------------------------------#

# final base image
FROM openjdk:8u171-jre-alpine

# set deployment directory
WORKDIR /ifood-backend-basic-test

# copy over the built artifact from the maven image
COPY --from=maven target/demo-0.0.1-SNAPSHOT.jar ./

# set necessary env vars
ENV OpenWeatherAppid=a313baa1d8465b05b329b9a7296b9bb1
ENV KeyWeatherbit=bc42023a26cc4c87969db5168b905dd1

# set the startup command to run your binary
CMD ["java", "-jar", "./demo-0.0.1-SNAPSHOT.jar"]