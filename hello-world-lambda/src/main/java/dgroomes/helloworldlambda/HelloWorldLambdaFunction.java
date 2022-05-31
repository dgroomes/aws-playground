package dgroomes.helloworldlambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import dgroomes.echo.Echo;

/**
 * This class represents an AWS Lambda function.
 * <p>
 * This Lambda function happens to be configured with a Lambda "function URL" which means that the even type is
 * {@link APIGatewayProxyRequestEvent}.
 */
public class HelloWorldLambdaFunction implements RequestHandler<APIGatewayProxyRequestEvent, String> {

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
  public String handleRequest(APIGatewayProxyRequestEvent request, Context context) {
    String body = request.getBody();
    return echo.echo(body);
  }
}
