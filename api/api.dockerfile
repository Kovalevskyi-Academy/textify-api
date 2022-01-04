# Берем образ линукса с JDK на борту.
FROM openjdk:17-alpine

# install some useful programs for testing container
#RUN apk update
#RUN apk add --no-cache tree
#RUN apk add --no-cache findutils

# Add and enable a new user. Because it is more secure.
RUN addgroup -S textify && adduser -S api -G textify
USER api:textify

# Improving application performance in docker.
# Unpack the *.jar and place the APP in a container in layers.

# 1. тут мы просто копируем распакованое приложение
# required a command `mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)`
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/module-info.class /app
COPY ${DEPENDENCY}/BOOT-INF/classes /app

# 2. Тут мы копируем слои докера, в которые было превращено приложение самой джавой!
# required a command `mkdir -p target/extracted && java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted`
#ARG EXTRACTED=target/extracted
#COPY ${EXTRACTED}/dependencies/ ./
#RUN true
#COPY ${EXTRACTED}/spring-boot-loader/ ./
#RUN true
#COPY ${EXTRACTED}/snapshot-dependencies/ ./
#RUN true
#COPY ${EXTRACTED}/application/ ./
#RUN true

#1.
ENTRYPOINT ["java","-cp","app:app/lib/*","textify.api.TextifyApiStarter"]
#2.
#ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
