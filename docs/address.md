# Address API Spec

## Create Address

- Endpoint : POST /api/contacts/{id_contact}/addresses

Request Header :
- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
    "street" : "jl apapun",
    "city" : "Kota",
    "province" : "provinsi",
    "country" : "negara",
    "postalCode" : "12321"
}
```

Response Body (Success) 200 :

```json
{
    "data" : {
        "id" : "random-string",
        "street" : "jl apapun",
        "city" : "Kota",
        "province" : "provinsi",
        "country" : "negara",
        "postalCode" : "12321"
    }
}
```

Response Body (Failed) 400 :

```json
{
    "errors" : "street cannot blank"
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

## Get Address

- Endpoint : GET /api/contacts/{id_contact}/addresses/{id_address}

Request Header :
- X-API-TOKEN : Token (mandatory)

Response Body (Success) 200 :

```json
{
    "data" : {
        "id" : "random-string",
        "street" : "jl apapun",
        "city" : "Kota",
        "province" : "provinsi",
        "country" : "negara",
        "postalCode" : "12321"
    }
}
```

Response Body (Failed) 404:

```json
{
    "errors" : "contact is not found"
}
```

Response Body (Failed) 404:

```json
{
    "errors" : "address is not found"
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## Update Address

- Endpoint : PUT /api/contacts/{id_contact}/addresses/{id_address}

Request Header :
- X-API-TOKEN : Token (mandatory)

Request Body :

```json
{
    "street" : "jl apapun",
    "city" : "Kota",
    "province" : "provinsi",
    "country" : "negara",
    "postalCode" : "12321"
}
```

Response Body (Success) 200 :

```json
{
    "data" : {
        "id" : "random-string",
        "street" : "jl apapun",
        "city" : "Kota",
        "province" : "provinsi",
        "country" : "negara",
        "postalCode" : "12321"
    }
}
```

Response Body (Failed) 400 :

```json
{
    "errors" : "street cannot blank"
}
```

Response Body (Failed) 404:

```json
{
    "errors" : "contact is not found"
}
```

Response Body (Failed) 404:

```json
{
    "errors" : "address is not found"
}
```
Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```


## Remove Address

- Endpoint : DELETE /api/contacts/{id_contact}/addresses/{id_address}

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

Response Body (Failed) 404:

```json
{
    "errors" : "address is not found"
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```

## List Address

- Endpoint : GET /api/contacts/{id_contact}/addresses

Request Header :
- X-API-TOKEN : Token (mandatory)

Response Body (Success) 200 :

```json
{
    "data" : [
        {
            "id" : "random-string",
            "street" : "jl apapun",
            "city" : "Kota",
            "province" : "provinsi",
            "country" : "negara",
            "postalCode" : "12321"
        }
    ]
}
```

Response Body (Failed) 404:

```json
{
    "errors" : "contact is not found"
}
```

Response Body (Failed) 404:

```json
{
    "errors" : "address is not found"
}
```

Response Body (Failed) 401 :

```json
{
    "errors" : "Unauthorized"
}
```
