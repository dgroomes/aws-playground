package dgroomes.helloworldlambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dgroomes.echo.Echo;

import java.util.Map;

/**
 * This class represents an AWS Lambda function.
 */
public class HelloWorldLambdaFunction implements RequestHandler<Map<String, String>, String> {

  private final Echo echo;

  /**
   * The default constructor will be called by AWS Lambda.
   */
  public HelloWorldLambdaFunction() {
    this.echo = new Echo("aws");
  }

  /**
   * This is a non-default constructor. It will be called by user-code like the JUnit test-suite or the ':simulator' module.
   */
  public HelloWorldLambdaFunction(Echo echo) {
    this.echo = echo;
  }

  @Override
  public String handleRequest(Map<String, String> input, Context context) {
    return echo.echo("Hello from an AWS Lambda function");
  }
}
