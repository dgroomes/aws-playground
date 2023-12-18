# lambda

This project builds an AWS Lambda function with Java 21 and exposes it via a Lambda *function URL*.


## Design

This project is implemented as a multi-module Gradle project:

* `hello-world-lambda/`
  * This is the module of importance. The `:hello-world-lambda` module represents the actual *Lambda function*. Specifically,
    it has a class that implements the AWS Handler interface `com.amazonaws.services.lambda.runtime.RequestHandler`.
* `simulator/`
  * Simulates the AWS Lambda deployment environment. This is a runnable Java program that serves the Lambda function in
    a local web server. This is useful for your local development workflow. The simulator maps the HTTP request to an
    event object and invokes the function. Related: [AWS Lambda docs: *Request and response payloads*](https://docs.aws.amazon.com/lambda/latest/dg/urls-invocation.html#urls-payloads).
    Similarly, it base64 encodes the payload, which Lambda also does.
* `echo/`
  * This is a toy library. It echos a given message into a JSON string.

The program is uploaded to AWS and a Lambda [*function URL*](https://docs.aws.amazon.com/lambda/latest/dg/lambda-urls.html) is configured to execute the Lambda function.

I've implemented my function with Java 21. Thankfully AWS Lambda was quick to support the 21 release of Java after only
about a month. While I'm also interested in using Lambda's support for Docker images, I need to stick to the "paved road"
to start with. I don't want to pile on caveats to my learning journey.


## Instructions

Follow these instructions to test the program, deploy it locally in a simulator, and deploy it to AWS as a Lambda function:

1. Pre-requisite: Java 21
2. Run the test suite:
   * ```shell
     ./gradlew test
     ```
3. Deploy the program locally in the Lambda simulator:
   * ```shell
     ./gradlew :simulator:run
     ```
   * A local web server is serving the program. Interact with it with the following `curl` command.
   * ```shell
     curl -X POST http://localhost:8080/ -d '{ "message": "Hello" }'
     ```
   * It should respond with something like:
     ```json
     {
       "message" : "Hello... Hello... Hello...",
       "deployment_environment" : "local"
     }
     ```
4. Build the program distribution:
   * ```shell
     ./gradlew :hello-world-lambda:buildZip
     ```
   * This builds the program distribution as a .zip file in `hello-world-lambda/build/distributions/hello-world-lambda.zip`
5. Deploy the Lambda function to AWS
   * Upload the program distribution .zip file using the AWS Lambda dashboard in the browser.
6. Try it!
   * Make a `curl` request like before, but this time direct it to the Lambda function URL. Find the URL in the AWS Console
     and export it as an environment variable named `HELLO_WORLD_LAMBDA_FUNCTION_URL`. Use the below command.
   * ```shell
     curl -X POST "$HELLO_WORLD_LAMBDA_FUNCTION_URL" -d '{ "message": "Hello" }'
     ```
   * It should print something like below. Notice the different value for the `deployment_environment` field.
     ```json
     {
       "message" : "Hello... Hello... Hello...",
       "deployment_environment" : "aws"
     }
     ```


## Observations

Here are some of my observations during my learning journey:

* AWS Lambda is inexpensive for use-cases that are not huge
* AWS Lambda gives a good developer experience, especially if you stay on the paved road. I would suggest, for example,
  that you stick with the official runtimes and not build a custom runtime. You are free to build a custom runtime, but
  if you can afford to exercise that amount of sophistication, I think you should consider deploying to a more generic
  product, like ECS (Elastic Container Service). Consider a Java-based Lambda function. You'll be tempted to create a
  custom Java runtime image because you just know in your bones how overpowered Java is for short-lived requests. Java
  excels at high throughput, where things like the just-in-time compiler kick in. So you might want to create a reduced-size
  custom Java runtime image for your Lambda function. But then your Lambda development experience has gone from a "upload this ZIP file"
  to "use jlink and a shell script to build a custom JRE with esoteric options, hard-to-explain value, and someting about
  AWS 'layers', and then also layer in your program onto it." Low reward in my opinion.
* I'm surprised that AWS Lambda doesnt' support Java 17 in May 2022, when Java 17 has been out for ever 6 months and it
  has been known for a few years that Java 17 was going to be an LTS release.
* I'm a bit annoyed that I have to forego a traditional HTTP toolkit and instead code to AWS's proprietary event schema.
  Specifically, I can't code my program to HTTP and instead code it to JSON-ified representations of HTTP requests. One
  gripe in particular is that the body is base64 encoded. So my Lambda function has to check for `getIsBase64Encoded` and
  then decode it. In HTTP toolkits, encoding and decoding happens transparently. For example, a framework like Micronaut
  or Spring will just decode a gzip HTTP request body and you never need to know.


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Implement `echo/`. Use Jackson.
* [x] DONE (this appeared to do what I needed it to, but I'm not sure if it's right. The libraries are in .jar files in
  the .zip but the main module ('hello-world-lambda') is in class files in directories. I'm not really sure what AWS Lambda
  needs exactly) Build the distribution zip
* [x] DONE Implement `hello-world-lambda`.
* [x] DONE Implement `simulator`.  Use Apache HttpComponents for a simple web server
* [x] DONE Rename 'runner' to 'simulator'
* [ ] Can HttpComponents serve ipv6?
* [ ] Consider renaming the 'echo' module something like 'data-enricher'. In playground examples, I want to model at
  vaguely realistic and useful problems and solutions. If I retool 'echo' as more of a message enricher which has to deal
  with JSON, then it becomes more interesting. And usefully, it can still be de-coupled from the Lambda code.


## Reference

* [AWS docs: *AWS Lambda function handler in Java*](https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html?icmpid=docs_lambda_help)
* [AWS docs: *Deploy Java Lambda functions with .zip or JAR file archives*](https://docs.aws.amazon.com/lambda/latest/dg/java-package.html)
* [AWS docs: *Lambda function URLs*](https://docs.aws.amazon.com/lambda/latest/dg/lambda-urls.html)
