# **RESTful Web-API. Subject: Veterinary Clinic**


## Using the following technologies:
- Gradle
- JDBC
- MySQL DB
- Spring Framework

### Description of the project:
The application implements a database. Which stores registered users with additional information about them. As well as the pets of these users. Doctors and pet reception records.

## Data model

![Снимок экрана 2022-02-21 150113](https://user-images.githubusercontent.com/93032950/154951502-61dd83c4-132c-44c6-8e6e-9e1d7a1aee2f.png)

## Tables

### User 
+ Contains the information about all the users (users, doctors, admins)
  + Id
  + Login
  + Password
  + Email
  + Role
  + Status

### User details
+ Contains the users personal information
  + User id
  + Name
  + Surname
  + Address
  + Phone number


### Pet
+ Contains the client's personal information
  + Pet id
  + Name
  + Age
  + Type of pet
  + User id
  + Status


### Medical_card
+ Contains appointments made by clients
  + Record id
  + Record date
  + Admission date
  + Visit comment
  + Pet id
  + Doctor id
  + Status

## RESTful API

1. API Description for User

| METHOD	| PATH	| DESCRIPTION |
|:----|:----|:----------|
| POST | /api/users/	| add new user |
| DELETE | /api/users/{id}	| delete user by id |
| PATCH | 	/api/users/{id}	| update user details by id |
| GET | /api/users/{id}	| get user by id |
| GET | /api/users/params	| get user by params(name, surname), it works according to one of the parameters, or both at once, if query without params - get all users |
| GET | /api/users/{id}/pets	| get pets by user by id |
| GET | /api/users/{id}/info	| get user details by id |
| GET | /api/users/{id}/verify	| user verification by id |

2. API Description for Details

METHOD	PATH	DESCRIPTION
POST	/api/pets/{id}	add pet
PATCH	/api/pets/{id}	update pet by id
DELETE	/api/pets/{id}	delete pet by id
GET	/api/pets	get all pets
GET	/api/pets/{id}	get pet by id
GET	/api/pets/{id}/records	get records by pet id
GET	/api/pets/{id}/verify	pet verification by id

Prerequisites:
Tomcat
Java 11
MySQl
