# User-Microservice
Backend app create user with jwt token creation.

# Use
Download or clone this project, then through IntelliJ IDEA or another IDE, open the project, or just for execute CLI commands.

# Install

```
Minimum Requirements
    . Mac OS X Sierra, Windows 10, Ubuntu 18.x
    . JDK 1.8.x^
    . Gradle 6.2.x^
 ```   

# Run
#### For next commands, you have to inside root path of application.

### 1. Run Application

```bash
gradle bootRun
```

### 2. Tests

```bash 
gradle test
````

### 2. Explanation

Methods
 
- Create user (POST)
```
Endpoint: http://localhost:8080/api/v1/users
```
Body Request
```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "AZazaz00",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```
Response
```
{
    "id": "36fc7f40-de46-4ce3-8cfa-ad2ee08e8747",
    "name": "Juan Rodriguez",
    "token": "eyJhbGciOiJIUzUxMiJ9.....",
    "timestamps": {
        "created_at": "2020-03-01T16:36:41.080103",
        "updated_at": "2020-03-01T16:36:41.080103"
    },
    "last_login": "2020-03-01T16:36:41.080103",
    "is_active": true
}
```
Response Error: After retry create user
```
{
    "code": 1003,
    "message": "this e-mail juan@rodriguez.org is already exist on dataBase"
}
```
Response Error: If name is empty or null
```
{
    "code": 1002,
    "message": "name can´t be null or empty"
}
```
Response Error: If email is empty or null
```
{
    "code": 1002,
    "message": "email can´t be null or empty"
}
```
Response Error: If password is empty or null
```
{
    "code": 1002,
    "message": "password can´t be null or empty"
}
```
Response Error: If array phones is empty or null
```
{
    "code": 1002,
    "message": "phones can´t be null or empty"
}
```


# Deploy

```
docker build -f Dockerfile -t usermsa .
docker-compose up -d --force-recreate
```
or
```
docker-compose up -d --build --force-recreate
```

# Architecture or diagram

![alt text](https://www.lucidchart.com/publicSegments/view/1b3ffc21-edea-4853-92a9-8e66130e100d/image.png)

# License
License under the terms of the GNU GPLv3 License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)


The GPLv3 License (GPL)

Copyright © 2020 Mauricio Valencia



