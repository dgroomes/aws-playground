package dgroomes.runner;

import dgroomes.echo.Echo;
import dgroomes.helloworldlambda.HelloWorldLambdaFunction;

public class Runner {
  public static void main(String[] args) {
    HelloWorldLambdaFunction helloWorldLambdaFunction = new HelloWorldLambdaFunction(new Echo("local"));

    var json = helloWorldLambdaFunction.hello();
    System.out.println(json);
  }
}
