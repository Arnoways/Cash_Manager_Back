FROM openjdk:11
WORKDIR /entrypoint
COPY . .
RUN chmod +x java-entrypoint.sh
ENTRYPOINT [ "/entrypoint/java-entrypoint.sh" ]
