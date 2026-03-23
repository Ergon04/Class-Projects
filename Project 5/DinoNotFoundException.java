/**
 * Exception thrown when a specific dinosaur cannot be located within the park system.
 *
 * This is a checked exception typically used by lookup or removal operations
 * (such as in Habitat.removeDinosaur(String)) when a provided unique identifier
 * does not match any dinosaurs in the current context.
 *
 */
public class DinoNotFoundException extends Exception {

  /**
   * Constructs a new DinoNotFoundException with an error message.
   *
   * @param message The descriptive message explaining the failure
   */
  public DinoNotFoundException(String message) {
    super(message);
  }
}
