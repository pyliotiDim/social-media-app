# social-media

Rest API for social media app.

This is a CRUD API for Posts and Comments. 
For Authentication we use JWT tokens.
Authorization is beyond the scope of this project.

ΙInsert test user on start up:
username: admin
password: admin

In order to be able to use the Api the jwtToken in headers is required.
If you want to take the refresh token to use the Api the AUTHORIZATION in headers is required.

* Get comments of a post.

GET: 
url:localhost:8080/post/{id}/comment?size=2&page=0 

* Get all the posts on the platform in chronological order.

GET:
url:localhost:8080/post/{id}?size=2&page=0&sort=createDate,desc or localhost:8080/post/{id}?size=2&page=0&sort=createDate,asc


Let's see the database, we have tree table one for Users, one for Comments and one for Posts. 
User can have many posts and many comments. Posts can have many comments but only one user can create a post.
Comments can create by one user. 


# Technologies used:
* Java 11
* Spring Boot Framework
* Spring Data JPA
* Embedded H2
* Spring Security

