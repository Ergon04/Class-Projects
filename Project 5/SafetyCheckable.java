/**
 * Defines a contract for entities within the park that must undergo regular safety audits.
 *
 * Any class implementing this interface must provide specific logic to evaluate
 * risks to both human personnel and dinosaur residents. These checks are
 * utilized by the DinoPark system to generate comprehensive safety reports.
 *
 */
public interface SafetyCheckable {

  /**
   * Performs an audit of the risks posed to park staff and visitors.
   * @return The total number of detected human safety violations.
   */
  int countHumanSafetyViolations();

  /**
   * Performs an audit of the risks posed to the dinosaurs' well-being.
   * @return The total number of detected dinosaur safety violations.
   */
  int countDinoSafetyViolations();
}
