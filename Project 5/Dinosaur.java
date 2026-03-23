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
 * Represents the base abstract definition of a Dinosaur within the management system.
 *
 * This class serves as a foundational template for all specific dinosaur species.
 * It encapsulates shared biological and administrative data, such as unique identifiers,
 * age, and dietary needs.
 *
 * Because this class is abstract, it cannot be instantiated directly. Subclasses
 * (e.g., Trex, Triceratops) must provide concrete implementations
 * for species-specific behaviors like vocalizations and social compatibility rules.
 *
 */
public abstract class Dinosaur {
  private String id;
  private String name;
  private int age; // years
  private FoodType foodType;
  private double preferredArea;

  /**
   * Constructs a new Dinosaur with the specified attributes.
   * This constructor validates that all strings are non-blank,
   * the age is non-negative, and the area requirements are positive.
   *
   * @param id The unique identifier for the dinosaur; must not be null or blank.
   * @param name The name of the dinosaur; must not be null or blank.
   * @param age The age of the dinosaur in years; must be 0 or greater.
   * @param foodType The dietary classification of the dinosaur.
   * @param preferredArea The minimum required living space
   * in square meters; must be greater than 0.
   * @throws IllegalArgumentException if id or name is null/blank,
   * if age is negative,
   * or if preferredArea is less than or equal to zero.
   */
  protected Dinosaur(String id, String name, int age, FoodType foodType, double preferredArea) {
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("ID can not be empty or null");
    }
    
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name can not be empty or null");
    }

    if (age < 0) {
      throw new IllegalArgumentException("Age can not be negative");
    }

    if (preferredArea <= 0) {
      throw new IllegalArgumentException("Age can not be negative");
    }
    
    this.id = id;
    this.name = name;
    this.age = age;
    this.foodType = foodType;
    this.preferredArea = preferredArea;

  }

  /**
   * Returns the vocalization or roar of the specific dinosaur species.
   * @return A string representing the dinosaur's sound.
   */
  public abstract String roar();

  /**
   * Determines if this dinosaur can safely share an enclosure with another dinosaur.
   * Implementation should consider species compatibility, diet, and temperament.
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if they can coexist; false otherwise.
   */
  public abstract boolean canCoexistWith(Dinosaur otherDinosaur);

  // --- Getters ---

  /**
   * Returns the unique identifier for this dinosaur.
   * @return The ID string.
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the name given to this dinosaur.
   * @return The dinosaur's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the age of the dinosaur in years.
   * @return The age value.
   */
  public int getAge() {
    return age;
  }

  /**
   * Returns the dietary classification of this dinosaur.
   * @return The FoodType enum value.
   */
  public FoodType getFoodType() {
    return foodType;
  }

  /**
   * Returns the required minimum living space for this dinosaur.
   * @return The preferred area in square meters.
   */
  public double getPreferredArea() {
    return preferredArea;
  }

  /**
   * Returns a string representation of the dinosaur, including its name, ID, and age.
   * @return A formatted string describing the dinosaur.
   */
  @Override
  public String toString() {
    return String.format("%s, [%s], age=%d", this.name, this.id, this.age);
  }
}
