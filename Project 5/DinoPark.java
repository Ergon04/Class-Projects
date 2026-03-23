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
 * Acts as the primary management container for the park's various attractions.
 * This class maintains a collection of objects that implement the SafetyCheckable
 * interface, allowing the park to run comprehensive safety audits across different
 * sectors (such as enclosures or research labs).
 */
public class DinoPark {

  private ArrayList<SafetyCheckable> attractions = new ArrayList<SafetyCheckable>();
  

  /**
   * Registers a new attraction or safety-monitored entity into the park system.
   * @param attraction the SafetyCheckable entity to be added.
   * @throws IllegalArgumentException if the provided attraction is {@code null}.
   */
  public void addAttraction(SafetyCheckable attraction) {
    if (attraction == null) {
      throw new IllegalArgumentException("Attraction can not be null");
    }
    
    attractions.add(attraction);    

  }

  /**
   * Compiles a comprehensive safety report by auditing every registered attraction.
   *
   * The report includes a breakdown of both Human Safety Violations and
   * Dinosaur Safety Violations for each entry in the park.
   *
   * @return A formatted string containing the full safety audit report.
   */
  public String generateSafetyReport() {
    StringBuilder report = new StringBuilder(10000);

    for (int i = 0; i < attractions.size(); i++) {
      report.append("=== SAFETY REPORT ===\n");
      report.append(attractions.get(i).toString() + "\n");
      report.append("Human Safety Violations: ");
      report.append(attractions.get(i).countHumanSafetyViolations() + "\n");
      report.append("Dinosaur Safety Violations: ");
      report.append(attractions.get(i).countDinoSafetyViolations() + "\n");
    }

    return report.toString();
  }
}
