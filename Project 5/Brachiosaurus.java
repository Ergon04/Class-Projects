/**
 * Represents a Brachiosaurus, a large herbivorous sauropod.
 *
 * Brachiosaurus are characterized by their massive size and gentle temperament.
 * In this system, they are pre-configured with a dietary type of FoodType.PLANTS
 * and a specific preferred area requirement.
 *
 */
public class Brachiosaurus extends Dinosaur {

  /**
   * Constructs a new Brachiosaurus with the specified identity and age.
   * This constructor invokes the superclass constructor with default values
   * specific to this species: a FoodType.PLANTS diet and a
   * preferred area of 12.0 units.
   *
   * @param id   The unique identifier for this individual.
   * @param name The name given to this individual.
   * @param age  The age of the dinosaur in years.
   */
  public Brachiosaurus(String id, String name, int age) {
    super(id, name, age, FoodType.PLANTS, 12);
  }

  /**
   * Returns the characteristic vocalization of a Brachiosaurus.
   * @return The string "UUUUUUU!".
   */
  @Override public String roar() {
    return "UUUUUUU!";
  }

  /**
   * Determines if this Brachiosaurus can share an enclosure with another dinosaur.
   *
   * Being a peaceful herbivore, the Brachiosaurus is universally compatible
   * and can coexist with any other dinosaur species.
   *
   * @param otherDinosaur The dinosaur to check compatibility against.
   * @return true in all cases.
   */
  @Override public boolean canCoexistWith(Dinosaur otherDinosaur) {
    return true;
  }
}
