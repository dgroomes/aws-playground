package dgroomes.runner;

import dgroomes.helloworldlambda.HelloWorldLambdaFunction;

public class Runner {
  public static void main(String[] args) {
    var message = HelloWorldLambdaFunction.hello();
    System.out.println(message);
  }
}
