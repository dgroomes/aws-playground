package dgroomes.echo;

import java.util.Objects;

/**
 * This is a domain type. It models an output event that is passed to some other system of interest.
 */
public class OutputEvent {
  public final String message;
  public final String deploymentEnvironment;

  public OutputEvent(String message, String deploymentEnvironment) {
    this.message = message;
    this.deploymentEnvironment = deploymentEnvironment;
  }

  @Override
  public String toString() {
    return "Event{" +
            "message='" + message + '\'' +
            ", deploymentEnvironment='" + deploymentEnvironment + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OutputEvent event = (OutputEvent) o;
    return Objects.equals(message, event.message) && Objects.equals(deploymentEnvironment, event.deploymentEnvironment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, deploymentEnvironment);
  }
}
