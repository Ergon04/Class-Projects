/**
 * Exception thrown when an invalid account operation is attempted.
 * This is an unchecked exception as it typically represents a programming error.
 * Examples include duplicate accounts, invalid account numbers, or accounts not found.
 */
public class InvalidAccountException extends RuntimeException {

  /**
   * Creates a new InvalidAccountException with the specified message.
   * @param message the detail message explaining the invalid account condition
   */
  public InvalidAccountException(String message) {
    super(message);
  }
}