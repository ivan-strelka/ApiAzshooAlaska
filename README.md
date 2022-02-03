# Simple API test to https://hub.docker.com/r/azshoo/alaska

An example of tests that can be implemented to test a REST API service that exposes a CRUD interface.

Download the repository from a docker hub: https://hub.docker.com/r/azshoo/alaska with the tag 1.0

The application is available inside the container at the address http://0.0.0.0:8091 after start

## Run Tests

### 1. Before run tests start a service (check docker demon is running):

### 2. Run command in terminal to start container ```docker run -p 8091:8091 -it azshoo/alaska:1.0```

### 3. Check http://0.0.0.0:8091/info (optional)

### 4. Run command in terminal to run tests ```mvn clean test```

### 5. Run command in terminal to ``mvn allure:serve`` to get a report