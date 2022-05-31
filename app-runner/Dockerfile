# Extend the Eclipse Temurin Docker image. See https://github.com/docker-library/docs/blob/master/eclipse-temurin/README.md#supported-tags-and-respective-dockerfile-links
FROM eclipse-temurin:17-jre

# Copy over the program distribution
RUN mkdir /opt/app
COPY build/install/app-runner /opt/app

# Invoke the "start script".
CMD ["/opt/app/bin/app-runner"]
