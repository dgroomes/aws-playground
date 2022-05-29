package dgroomes.echo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.LinkedHashMap;

public class Echo {

  public final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
  private final String deploymentEnvironment;

  public Echo(String deploymentEnvironment) {
    this.deploymentEnvironment = deploymentEnvironment;
  }

  /**
   * Echo a message into a JSON document
   *
   * @param message a message to echo
   * @return a JSON document as a string. The document contains a 'message' field with an "echoed" version of the original
   * message
   */
  public String echo(String message) {

    var echoedMessage = message + "!"; // Add some flair to the given message

    // Note: use a LinkedHashMap to preserve insertion order when serializing to JSON.
    var map = new LinkedHashMap<String, String>();
    map.put("message", echoedMessage);
    map.put("deployment-environment", deploymentEnvironment);

    return serialize(map);
  }

  private String serialize(Object obj) {
    try {
      return writer.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Failed to serialize the message. This is unexpected", e);
    }
  }
}
