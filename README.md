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
| POST | /api/users	| add new user |
| DELETE | /api/users/{id}	| delete user by id |
| PATCH | 	/api/users/{id}	| update user details by id |
| GET | /api/users/{id}	| get user by id |
| GET | /api/users/params	| get user by params(name, surname), it works according to one of the parameters, or both at once, if query without params - get all users |
| GET | /api/users/{id}/pets	| get pets by user by id |
| GET | /api/users/{id}/info	| get user details by id |
| GET | /api/users/{id}/verify | user verification by id |

2. API Description for Details

| METHOD	| PATH	| DESCRIPTION |
|:----|:----|:----------|
| POST | /api/pets | add pet |
| PATCH | /api/pets/{id} | update pet by id |
| DELETE | /api/pets/{id} | delete pet by id |
| GET | /api/pets | get all pets |
| GET | /api/pets/{id} | get pet by id |
| GET | /api/pets/{id}/records | get records by pet id |
| GET | /api/pets/{id}/verify | pet verification by id |

3. API Description for Records

| METHOD	| PATH	| DESCRIPTION |
|:----|:----|:----------|
| POST | /api/records | add record |
| GET | /api/pets/records | get all records |

4. API Description for Doctors

| METHOD	| PATH	| DESCRIPTION |
|:----|:----|:----------|
| GET | /api/doctors/{id}/records | get all records by doctor id |

## Project requests are grouped as follows in Postman:

![Снимок экрана 2022-02-21 155617](https://user-images.githubusercontent.com/93032950/154959475-6381777b-5abc-49f7-8648-65bf9fdda22b.png)

## Click button to try
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/18900246-6e7d9081-2d01-4a7d-ac69-c86e6f9a2fd4?action=collection%2Ffork&collection-url=entityId%3D18900246-6e7d9081-2d01-4a7d-ac69-c86e6f9a2fd4%26entityType%3Dcollection%26workspaceId%3D29a1b792-b6be-4d8e-b462-87cfcadcbc45)

### Prerequisites:
- Tomcat
- Java 11
- MySQl


