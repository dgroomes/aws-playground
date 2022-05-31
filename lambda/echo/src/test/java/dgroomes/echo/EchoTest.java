package dgroomes.echo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EchoTest {

  @Test
  void echo() {
    Echo echo = new Echo("test-suite");
    String inputJson = "{ \"message\": \"Hi\" }";

    String outputJson = echo.echo(inputJson);

    assertThat(outputJson).isEqualToIgnoringWhitespace("{\"message\":\"Hi... Hi... Hi...\", \"deployment_environment\":\"test-suite\"}");
  }
}
