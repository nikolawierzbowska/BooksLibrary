<h1>BooksLibrary REST API</h1>

<h3>REQUIREMENTS</h3>
JDK 17 
docker and docker compose installed on your machine

<h3>RUNNING TESTS</h3>
./mvnw clean test

<h3>RUNNING LOCALLY</h3>
./mvnw clean build && docker-compose up --build

<h3>ENDPOINTS</h3>
GET localhost:8080/api/v1/books<br>
GET localhost:8080/api/v1/books?titleSort=ASC<br>
GET localhost:8080/api/v1/books?titleSort=DSC<br>
GET localhost:8080/api/v1/books?title=obCy<br>
GET localhost:8080/api/v1/books?author=albert Camus<br>
GET localhost:8080/api/v1/books?year=1925<br>
GET localhost:8080/api/v1/books?rate=2<br>
PATCH localhost:8080/api/v1/books/062f817b-b423-4f01-aead-1546c948110a<br>
