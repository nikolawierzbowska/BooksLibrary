# BooksLibrary REST API

REQUIREMENTS
JDK 17 
docker and docker compose installed on your machine

RUNNING TESTS
./mvnw clean test

RUNNING LOCALLY
./mvnw clean build && docker-compose up --build

ENDPOINTS
GET localhost:8080/api/v1/books

GET localhost:8080/api/v1/books?titleSort=ASC

GET localhost:8080/api/v1/books?titleSort=DSC

GET localhost:8080/api/v1/books?title=obCy

GET localhost:8080/api/v1/books?author=albert Camus

GET localhost:8080/api/v1/books?year=1925

GET localhost:8080/api/v1/books?rate=2

PATCH localhost:8080/api/v1/books/062f817b-b423-4f01-aead-1546c948110a
