# ecs

NOTE: I could not get this to work, but I'm still checking in this unfinished project for posterity.

Can I do a "Hello world" in ECS?


## Notes

Here is an unorganized (could be out of order) collection of commands I ran and notes.

So it turns out I have a default security group, default VPC, etc.

* `aws --profile cli ecs create-cluster --cluster-name fargate-cluster --region us-east-1`
* `aws --profile cli --region us-east-1 ecs register-task-definition --cli-input-json "file://$PWD/task.json"`
* `aws --profile cli ecs list-task-definitions`
* `aws --profile cli ecs create-service --cluster fargate-cluster --service-name fargate-service --task-definition sample-fargate:1 --desired-count 1 --launch-type "FARGATE" --network-configuration "awsvpcConfiguration={subnets=[subnet-0743e83fb4b4551f4],securityGroups=[sg-030b9ba7e7d5b81a5]}"`
* `aws --profile cli ecs list-services --cluster fargate-cluster`
* `aws --profile cli ecs describe-services --cluster fargate-cluster --services fargate-service`
* `aws --profile cli ecs describe-tasks --cluster fargate-cluster --tasks fargate-tasks`
* `aws --profile cli ecs describe-clusters --cluster fargate-cluster`

Ok, I've found yet another tutorial in the AWS docs that is worded in the style of "Here is your first example of running
something on ECS". This is the third "first tutorial" I've found. This segues into the third and fourth AWS CLIs I've found too.
The two new entries are AWS Copilot (`copilot`) and the ECS CLI (`ecs-cli`). I've been burned by jumping into too far too
early. I want to follow the [Getting started with Amazon ECS using the classic console
](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/getting-started-console.html) way instead. Update: no the
"first run wizard" doesn't appear for me. Is it because I've already created clusters/services because I started the other
tutorial?


> The networkInterface ID 'eni-0470c9c7c45ca2457' does not exist

Wow, you can even deploy Docker containers to [Elastic Beanstalk](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_docker_ecs.html#create_deploy_docker_ecs_images).
I think EBS is mostly a relic so I'll ignore it. Although frankly it is tempting because it looks simple.

Oh goodie I've lucked into the actual "first run" (also called the wizard?): <https://us-west-2.console.aws.amazon.com/ecs/home?ad=c&cp=bn&p=frg&region=us-west-2#/firstRun>.

The wizard 

## Reference

* [AWS docs: *Tutorial: Creating a cluster with a Fargate Linux task using the AWS CLI*](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ECS_AWSCLI_Fargate.html#ECS_AWSCLI_Fargate_register_task_definition)
  * I'm hoping I can get this to work. It's a much less ambition goal than using CloudFormation and CDK.
* [AWS docs: *Set up to use Amazon ECS*](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/get-set-up-for-amazon-ecs.html)
* [AWS docs: *Work with VPCs*](https://docs.aws.amazon.com/vpc/latest/userguide/working-with-vpcs.html)
* [AWS docs: *Using the AWS Copilot command line interface*](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/AWS_Copilot.html)
