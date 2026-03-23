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
 * Represents a Spinosaurus
 *
 * These creatures are known for their large spines
 * and the signature spikes that protrude from them.
 * They are very scary.
 */
public class Spinosaurus extends Dinosaur {
  /**
   * Constructs a new Spinosaurus with the specified identity and age.
   *
   * This constructor initializes the dinosaur with species-specific defaults:
   * a FoodType.MEAT diet and a preferred area of 16.0 units.
   *
   * @param id The unique identifier for this individual.
   * @param name The name given to this individual.
   * @param age The age of the dinosaur in years.
   */
  public Spinosaurus(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 16);
  }

  /**
   * Returns the high-pitched, piercing vocalization of a Spinosaurus.
   * @return The string "GRRRRR!".
   */
  @Override 
  public String roar() {
    return "GRRRRR!";
  }

  /**
   * Determines if this Spinosaurus can share an enclosure with another dinosaur.
   *
   * For social reasons, this implementation only allows coexistence
   * with anyone. 
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if the other dinosaur is also a dinosaur.
   */
  @Override 
  public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur.getClass() == Spinosaurus.class) {
      return true;
    } 

    return otherDinosaur.canCoexistWith(this);
  }
}