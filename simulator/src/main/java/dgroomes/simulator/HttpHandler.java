package dgroomes.simulator;

import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;

/**
 * This handles incoming HTTP requests and transforms them into simulated AWS Lambda "events".
 */
class HttpHandler implements HttpRequestHandler {

  @Override
  public void handle(final ClassicHttpRequest request, final ClassicHttpResponse response, final HttpContext context) throws HttpException, IOException {
    var body = new StringEntity("Hello from the Lambda simulator! The handler is not implemented yet.");
    response.setEntity(body);
  }
}
