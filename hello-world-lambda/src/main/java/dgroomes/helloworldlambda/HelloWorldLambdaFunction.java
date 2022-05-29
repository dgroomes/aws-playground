package dgroomes.helloworldlambda;

import dgroomes.echo.Echo;

/**
 * NOT YET FULLY IMPLEMENTED
 *
 * This class represents an AWS Lambda function.
 */
public class HelloWorldLambdaFunction {

  public static String hello() {
    return Echo.echo("Hello world from a Lambda function");
  }
}
