# Coding LeaderBoard backend

## Description

* This is a Learning Management System backend application built with Spring Boot and Spring Data JPA using MySQL. It provides RESTful APIs for creating, reading, updating, and deleting leaderboard contest.

## Running the Application

#### Just use run on LearningSystemApplication if on IntelliJ IDEA or use the following command if using Gradle

```bash
./gradlew bootRun
```

## Database

### MySQL

#### If you don't have mysql installed, run the following command
```bash
brew install mysql
mysql --version
```

#### To Login to MySQL

```bash
brew services start mysql
mysql
mysql -u root -p
CREATE DATABASE <your-database-name>;
```

#### Add this in application.properties
```
spring.application.name=learning_system
spring.datasource.url=jdbc:mysql://localhost:3306/lms
spring.datasource.username=root
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update

server.port=8080
```



## API Endpoints

### Student API

### `POST /student`
Register a new student

- **Request Body**:
    ```json
    {
        "studentName": "string"
    }
    ```
- **Response**:
    - **Status**: `201 Created`
    - **Body**:
        ```json
        {
            "id": "string",
            "studentName": "string",
            ...
        }
        ```

### `GET /student`
Retrieve a list of all students

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        [
            {
                "id": "string",
                "studentName": "string",
                ...
            },
            ...
        ]
        ```

### `GET /student/{studentId}`
Retrieve the details of a specific student

- **Parameters**:
    - `studentId` (path): ID of the student to retrieve

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        {
            "id": "string",
            "studentName": "string",
            ...
        }
        ```

### `DELETE /student/{studentId}`
Delete a specific student

- **Parameters**:
    - `studentId` (path): ID of the student to delete

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        {
            "message": "Student deleted successfully"
        }
        ```

### `POST /student/{studentId}/register/{subjectId}`
Register a student to a subject

- **Parameters**:
    - `studentId` (path): ID of the student
    - `subjectId` (path): ID of the subject

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        {
            "id": "string",
            "studentName": "string",
            ...
        }
        ```

### `POST /student/{studentId}/exam/{examId}`
Enroll a student to an exam

- **Parameters**:
    - `studentId` (path): ID of the student
    - `examId` (path): ID of the exam

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        {
            "id": "string",
            "studentName": "string",
            ...
        }
        ```


### Subject API

### `GET /subject`
Retrieve a list of all subjects

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        [
            {
                "id": "string",
                "name": "string",
                ...
            },
            ...
        ]
        ```

### `GET /subject/{subjectId}`
Retrieve the details of a specific subject

- **Parameters**:
    - `subjectId` (path): ID of the subject to retrieve

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        {
            "id": "string",
            "name": "string",
            ...
        }
        ```

### `POST /subject`
Create a new subject

- **Request Body**:
    ```json
    {
        "name": "string",
        ...
    }
    ```

- **Response**:
    - **Status**: `201 Created`
    - **Body**:
        ```json
        {
            "id": "string",
            "name": "string",
            ...
        }
        ```

### `DELETE /subject/{subjectId}`
Delete a specific subject

- **Parameters**:
    - `subjectId` (path): ID of the subject to delete

- **Response**:
    - **Status**: `204 No Content`


### Exam API

### `POST /exams`
Create a new exam

- **Request Body**:
    ```json
    {
        "subjectId": "string"
    }
    ```
- **Response**:
    - **Status**: `201 Created`
    - **Body**:
        ```json
        {
            "id": "string",
            "subjectId": "string",
            ...
        }
        ```

### `GET /exams`
Retrieve a list of all exams

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        [
            {
                "id": "string",
                "subjectId": "string",
                ...
            },
            ...
        ]
        ```

### `GET /exams/{examId}`
Retrieve the details of a specific exam

- **Parameters**:
    - `examId` (path): ID of the exam to retrieve

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        {
            "id": "string",
            "subjectId": "string",
            ...
        }
        ```

### `DELETE /exams/{examId}`
Delete a specific exam

- **Parameters**:
    - `examId` (path): ID of the exam to delete

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        {
            "message": "Exams deleted successfully"
        }
        ```


### Number API

### `GET /hidden-feature/{number}`
Retrieve a fact about a specific number

- **Parameters**:
    - `number` (path): The number to retrieve the fact for

- **Response**:
    - **Status**: `200 OK`
    - **Body**:
        ```json
        "string"
        ```

- **Example**:
    - **Request**:
        ```http
        GET /hidden-feature/42
        ```
    - **Response**:
        ```json
        "The number 42 is the answer to life, the universe, and everything."
        ```




## Postman API

#### A Postman collection for testing the API is available [here](https://api.postman.com/collections/20879467-8dcf40be-b341-4028-b38d-f3217ce9594e?access_key=PMAT-01J0V761X81CVA9NZ2SZH35STK).