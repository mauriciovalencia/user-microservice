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


# Deploy


# Architecture or diagram


# License
License under the terms of the GNU GPLv3 License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)


The GPLv3 License (GPL)

Copyright © 2020 Mauricio Valencia



