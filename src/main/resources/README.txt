There will be two default users in a database after its creating. Use these JSONs in a request's body:

    1.{
               "username": "anna",
               "password": "password"
       }
    2.{
              "username": "linda",
              "password": "password"
      }

    User "anna" has role USER and next authority: book:read
    User "linda" has role ADMIN and next authorities: book:read, book:write

If you don't want to login, just use these JWT-token in a header "Authentication":
    1. ROLE_USER: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbm5hIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6ImJvb2s6cmVhZCJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2MTM3NzI0NTYsImV4cCI6MTYxNDk4MTYwMH0.5yBMWqgWKXRhDhfZipRrKveimRCWz-YiEU-RAJ2njB9A3K5y9XwOpcYTujlbWvIAFD7CrlFdmZouOprxqWwWAA"
    2. ROLE_ADMIN: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsaW5kYSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJib29rOnJlYWQifSx7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifSx7ImF1dGhvcml0eSI6ImJvb2s6d3JpdGUifV0sImlhdCI6MTYxMzc3MzEwOSwiZXhwIjoxNjE0OTgxNjAwfQ.m267LKGHrRcB6kq7E-Av7aEFV78PLarnZiJg5txaXIvX16Z4NTxbYELem5DrJJIG_zxWkDa6PQ3ZuyLw29w99Q"

The results of requests for users with different roles are illustrated in the file results.pdf