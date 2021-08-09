# To run the applicatopm

1. Build the tweets-and-feeds-service with Maven
 ```
cd /tweets-and-feeds-service
./mvnw install
```

2. Run docker compose (Please make sure you have docker installed)
cd to the root directory of the project
```
docker compose up --build
```

Access the application on `locahost:3000`
