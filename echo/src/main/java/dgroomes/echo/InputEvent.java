package dgroomes.echo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * This is a domain type. It models an input event that is passed by some other system of interest.
 */
public class InputEvent {
  public final String message;

  /**
   * Note: what is the point of the {@link JsonProperty} annotation here? See https://github.com/dgroomes/jackson-playground/blob/92d1f59802f1406a8e1590960d5be699db9d11be/src/main/java/dgroomes/singleargconstructor/SingleArgConstructor.java#L4
   */
  public InputEvent(@JsonProperty("message") String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InputEvent that = (InputEvent) o;
    return Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    return "InputEvent{" +
            "message='" + message + '\'' +
            '}';
  }
}
