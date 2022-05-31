# app-runner

NOT YET IMPLEMENTED

An example microservice deployed to AWS App Runner.

## Instructions

Follow these instructions to build the program, build the Docker image, upload it to the AWS Elastic Container Registry (ECR), and
deploy it using AWS App Runner. 

1. Use Java 17
2. Run the program locally:
   * ```shell
     ./gradlew run
     ```
3. Exercise the server with a sample request:
   * ```shell
     curl "http://localhost:8080?who=me&favorite_color=blue"
     ```
   * It should respond with:
     ```json
     {
       "message": "You called this endpoint with 2 query parameters. They are listed below.",
       "who": "me",
       "favorite_color": "blue"
     }
     ```
4. Build the program distribution:
   * ```shell
     ./gradlew installDist
     ```
5. Build the Docker image:
   * ```shell
     docker build . -t dgroomes-aws-playground-app-runner:local
     ```
6. Run the program as a Docker container:
   * ```shell
     docker run --rm -p 8080:8080 dgroomes-aws-playground-app-runner:local
     ```
   * Consider using the earlier `curl` command to exercise the server.
8. Upload it
   * TODO
9. Deploy it
   * TODO


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Build the Docker image
* [ ] Upload to AWS


## Reference

* [AWS Elastic Container Registry](https://aws.amazon.com/ecr/)
