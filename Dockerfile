FROM openjdk:11
EXPOSE 8089
ADD target/achat-1.0.jar achat.jar
ENTRYPOINT ["java", "-jar", "achat.jar"]

