# Helsinki-bike-app

This is a simple web application that allows you to view the journey data from a csv file. The data is stored in a database, and the app provides endpoints for getting the journey information. The app also allows for unique station names and their count of occurences to be accessed.

The app includes:
-backend app using gradle, spring and java (these were chosen based on my research on languages used in Solita and my plan to improve my skills with spring)
-CSV reading and simple validation
-server based pagination
-endpoints for db access
-Unit tests
-React frontend

## How to run it in Docker

1. Clone the repository
2. Build a docker container using a command
    docker run --name helsinkibikeDB -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:latest
3. Run the backend app using your IDE of choice (the app was created using IntelliJ)
4. Access the saveAll endpoint going to the url http://localhost:8080/api/saveAllJourneys to populate the table. This will take a long time due to the size of the file. I recommend interupting the process and starting the server again to procceed, unless you have time to wait
5. Go to http://localhost:8080/api/saveAllStations to save the stations into the db
5. Run the frontend React app using npm start
