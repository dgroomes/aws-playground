# aws-playground

NOT YET FULLY IMPLEMENTED

📚 Learning and exploring AWS (Amazon Web Services).

> Amazon Web Services (AWS) is the world’s most comprehensive and broadly adopted cloud platform, offering over 200
> fully featured services from data centers globally.
> --<cite>https://aws.amazon.com/what-is-aws/</cite>


## Description

**NOTE**: This project was developed on macOS. It is for my own personal use.

I'm going to start learning AWS by writing and deploying an AWS Lambda function using Java. I think this is one of the
easier things to do from a conceptual standpoint and it's useful too.

I'll implement my function in Java 11 because Lambda supports only up to Java 11 (Corretto) as of May 2022. While I'm
interested in using Lambda's support for Docker images, I need to stick to the "paved road" to start with. I don't want
to pile on caveats to my learning journey.


## Design

This project is designed across a multi-module Gradle project:

* `hello-world-lambda/`
  * The  is the module of importance. The `:hello-world-lambda` module represents the actual *Lambda function*. Specifically,
    it has a class (? class name) that implements the AWS Handler interface (? name).
* `runner/`
  * This is a runnable Java program that serves the Lambda function in a local web server. The `:runner` module is only
    useful for your local development workflow. This module does not get published to AWS.
* `echo/`
  * This is a toy library. It echos a given message into a JSON string. 


## Instructions

Follow these instructions to test the program, deploy it locally, and deploy it to AWS as a proper Lambda function:

1. Use Java 11
2. Run the test suite:
   * ```shell
     ./gradlew test
     ```
4. Build and deploy the program locally:
   * ```shell
     ./gradlew :runner:run
     ```
   * A local web server is serving the program. Interact with it with the following `curl` command.
   * ```shell
     curl http://[::1]:8080/hello-world
     ```
   * It should respond with something like:
     ```json
     {
       "message" : "Hello from an AWS Lambda function!",
       "deployment-environment" : "local"
     }
     ```
5. Build the program distribution:
   * ```shell
     ./gradlew :hello-world-lambda:buildZip
     ```
   * This builds the program distribution as a .zip file in `hello-world-lambda/build/distributions/hello-world-lambda.zip`
6. Deploy the lambda function to AWS
   * Upload the program distribution .zip file using the AWS Lambda dashboard in the browser.
7. Try it!
   * Try it out for size. Use a Lambda *Test Event* to exercise the Lambda function. It should print something like this:
     ```json
     {
       "message": "Hello from an AWS Lambda function!",
       "deployment-environment": "aws"
     }
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Implement `echo/`. Use Jackson.
* [x] DONE (this appeared to do what I needed it to, but I'm not sure if it's right. The libraries are in .jar files in
  the .zip but the main module ('hello-world-lambda') is in class files in directories. I'm not really sure what AWS Lambda
  needs exactly) Build the distribution zip
* [x] DONE Implement `hello-world-lambda`.
* [ ] Implement `runner`.  Use Apache HttpComponents for a simple web server


## Reference

* [AWS docs: *AWS Lambda function handler in Java*](https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html?icmpid=docs_lambda_help)
* [AWS docs: *Deploy Java Lambda functions with .zip or JAR file archives*](https://docs.aws.amazon.com/lambda/latest/dg/java-package.html)
