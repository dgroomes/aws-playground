package dgroomes.echo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoTest {

  @Test
  void echo() {
    String message = "Hi there";

    String echoedMessage = Echo.echo(message);

    assertEquals("Hi there!", echoedMessage);
  }
}
