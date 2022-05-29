package dgroomes.echo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EchoTest {

  @Test
  void echo() {
    Echo echo = new Echo("test-suite");
    String message = "Hi there";

    String json = echo.echo(message);

    assertThat(json).isEqualToIgnoringWhitespace("{\"message\":\"Hi there!\", \"deployment-environment\":\"test-suite\"}");
  }
}
