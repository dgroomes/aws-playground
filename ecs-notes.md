# ecs-notes

This was another failure! I followed both [Deploy a Container Web App on Amazon ECS](https://aws.amazon.com/getting-started/guides/deploy-webapp-ecs/module-two/)
and the very similar (yet slightly different so it makes you confused between the two) [Get Started with AWS CDK](https://aws.amazon.com/getting-started/guides/setup-cdk/)
and could not get it work. The docs made me think I have to use CloudFormation, and in turn the CDK CLI, but really I should
be able to use ECS directly, like you can with any other AWS product.

cdk-ecs-infra

EXPERIMENTAL

While I failed to get up and running using AWS App Runner, will I have success with ECS?

I'd like to do the simplest deployment of a web service. This means launching a container using the Fargate
launch type because it means I don't need to provision some other infrastructure things. But how much do I have to configure?
Do I really need the `cdk` CLI?

The project in this directory was scaffolded by a CDK template.


## Notes

* `cdk --profile cli bootstrap aws://<my-account-number>/us-east-1`
* `cdk deploy`???


## Reference

* [AWS docs: *Deploy a Container Web App on Amazon ECS*](https://aws.amazon.com/getting-started/guides/deploy-webapp-ecs/module-two/)
    * This is the tutorial I'm following. Looks promising. I like the step-by-step commands that it lists.
* [AWS docs: *Get Started with AWS CDK*](https://aws.amazon.com/getting-started/guides/setup-cdk/)


# Welcome to your CDK TypeScript project

This is a blank project for CDK development with TypeScript.

The `cdk.json` file tells the CDK Toolkit how to execute your app.

## Useful commands

* `npm run build`   compile typescript to js
* `npm run watch`   watch for changes and compile
* `npm run test`    perform the jest unit tests
* `cdk deploy`      deploy this stack to your default AWS account/region
* `cdk diff`        compare deployed stack with current state
* `cdk synth`       emits the synthesized CloudFormation template
