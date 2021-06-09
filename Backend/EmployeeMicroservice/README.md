# Employee REST-API

Documentation of all URIs for the Employee REST-API.

## GET

### GET ALL
```
/employeeBackend
```
Returns a list of all employees.

### GET BY ID
```
/employeeBackend/id=0
```
Returns a single employee by its ID.

### GET BY FIRSTNAME
```
/employeeBackend/firstname=Max
```
Returns a list of employees found by the firstname.

### GET BY LASTNAME
```
/employeeBackend/lastname=Mustermann
```
Returns a list of employees found by the lastname.

## POST

URI:
```
/employeeBackend
```

JSON:
```JSON
{
    "firstname": "Max",
    "lastname": "Mustermann"
}
```
![#f03c15](https://via.placeholder.com/15/ff0000/000000?text=+) Firstname Length >= 1\
Adds an employee with the values from the JSON body.
Returns the new added employee.

## PUT

URI:
```
/employeeBackend/id=0
```

JSON:
```JSON
{
    "firstname": "Max",
    "lastname": "Mustermann"
}
```
![#f03c15](https://via.placeholder.com/15/ff0000/000000?text=+) Lastname Length >= 3\
Updates the employee with the id in the URI. The employee gets updated with the values from the JSON body.
Returns the updated employee.

## DELETE

URI:
```
/employeeBackend/id=0
```

Deletes the employee with the id in the URI.
Returns the deleted employee.
