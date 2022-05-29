package dgroomes.helloworldlambda;

import dgroomes.echo.Echo;

/**
 * NOT YET FULLY IMPLEMENTED
 *
 * This class represents an AWS Lambda function.
 */
public class HelloWorldLambdaFunction {

  private final Echo echo;
  public HelloWorldLambdaFunction(Echo echo) {
    this.echo = echo;
  }

  public String hello() {
    return echo.echo("Hello from an AWS Lambda function");
  }
}
