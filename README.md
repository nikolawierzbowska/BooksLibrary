<h1>BooksLibrary REST API</h1>

<h2>REQUIREMENTS</h2>
JDK 17 
docker and docker compose installed on your machine

<h2>RUNNING TESTS</h2>
./mvnw clean test

<h2>RUNNING LOCALLY</h2>
./mvnw clean package && docker-compose up --build

<h2>ENDPOINTS</h2>
GET localhost:8080/api/v1/books<br>
GET localhost:8080/api/v1/books?titleSort=ASC<br>
GET localhost:8080/api/v1/books?titleSort=DSC<br>
GET localhost:8080/api/v1/books?title=obCy<br>
GET localhost:8080/api/v1/books?author=albert Camus<br>
GET localhost:8080/api/v1/books?year=1925<br>
GET localhost:8080/api/v1/books?rate=2<br>
PATCH localhost:8080/api/v1/books/062f817b-b423-4f01-aead-1546c948110a<br>
