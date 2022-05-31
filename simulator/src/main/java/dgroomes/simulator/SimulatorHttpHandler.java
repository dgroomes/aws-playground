package dgroomes.simulator;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This handles incoming HTTP requests and transforms them into simulated AWS Lambda "events" as if they had come from
 * API Gateway or a Lambda "function URL".
 */
class SimulatorHttpHandler implements HttpRequestHandler {

  private RequestHandler<APIGatewayProxyRequestEvent, String> lambdaRequestHandler;

  SimulatorHttpHandler(RequestHandler<APIGatewayProxyRequestEvent, String> lambdaRequestHandler) {
    this.lambdaRequestHandler = lambdaRequestHandler;
  }

  @Override
  public void handle(final ClassicHttpRequest request, final ClassicHttpResponse response, final HttpContext context) throws HttpException, IOException {
    String requestBody = EntityUtils.toString(request.getEntity());

    APIGatewayProxyRequestEvent apiGatewayRequest = new APIGatewayProxyRequestEvent();
    apiGatewayRequest.setBody(requestBody);

    String handlerResponse = lambdaRequestHandler.handleRequest(apiGatewayRequest, null);
    StringEntity responseBody = new StringEntity(handlerResponse);
    response.setEntity(responseBody);
  }

  /**
   * Extract the query parameters from the HTTP request.
   * <p>
   * Note: It is surprisingly verbose to extract the query parameters from a {@link HttpRequest}.
   *
   * @return a map of the query parameters, keyed by named.
   */
  private static Map<String, String> getQueryParams(HttpRequest request) {
    URI uri;
    try {
      uri = request.getUri();
    } catch (URISyntaxException ex) {
      throw new IllegalStateException("Failed to parse the URI of an incoming HTTP request", ex);
    }

    List<NameValuePair> queryParams = new URIBuilder(uri).getQueryParams();

    return queryParams.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
  }
}
