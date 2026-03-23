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
 * Represents a TRex, also known as a Tyrannosaurus rex
 *
 * With their large size and teeth they dwarf over 
 * other dinosaurs within their time period. 
 * They are often characterized by their small arms.
 */
public class TRex extends Dinosaur {
  /**
   * Constructs a new TRex with the specified identity and age.
   *
   * This constructor initializes the dinosaur with species-specific defaults:
   * a FoodType.MEAT diet and a preferred area of 20.0 units.
   *
   * @param id The unique identifier for this individual.
   * @param name The name given to this individual.
   * @param age The age of the dinosaur in years.
   */
  public TRex(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 20);
  }

  /**
   * Returns the high-pitched, piercing vocalization of a TRex.
   * @return The string "ROOOAAAR!".
   */
  @Override 
  public String roar() {
    return "ROOOAAAR!";
  }

  /**
   * Determines if this TRex can share an enclosure with another dinosaur.
   *
   * For safety and social reasons, this implementation only allows coexistence
   * with other TRex. 
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if the other dinosaur is also a TRex; false otherwise.
   */
  @Override 
  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur == this) {
      return true;
    } else {
      return false;
    }
  }
}