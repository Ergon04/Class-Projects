/*
 * Author: Eric Gonzalez-Montijo
 * Email: gonzlezmonti@wisc.edu
 * Course: Computer Science 300, Spring 2026
 * Assignment:  Project 5
 * Citations: No LLM used, Canvas Style Guide, 
 * Collaboration with Gavin Garrison, ArrayList JavaDoc, 
 * Types of Exceptions JavaDoc, Interfaces JavaDoc
 */

/**
 * Represents a scientific experiment or research project within the facility.
 *
 * This class tracks the descriptive details of the experiment and the
 * number of personnel required to conduct or monitor the research safely.
 *
 */
public class Experiment {
  private String description;
  private int staff;

  /**
   * Constructs a new Experiment with the given details.
   * @param description A brief summary of the experiment's goals.
   * @param staff The number of staff members assigned to this project.
   * @throws IllegalArgumentException if staff is < 1
   */
  public Experiment(String description, int staff) {
    if (staff <= 0) {
      throw new IllegalArgumentException("Staff must be greater than zero");
    }
    
    this.description = description;
    this.staff = staff;
  }

  /**
   * Retrieves the description of the experiment.
   * @return The description string.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Retrieves the total number of staff members assigned to the experiment.
   * @return The staff count.
   */
  public int getStaff() {
    return staff;
  }

  /**
   * Updates the description of the experiment.
   * @param description The new description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Updates the number of staff members assigned to the experiment.
   * @param staff The new staff count to set.
   */
  public void setStaff(int staff) {
    this.staff = staff;
  }

  /**
   * Returns a string representation of the experiment,
   * including its description and staff count.
   * @return A formatted string describing the experiment.
   */
  @Override
  public String toString() {
    return String.format("%s, staff=%d", this.description, this.staff);
  }
}
