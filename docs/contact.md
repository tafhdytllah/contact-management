# Contact API Spec

## Create Contact

- Endpoint : POST /api/contacts

Request Header :
- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
    "firt_name" : "Taf",
    "last_name" : "hdytllah",
    "email" : "tafh@gmail.com",
    "phone" : "081918811"
}
```

Response Body (Success) 200 :

```json
{
    "data" : {
        "id" : "random-string",
        "firt_name" : "Taf",
        "last_name" : "hdytllah",
        "email" : "tafh@gmail.com",
        "phone" : "081918811"
    }
}
```

Response Body (Failed) 400 :

```json
{
    "errors" : "Email format invalid, phone format invalid ..."
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## Get Contact

- Endpoint : GET /api/contacts/{id_contact}

Request Header :
- X-API-TOKEN : Token (mandatory)

Response Body (Success) 200 :

```json
{
    "data" : {
        "id" : "random-string",
        "firt_name" : "Taf",
        "last_name" : "hdytllah",
        "email" : "tafh@gmail.com",
        "phone" : "081918811"
    }
}
```

Response Body (Failed) 404:

```json
{
    "errors" : "contact is not found"
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## Update Contact

- Endpoint : PUT /api/contacts/{id_contact}

Request Header :
- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
    "firt_name" : "Taf",
    "last_name" : "hdytllah",
    "email" : "tafh@gmail.com",
    "phone" : "081918811"
}
```

Response Body (Success) 200 :

```json
{
    "data" : {
        "id" : "random-string",
        "firt_name" : "Taf",
        "last_name" : "hdytllah",
        "email" : "tafh@gmail.com",
        "phone" : "081918811"
    }
}
```

Response Body (Failed) 400 :

```json
{
    "errors" : "Email format invalid, phone format invalid ..."
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## Search Contact

Endpoint : 
- GET /api/contacts

Query Param :

- name : String, contact first name or last name, using like query, optional
- email : String, contact email,  using like query, optional
- phone : String, contact phone, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Request Header :
- X-API-TOKEN : Token (mandatory)

Response Body (Success) 200 :


```json
{
    "data" : [
        {
            "id" : "random string",
            "firt_name" : "Taf",
            "last_name" : "hdytllah",
            "email" : "tafh@gmail.com",
            "phone" : "081918811"
        },
        {
            "id" : "random string",
            "firt_name" : "Taf",
            "last_name" : "hdytllah",
            "email" : "tafh@gmail.com",
            "phone" : "081918811"
        },
    ],
    "paging" : {
        "current_page" : 0,
        "total_page" : 10,
        "size" : 10
    }
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## Remove Contact

- Endpoint : DELETE /api/contacts/{id_contact}

Request Header :
- X-API-TOKEN : Token (mandatory)

Response Body (Success) 200 :

```json
{
    "data" : "Ok"
}
```


Response Body (Failed) 404:

```json
{
    "errors" : "contact is not found"
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```
