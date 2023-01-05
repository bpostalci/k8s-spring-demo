####
# Build :
# docker build -f Dockerfile -t openshift-spring-demo .
# Then run the container using:
# docker run -i --rm -p 8081:8081 openshift-spring-demo
####

FROM quay.io/devfile/maven:3.8.1-openjdk-17-slim

WORKDIR /build

# Build dependency offline to streamline build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:11-jdk
COPY --from=0 /build/target/openshift-spring-demo.jar /app/target/openshift-spring-demo.jar

EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "/app/target/openshift-spring-demo.jar", "--server.port=8081" ]
