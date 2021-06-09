# Service REST-API

Documentation of all URIs for the Service REST-API.

## GET

### GET ALL
```
/serviceBackend
```
Returns a list of all services.

### GET BY ID
```
/serviceBackend/id=0
```
Returns a single service by its ID.

### GET BY NAME
```
/serviceBackend/name=TestService
```
Returns a list of services found by its name.

### GET BY EMPLOYEE
```
/serviceBackend/employeeID=0
```
Returns a list of services found by an employee ID.

### GET BY DATE
```
/serviceBackend/date=31-12-2021
```
![#f03c15](https://via.placeholder.com/15/ff0000/000000?text=+) FORMAT -> `dd-MM-yyyy`\
Returns a list of services found by a date.

## POST

URI:
```
/serviceBackend
```

JSON:
```JSON
{
    "name": "New Service",
    "employeeId": 0,
    "date": "31-12-2021",
    "address": "Parzer Schulstraße 1"
}
```
![#f03c15](https://via.placeholder.com/15/ff0000/000000?text=+) DATE FORMAT -> `dd-MM-yyyy`\
Adds a service with the values from the JSON body.
Returns the new added service.

## PUT

URI:
```
/serviceBackend/id=0
```

JSON:
```JSON
{
    "name": "New Service",
    "employeeId": 0,
    "date": "31-12-2021",
    "address": "Parzer Schulstraße 1"
}
```
![#f03c15](https://via.placeholder.com/15/ff0000/000000?text=+) DATE FORMAT -> `dd-MM-yyyy`\
Updates the service with the id in the URI. The Service gets updated with the values from the JSON body.
Returns the updated service.

## DELETE

URI:
```
/serviceBackend/id=0
```

Deletes the service with the id in the URI.
Returns the deleted service.
