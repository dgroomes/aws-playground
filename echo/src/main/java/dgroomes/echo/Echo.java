package dgroomes.echo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * A toy class.
 */
public class Echo {

  private final ObjectMapper mapper;
  private final ObjectWriter writer;
  private final String deploymentEnvironment;

  public Echo(String deploymentEnvironment) {
    this.deploymentEnvironment = deploymentEnvironment;
    mapper = JsonMapper.builder()
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .build();

    writer = mapper.writerWithDefaultPrettyPrinter();
  }

  /**
   * Given a JSON document with a 'message' field, echo the given message with some flair, and return it inside a new
   * JSON document.
   *
   * @param inputJson a JSON document containing a 'message' field.
   * @return a new JSON document. The document contains a 'message' field with an "echoed" version of the original
   * message
   */
  public String echo(String inputJson) {
    InputEvent input = parseAndValidate(inputJson);
    OutputEvent output = echo(input);
    return serialize(output);
  }

  private OutputEvent echo(InputEvent event) {
    var echoedMessage = event.message + "... " + event.message + "... " + event.message + "...";
    return new OutputEvent(echoedMessage, deploymentEnvironment);
  }

  /**
   * Parse and validate the incoming JSON.
   *
   * @throws IllegalArgumentException if the JSON is not malformed or is missing required fields
   */
  private InputEvent parseAndValidate(String jsonInput) {
    if (jsonInput == null || jsonInput.isBlank()) {
      throw new IllegalArgumentException("The JSON input must not be empty.");
    }

    InputEvent input;
    try {
      input = mapper.readValue(jsonInput, InputEvent.class);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("The input JSON could not be parsed.", e);
    }

    return input;
  }

  private String serialize(Object obj) {
    try {
      return writer.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Failed to serialize the message. This is unexpected", e);
    }
  }
}
