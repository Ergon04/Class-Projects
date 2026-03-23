/**
 * Exception thrown when a bank account has insufficient funds for a requested operation.
 * This is a checked exception since it represents a recoverable business condition.
 */
public class InsufficientFundsException extends Exception {
  /**
   * Creates a new InsufficientFundsException with the specified message.
   * @param message the detail message explaining the insufficient funds condition
   */
  public InsufficientFundsException(String message) {
    super(message);
  }
}