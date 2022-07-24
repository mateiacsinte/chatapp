mvn verify
mvn clean test
docker build -t chatapp:latest .
docker-compose up