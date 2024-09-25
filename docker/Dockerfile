FROM eclipse-temurin:17.0.10_7-jre-jammy
WORKDIR /app
COPY ./target/BooksLibrary*.jar ./bookslibrary.jar
COPY ./docker/application.yaml ./
CMD ["java", "-jar", "./bookslibrary.jar"]

