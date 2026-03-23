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
 * Represents a Triceratops
 *
 * These creatures are known for their massive skulls
 * and the signature horns that protrude from them.
 * They would use these horns to impale their prey.
 */
public class Triceratops extends Dinosaur {
  /**
   * Constructs a new Triceratops with the specified identity and age.
   *
   * This constructor initializes the dinosaur with species-specific defaults:
   * a FoodType.PLANTS diet and a preferred area of 6.0 units.
   *
   * @param id The unique identifier for this individual.
   * @param name The name given to this individual.
   * @param age The age of the dinosaur in years.
   */
  public Triceratops(String id, String name, int age) {
    super(id, name, age, FoodType.PLANTS, 6);
  }

  /**
   * Returns the high-pitched, piercing vocalization of a Triceratops.
   * @return The string "HMMMPH!".
   */
  @Override 
  public String roar() {
    return "HMMMPH!";
  }

  /**
   * Determines if this Triceratops can share an enclosure with another dinosaur.
   *
   * For safety and social reasons, this implementation only allows coexistence
   * with other herbivores. Any dinosaur with a diet other than FoodType.PLANT
   * is considered incompatible.
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if the other dinosaur is also a meat-eater; false otherwise.
   */
  @Override 
  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur.getFoodType() == FoodType.MEAT) {
      return false;
    }
    return true;
  }
}