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
4. Build the program distribution:
   * ```shell
     ./gradlew installDist
     ```
5. Build the Docker image:
   * ```shell
     docker build . -t dgroomes-aws-playground-app-runner:local
     ```
6. Upload it
   * TODO
7. Deploy it
   * TODO


## Reference

* [AWS Elastic Container Registry](https://aws.amazon.com/ecr/)
