/**
 * Represents a Velociraptor, a highly intelligent and agile carnivorous dinosaur.
 *
 * Velociraptors are pack hunters characterized by their speed and predatory nature.
 * In this system, they are pre-configured with a dietary type of FoodType.MEAT
 * and a preferred area requirement of 8.0 units. Unlike gentler species, Velociraptors
 * have specific social requirements for coexistence.
 *
 */
public class Velociraptor extends Dinosaur {

  /**
   * Constructs a new Velociraptor with the specified identity and age.
   *
   * This constructor initializes the dinosaur with species-specific defaults:
   * a FoodType.MEAT diet and a preferred area of 8.0 units.
   *
   * @param id The unique identifier for this individual.
   * @param name The name given to this individual.
   * @param age The age of the dinosaur in years.
   */
  public Velociraptor(String id, String name, int age) {
    super(id, name, age, FoodType.MEAT, 8);
  }

  /**
   * Returns the high-pitched, piercing vocalization of a Velociraptor.
   * @return The string "SKREEE! ME VEEEEEE!".
   */
  @Override public String roar() {
    return "SKREEE! ME VEEEEEE!";
  }

  /**
   * Determines if this Velociraptor can share an enclosure with another dinosaur.
   *
   * For safety and social reasons, this implementation only allows coexistence
   * with other carnivores. Any dinosaur with a diet other than FoodType.MEAT
   * is considered incompatible (and likely prey).
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true if the other dinosaur is also a meat-eater; false otherwise.
   */
  @Override public boolean canCoexistWith(Dinosaur otherDinosaur) {
    if (otherDinosaur == this) {
      return true;
    }

    return (otherDinosaur.getFoodType() == FoodType.MEAT);
  }
}
