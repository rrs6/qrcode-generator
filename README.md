# Project Description - QRCODE GENERATOR
A simple API for generating QR Codes and upload to AWS S3 from text provided in the request body.

### Tecnologies
- Java Spring Boot (Spring Web, Spring Dev Tools).
- AWS S3.
- Docker

### How to Run
There's two way to run this project:
### First Way - Docker
The steps are:
* `docker build -t "name_of_image" .`
* `docker run --env-file .env -p 8080:8080 "name_of_image`

### Second Way - VS Code
Basically, install all the extensions needed to run a Java Spring Boot project.

It's necessary to create a env file and set the following args:
* `AWS_SECRET_ACCESS_KEY`, `AWS_ACCESS_KEY_ID`, `AWS_REGION=us-east-1`, `AWS_BUCKET_NAME`

Secrets are generated in IAM roles in AWS Console.

### Structure Project
The structure project is based on Hexagonal Architecture, there is one implementation of a port storage, called S3Storage.

* Controller -> Service -> S3Storage

### Some questions that arise during the development process

- What is a @Component?
A component is an annotation in java that mark a class to be used in a service.