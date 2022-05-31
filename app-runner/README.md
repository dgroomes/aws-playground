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
     docker build . -t aws-playground-app-runner:local
     ```
6. Run the program as a Docker container:
   * ```shell
     docker run --rm -p 8080:8080 aws-playground-app-runner:local
     ```
   * Consider using the earlier `curl` command to exercise the server.
8. Upload the image to Amazon Elastic Container Registry (ECR)
   * You'll need to get acquainted with ECR by reading the docs. This might take a while because you need to do things
     like configure the `aws` CLI and create IAM profiles, if you haven't already. I have a profile named `cli` that I
     gave the bare minimum permissions that I need for ECR. I used a command like the following to login my local `docker`
     client to my ECR repo.
   * ```shell
     aws --profile cli ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin "$AWS_ECS_REGISTRY"
     ```
   * Finally, tag the Docker image with a unique tag (I enabled
     immutable images in my ECR repo) with a command like the following.
   * ```shell
     docker tag aws-playground-app-runner:local "$AWS_ECS_REGISTRY/aws-playground-app-runner:1"
     ```
   * Push the image
   * ```shell
     docker push "$AWS_ECS_REGISTRY/aws-playground-app-runner:1"
     ```
9. Deploy it
   * TODO


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Build the Docker image
* [x] DONE Upload to AWS ECR
* [ ] Deploy to App Runner


## Reference

* [AWS Elastic Container Registry](https://aws.amazon.com/ecr/)
