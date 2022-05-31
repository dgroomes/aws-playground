package dgroomes;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class HttpHandler implements HttpRequestHandler {
  private final ObjectWriter writer;

  {
    var mapper = JsonMapper.builder().propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE).build();
    writer = mapper.writerWithDefaultPrettyPrinter();
  }

  @Override
  public void handle(final ClassicHttpRequest request, final ClassicHttpResponse response, final HttpContext context) throws HttpException, IOException {
    Map<String, String> params = getQueryParams(request);

    var message = "You called this endpoint with %d query parameters. They are listed below.".formatted(params.size());

    ObjectNode returnNode = JsonNodeFactory.instance.objectNode();
    returnNode.put("message", message);

    for (Map.Entry<String, String> entry : params.entrySet()) {
      returnNode.put(entry.getKey(), entry.getValue());
    }

    var returnJson = writer.writeValueAsString(returnNode);
    StringEntity responseBody = new StringEntity(returnJson);
    response.setEntity(responseBody);
    response.addHeader("Content-Type", "application/json");
  }

  /**
   * Extract the query parameters from the HTTP request.
   * <p>
   * Note: It is surprisingly verbose to extract the query parameters from a {@link HttpRequest}.
   *
   * @return a map of the query parameters, keyed by named and in their original order.
   */
  private static Map<String, String> getQueryParams(HttpRequest request) {
    URI uri;
    try {
      uri = request.getUri();
    } catch (URISyntaxException ex) {
      throw new IllegalStateException("Failed to parse the URI of an incoming HTTP request", ex);
    }

    List<NameValuePair> queryParams = new URIBuilder(uri).getQueryParams();

    // Note: use a LinkedHashMap to preserve insertion order.
    LinkedHashMap<String, String> keyed = new LinkedHashMap<>();
    for (var queryParam : queryParams) {
      keyed.put(queryParam.getName(), queryParam.getValue());
    }

    return keyed;
  }
}
