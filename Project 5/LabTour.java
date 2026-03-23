/*
 * Author: Eric Gonzalez-Montijo
 * Email: gonzlezmonti@wisc.edu
 * Course: Computer Science 300, Spring 2026
 * Assignment:  Project 5
 * Citations: No LLM used, Canvas Style Guide, 
 * Collaboration with Gavin Garrison, ArrayList JavaDoc, 
 * Types of Exceptions JavaDoc, Interfaces JavaDoc
 */

import java.util.ArrayList;

/**
 * Represents an educational tour through the park's research facilities.
 *
 * This class monitors the safety of visitors and staff during active laboratory
 * sessions. It implements SafetyCheckable to ensure that the sum of
 * researchers and tourists does not exceed the structural and safety limits
 * of the laboratory environment.
 *
 */
public class LabTour implements SafetyCheckable {

  /**
   * The maximum number of individuals (staff and visitors combined)
   * allowed in the lab at any single time for safety reasons.
   */
  public static final int LAB_CAPACITY = 10;

  private String name;
  private int visitors;
  private ArrayList<Experiment> activeExperiments;

  /**
   * Constructs a new LabTour with a specific name and initial visitor count.
   *
   * @param name The name or designation of the lab tour.
   * @param visitors The number of tourists currently on the tour.
   */
  public LabTour(String name, int visitors) {
    this.name = name;
    this.visitors = visitors;
    this.activeExperiments = new ArrayList<>();
  }

  /**
   * Adds an active research experiment to the tour's itinerary.
   *
   * @param experiment The Experiment to be added.
   */
  public void addExperiment(Experiment experiment) {
    this.activeExperiments.add(experiment);
  }

  /**
   * Removes an experiment from the lab tour.
   *
   * @param experiment The Experiment to be removed.
   */
  public void removeExperiment(Experiment experiment) {
    this.activeExperiments.remove(experiment);
  }

  /**
   * Evaluates the safety of the laboratory based on human occupancy.
   *
   * This check calculates the sum of all visitors and all staff members
   * assigned to currently active experiments. 1 violation occurs if
   * the total headcount exceeds LAB_CAPACITY.
   *
   * @return 1 if the total number of individuals exceeds the capacity, or 0 if within limits.
   */
  @Override
  public int countHumanSafetyViolations() {
    int totalStaff = 0;

    for (int i = 0; i < activeExperiments.size(); i++) {
      totalStaff += activeExperiments.get(i).getStaff();
    }

    if ((visitors + totalStaff) > LAB_CAPACITY) {
      return 1;
    }

    return 0;
  }

  /**
   * Performs a dinosaur safety check for the laboratory.
   *
   * Since visitors and active experiments are contained within
   * human-only facilities, this always returns 0 violations.
   *
   * @return Always 0.
   */
  @Override
  public int countDinoSafetyViolations() {
    return 0;
  }

  /**
   * Returns a detailed summary of the lab tour, including its name,
   * visitor count, and a list of all active experiments.
   *
   * @return A formatted string representation of the tour.
   */
  @Override
  public String toString() {
    StringBuilder labTourString = new StringBuilder(
        String.format("Lab Tour: %s, visitors=%d\n", this.name, this.visitors)
    );
    for (Experiment experiment : this.activeExperiments) {
      labTourString.append(String.format("%s\n", experiment.toString()));
    }
    return labTourString.toString();
  }
}
