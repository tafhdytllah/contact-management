# User API Spec

## Register User

- Endpoint : POST /api/users
  
Request Body :

```json
{
    "name" : "tafh dytllah",
    "username" : "tafh@gmail.com",
    "password" : "secretpassword123"
}
```

Response Body (Success) 200 :

```json
{
    "data" : "OK"
}
```

Response Body (Failed) 400 :

```json
{
    "errors" : "username must not blank"
}
```

## Login User

- Endpoint : POST /api/auth/login
  
Request Body :

```json
{
    "username" : "tafh@gmail.com",
    "password" : "secretpassword123",
}
```

Response Body (Success) 200 :

```json
{
    "data" : {
        "token": "TOKEN",
        "expired_at": 1512512 // milisecond
    }
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "username or password wrong"
}
```

## Get user

- Endpoint : GET /api/users/current
  

Request Header :
- X-API-TOKEN : Token (mandatory)

Response Body (Success) 200 :

```json
{
    "data" : {
        "username" : "tafh@gmail.com",
        "name" : "taf hdytllah"
    }
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## Update User

- Endpoint : PATCH /api/users/current

Request Header :
- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
    "name" : "Tafh Hdytllah", // put if only want to update name
    "password" : "new password" // put if only want to update password
}
```
  

Response Body (Success) 200 :

```json
{
    "data" : {
        "username" : "tafh@gmail.com",
        "name" : "taf hdytllah"
    }
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## Logout User

- Endpoint : DELETE /api/auth/logout

Request Header :
- X-API-TOKEN : Token (mandatory)

Response Body (Success) 200 :

```json
{
    "data" : "Ok"
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```