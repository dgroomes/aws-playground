# aws-playground

📚 Learning and exploring AWS (Amazon Web Services) by example.

> Amazon Web Services (AWS) is the world’s most comprehensive and broadly adopted cloud platform, offering over 200
> fully featured services from data centers globally.
> --<cite>https://aws.amazon.com/what-is-aws/</cite>


## Description

**NOTE**: This project was developed on macOS. It is for my own personal use.

I need to learn some public cloud skills and AWS is the easiest choice. In this project I started my AWS learning journey
by writing and deploying an AWS Lambda function using Java. Lambda is a great product. It's useful, conceptually simple,
and cost-effective. I want to explore other products, like [AWS App Runner](https://aws.amazon.com/apprunner/).


## Standalone subprojects

This repository illustrates different concepts, patterns and examples via standalone subprojects. Each subproject is
completely independent of the others and do not depend on the root project. This _standalone subproject constraint_
forces the subprojects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The subprojects include:


### `lambda/`

This project builds an AWS Lambda function with Java 11 and exposes it via a Lambda *function URL*.

See the README in [lambda/](lambda/).


### `app-runner/`

An example microservice deployed to AWS App Runner.

See the README in [app-runner/](app-runner/).


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] ABANDONED (Couldn't get this to work) Make an AWS App Runner example. To me, App Runner is at the same level of convenience as Lambda. It's just at a (much) 
  higher price point because of the always-on thing.


## Reference

* [AWS Lambda](https://aws.amazon.com/lambda/)
* [AWS App Runner](https://aws.amazon.com/apprunner/)
* [AWS Elastic Container Service (ECS)](https://aws.amazon.com/ecs/)
