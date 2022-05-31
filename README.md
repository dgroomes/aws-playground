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


## Standalone sub-projects

This repository illustrates different concepts, patterns and examples via standalone sub-projects. Each sub-project is
completely independent of the others and do not depend on the root project. This _standalone sub-project constraint_
forces the sub-projects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The sub-projects include:

### `lambda/`

This project builds an AWS Lambda function with Java 11 and exposes it via a Lambda *function URL*.

See the README in [lambda/](lambda/).


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
* [ ] IN PROGRESS Make an AWS App Runner example. To me, App Runner is at the same level of convenience as Lambda. It's just at a (much) 
  higher price point because of the always-on thing.

## Reference

* [AWS Lambda](https://aws.amazon.com/lambda/)
* [AWS App Runner](https://aws.amazon.com/apprunner/)
