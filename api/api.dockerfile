FROM bellsoft/liberica-runtime-container:jre-17-slim-stream-glibc

# Add and enable a new user. Because it is more secure.
RUN addgroup -S textify && adduser -S api -G textify
USER api:textify

ARG EXTRACTED=target/extracted
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
