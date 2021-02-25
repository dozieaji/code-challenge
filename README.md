# Code-Challenge-Application [![HitCount](https://code-challenge.shedrackaji.repl.co)]


<hr>

## About this project
This an API web server that accepts a snippet of text and makes that snippet
available at a URL. Each snippet will be available as text at a URL until it
expires. The snippet's expiry should be extended by 30 seconds each time it is accessed.

This Application is being developed using H2 with Spring Boot. Just like other databases, there's full intrinsic support for it in the Spring Boot ecosystem.

 

## Assumptions 
	Considering the Use case for the project. There's a need for embedded database and that's why I used H2 Database. And since multiple concurrent browser sessions are supported and the database objects reside on the server, the amount of concurrent work is limited by the memory available to the server application.


##Design and technolies used and reasons
	Spring boot reduces lots of development time and increases productivity. It avoids writing lots of boilerplate Code, Annotations and XML Configuration. It is very easy to integrate Spring Boot Application with its Spring Ecosystem like Spring JDBC, Spring ORM, Spring Data, Spring Security etc.
	I decided to use a in memory databse to save the data and then manage the session based on use case.
	

## Run the project via the url provided on postman



1. SAMPLE POST REQUEST
URL: https://code-challenge.shedrackajiaji.repl.co/snippets 
{
"name":"recipe", 
"expires_in": 30, 
"snippet":"1 apple"
}


{
    "url": "http://code-challenge.shedrackajiaji.repl.co/snippets/recipe",
    "name": "recipe",
    "snippet": "1 apple",
    "likes": 0,
    "expires_at": "2021-02-25T15:17:53.644212"
}

2. SAMPLE GET REQUEST
URL https://code-challenge.shedrackajiaji.repl.co/snippets/recipe


SAMPLE RESPONSE
{
    "url": "http://code-challenge.shedrackajiaji.repl.co/snippets/recipe",
    "name": "recipe",
    "snippet": "1 apple",
    "likes": 0,
    "expires_at": "2021-02-25T15:18:04.297551"
}


3. SAMPLE POST REQUEST
URL: https://code-challenge.shedrackajiaji.repl.co/snippets/recipe/like

SAMPLE RESPONSE
{
    "url": "http://code-challenge.shedrackajiaji.repl.co/snippets/recipe",
    "name": "recipe",
    "snippet": "1 apple",
    "likes": 2,
    "expires_at": "2021-02-25T15:18:11.964207"
} 
