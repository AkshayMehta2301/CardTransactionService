# Welcome to CardTransactionService

This microservice deals with **Card Transactions**.

- This service consist of 3 entities : Account, OperationType and Transaction
- **Account** mapped to user and will store account balance.
- **OperationType** stores various supported operation by service.
- **Transaction** stores the operation done for particular account.

### Things included in this microservice:

- Runs on JDK : **17**
- **Maven** project
- Database : **Postgresql**, Database name : card_transaction
- **Flyway** is integrated to automate database migration. Database creation (V1__ddl.sql) and seed data filling(V2__operation_type_insert.sql) migrations are added.
- **Test cases** are added with acceptance criteria of **80%** code coverage.
- **Docker** : Dockerfile added to run the application in containerized environment.
- **docker-compose.yaml** file added for easy service set-up and testing features.
- **run.sh** script added which does all thing to run the service.
- **Factory** and **Strategy** design pattern combination implemented to update account balance for various types of operation.

### To run and test the feature of service:

1. Clone the code
2. Build the jar
    ```
    mvn clean package -Dmaven.test.skip
    ```
3. To run the test
    ```
    mvn test
    ```
4. Run the service, which will build the containers mentioned in docker-compose.yaml and run them.
    ```bash
    bash run.sh
    ```
   Visit below-mentioned API docs (swagger url) to test API functionalities.
5. To stop running service, we can press `CTRL + C`
6. To remove stopped containers
    ```
    docker-compose down
    ```

## API Docs : http://localhost:8080/card/swagger-ui/index.html#/
## Github Repo : https://github.com/AkshayMehta2301/CardTransactionService  