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
 * Test class for DinoPark system functionality.
 * Tests DinoPark, Habitat, and LabTour classes with comprehensive coverage
 * including edge cases, exception handling and normal cases.
 */
public class DinoParkTester {

  /**
    * Calls the testing methods and prints out the results.
    *
    * @param args unused
    */
  public static void main(String[] args) {
    System.out.println("=== DINOPARK TESTING SUITE ===\n");

    boolean allPassed = allTests();

    System.out.println("\n=== FINAL TEST RESULTS ===");
    if (allPassed) {
      System.out.println("ALL TESTS PASSED!");
    } else {
      System.out.println("Some tests failed. Review output above.");
    }
  }

  /**
    * Calls all the individual testing methods.
    *
    * @return true if all tests passed, false if any test failed.
    */
  public static boolean allTests() {
    boolean allPassed = true;
    allPassed &= testLabTourSafetyChecks();
    allPassed &= testVelociraptorCoexist();
    allPassed &= testTRexCoexist();
    allPassed &= testTriceratopsCoexist();
    allPassed &= testSpinosaurusCoexist();
    allPassed &= testHabitatAddValidation();
    allPassed &= testHabitatRemoveValidation();
    allPassed &= testHabitatHumanSafetyChecks();
    allPassed &= testHabitatDinoSafetyChecks();
    allPassed &= testCompleteWorkflow();
    return allPassed;
  }

  public static boolean testLabTourSafetyChecks() {
    System.out.println("Testing LabTour safety checks...");
    boolean allTestsPassed = true;
    LabTour labTourUnderCapacity = new LabTour("Mr. DNA", 4);
    labTourUnderCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labTourUnderCapacity.addExperiment(new Experiment("Genome splicing", 2));

    int dinoViolationsUnderCapacity = labTourUnderCapacity.countDinoSafetyViolations();
    int humanViolationsUnderCapacity = labTourUnderCapacity.countHumanSafetyViolations();

    if (dinoViolationsUnderCapacity != 0) {
      System.out.println("LabTour countDinoSafetyViolations incorrect for case: "
              + "Total people under capacity.\n"
              + "Expected: 0\n"
              + "Actual: " + dinoViolationsUnderCapacity);
      allTestsPassed = false;
    }
    if (humanViolationsUnderCapacity != 0) {
      System.out.println("LabTour countHumanSafetyViolations incorrect for case: "
              + "Total people under capacity.\n"
              + "Expected: 0\n"
              + "Actual: " + humanViolationsUnderCapacity);
      allTestsPassed = false;
    }

    LabTour labOverCapacity = new LabTour("Mr. DNA", 11);
    labOverCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labOverCapacity.addExperiment(new Experiment("Genome splicing", 3));

    int dinoViolationsOverCapacity = labOverCapacity.countDinoSafetyViolations();
    int humanViolationsOverCapacity = labOverCapacity.countHumanSafetyViolations();

    if (dinoViolationsOverCapacity != 0) {
      System.out.println("LabTour countDinoSafeteyViolations incorrect for case: "
            + "Total people over capacity\n"
            + "Expected: 0\n"
            + "Actual; " + dinoViolationsOverCapacity);
      allTestsPassed = false;
    }

    if (humanViolationsOverCapacity != 1) {
      System.out.println("LabTour countHumanSafeteyViolations incorrect for case: "
            + "Total people over capacity\n"
            + "Expected: 1\n"
            + "Actual; " + humanViolationsOverCapacity);
      allTestsPassed = false;
    }

    LabTour labCapacity = new LabTour("Mr. DNA", 4);
    labCapacity.addExperiment(new Experiment("Hatching raptors", 3));
    labCapacity.addExperiment(new Experiment("Genome splicing", 3));

    int dinoViolationsCapacity = labCapacity.countDinoSafetyViolations();
    int humanViolationsCapacity = labCapacity.countHumanSafetyViolations();
    
    if (dinoViolationsOverCapacity != 0) {
      System.out.println("LabTour countDinoSafeteyViolations incorrect for case: "
            + "Total people capacity\n"
            + "Expected: 0\n"
            + "Actual; " + dinoViolationsCapacity);
      allTestsPassed = false;
    }
    
    if (humanViolationsCapacity != 0) {
      System.out.println("LabTour countHumanSafeteyViolations incorrect for case: "
            + "Total people capacity\n"
            + "Expected: 0\n"
            + "Actual; " + humanViolationsCapacity);
      allTestsPassed = false;
    }

    
    if (allTestsPassed) {
      System.out.println("PASS"); 
    } else {
      System.out.println("FAIL"); 
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testVelociraptorCoexist() {
    System.out.println("Testing Velociraptor canCoexistWith...");
    boolean allTestsPassed = true;

    Velociraptor velociraptor = new Velociraptor("raptor1", "Alan", 8);

    Velociraptor meatDinosaur = new Velociraptor("raptor2", "Radia", 12);
    boolean velociraptorCoexistWithMeat = velociraptor.canCoexistWith(meatDinosaur);
    if (!velociraptorCoexistWithMeat) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
              + "Other Dinosaur has food type MEAT.\n"
              + "Expected: true\n"
              + "Actual: false");
      allTestsPassed = false;
    }

    Brachiosaurus plantsDinosaur = new Brachiosaurus("longneck1", "Littlefoot", 4);
    boolean velociraptorCoexistWithPlants = velociraptor.canCoexistWith(plantsDinosaur);
    if (velociraptorCoexistWithPlants) {
      System.out.println("Velociraptor canCoexistWith incorrect for case: "
              + "Other Dinosaur has food type PLANTS.\n"
              + "Expected: false\n"
              + "Actual: true");
      allTestsPassed = false;
    }

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testTRexCoexist() {
    System.out.println("Testing TRex canCoexistWith...");
    boolean allTestsPassed = true;

    TRex trex = new TRex("trex1", "Sue", 24);
    Velociraptor velociraptor = new Velociraptor("raptor1", "Alan", 8);

    boolean canTRexCoexist = trex.canCoexistWith(velociraptor);

    if (canTRexCoexist) {
      System.out.println("TRex canCoexistWith incorrect for case: "
                + "No dinosaur should coexist with TRex\n"
                + "Expected: false\n"
                + "Actual: true");
      allTestsPassed = false;
    }
    
    boolean trexCoexistWithItself = trex.canCoexistWith(trex);
    if (!trexCoexistWithItself) {
      System.out.println("TRex canCoexistWith incorrect for case: "
                + "Can coexist with itself.\n"
                + "Expected: true\n"
                + "Actual: false");
      allTestsPassed = false;
    }
    

    if (allTestsPassed) {
      System.out.println("PASS");
    }        
    System.out.println();
    return allTestsPassed;
  }

  public static boolean testTriceratopsCoexist() {
    System.out.println("Testing Triceratops canCoexistWith...");
    boolean allTestsPassed = true;

    Triceratops tri = new Triceratops("tri1", "Sal", 24);
    TRex trex = new TRex("trex1", "Sue", 24);

    boolean canTriCoexist = tri.canCoexistWith(trex);

    if (canTriCoexist) {
      System.out.println("Triceratops canCoexistWith incorrect for case: "
                + "Triceratops can not coexist with meat eaters.\n"
                + "Expected: false\n"
                + "Actual: true");
      allTestsPassed = false;
    }

    canTriCoexist = tri.canCoexistWith(tri);

    if (!canTriCoexist) {
      System.out.println("Triceratops canCoexistWith incorrect for case: "
                + "Triceratops can  coexist with plant eaters.\n"
                + "Expected: true\n"
                + "Actual: false");
      allTestsPassed = false;
    }

    if (allTestsPassed) {
      System.out.println("PASS");
    }   

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testSpinosaurusCoexist() {
    System.out.println("Testing Spinosaurus canCoexistWith...");
    boolean allTestsPassed = true;

    Spinosaurus spino = new Spinosaurus("spino1", "Eric", 2);
    Spinosaurus spino2 = new Spinosaurus("spino2", "Aric", 3);

    boolean canSpinoCoexist = spino.canCoexistWith(spino);

    if (!canSpinoCoexist) {
      System.out.println("Spinosaurus canCoexistWith incorrect for case: "
                + "Spinosaurus can coexist with itself.\n"
                + "Expected: true\n"
                + "Actual: false");
      allTestsPassed = false;
    }
  
    canSpinoCoexist = spino.canCoexistWith(spino2);

    if (!canSpinoCoexist) {
      System.out.println("Spinosaurus canCoexistWith incorrect for case: "
                + "Spinosaurus can coexist with other Spinosaurus object.\n"
                + "Expected: true\n"
                + "Actual: false");
      allTestsPassed = false;
    }

    Brachiosaurus brach = new Brachiosaurus("brach1", "Erick", 20);

    canSpinoCoexist = spino.canCoexistWith(brach);

    if (!canSpinoCoexist) {
      System.out.println("Spinosaurus canCoexistWith incorrect for case: "
                + "Spinosaurus can coexist with those who can coexist with Spinosaurus.\n"
                + "Expected: true\n"
                + "Actual: false");
      allTestsPassed = false;
    }

    TRex trex = new TRex("trex1", "Sue", 24);

    canSpinoCoexist = spino.canCoexistWith(trex);

    if (canSpinoCoexist) {
      System.out.println("Spinosaurus canCoexistWith incorrect for case: "
                + "Triceratops can not coexist with TRex.\n"
                + "Expected: false\n"
                + "Actual: true");
      allTestsPassed = false;
    }

    if (allTestsPassed) {
      System.out.println("PASS");
    }   

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatAddValidation() {
    System.out.println("Testing Habitat addDinosaur validation...");
    boolean allTestsPassed = true;

    Habitat habitat = new Habitat("habitat1", 1, 1);

    try {
      habitat.addDinosaur(new Velociraptor("raptor1", "Alan", 2));

      if (habitat.getDinosaurs().size() != 1) {
        allTestsPassed = false;
      } else if (!habitat.getDinosaurs().get(0).getId().equals("raptor1")) {
        allTestsPassed = false;
      }
    } catch (Exception e) {
      System.out.println("Habitat addDinosaur with a valid dinosaur threw exception: "
              + e.getMessage());
      allTestsPassed = false;
    }

    try {
      habitat.addDinosaur(null);

      if (habitat.getDinosaurs().size() != 1) {
        allTestsPassed = false;
        System.out.println("addDinosaur() did not throw exception at null dino");
      }
    } catch (IllegalArgumentException e) {
      allTestsPassed = true;
    }

    if (allTestsPassed) {
      System.out.println("PASS");
    }   

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatRemoveValidation() {
    System.out.println("Testing Habitat removeDinosaur validation...");
    boolean allTestsPassed = true;

    Habitat habitat = new Habitat("habitat1", 1, 1);
    habitat.addDinosaur(new Velociraptor("raptor1", "Alan", 2));

    try {
      habitat.removeDinosaur("raptor1");
    } catch (Exception e) {
      System.out.println("Valid dino removal threw exception"
                  + e.getMessage());
      allTestsPassed = false;
    }
    
    try {
      habitat.removeDinosaur(null);
      allTestsPassed = false;
      System.out.println("No exception thrown for remove null dino");
    } catch (IllegalArgumentException e) {
      // allTestsPassed = true;
    } catch (Exception e) {
      System.out.println("Wrong exception at remove null dino");
      allTestsPassed = false;
    }

    try {
      habitat.removeDinosaur("ericeric");
      allTestsPassed = false;
      System.out.println("No exception thrown for remove non-existent dino");
    } catch (DinoNotFoundException e) {
      // allTestsPassed = true;
    }

    if (allTestsPassed) {
      System.out.println("PASS");
    }   

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatHumanSafetyChecks() {
    System.out.println("Testing Habitat human safety checks...");
    boolean allTestsPassed = true;

    Habitat habitatEnoughRangers = new Habitat("habitat1", 4, 3);
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));
    habitatEnoughRangers.addDinosaur(new Velociraptor("raptor3", "Ian", 2));

    int violationsEnoughRangers = habitatEnoughRangers.countHumanSafetyViolations();

    if (violationsEnoughRangers != 0) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
                + "Enough rangers for area and dinosaurs.\n"
                + "Expected: 0\n"
                + "Actual: " + violationsEnoughRangers);
      allTestsPassed = false;
    }

    Habitat habitatAreaTooLarge = new Habitat("habitat2", 40, 3);

    int violationsAreaTooLarge = habitatAreaTooLarge.countHumanSafetyViolations();

    if (violationsAreaTooLarge != 1) {
      System.out.println("Habitat countHumanSafetyViolations incorrect for case: "
                + "Not too many dinosaurs but area too large.\n"
                + "Expected: 1\n"
                + "Actual: " + violationsAreaTooLarge);
      allTestsPassed = false;
    }

    Habitat habitatTooMany = new Habitat("habitat3", 4, 1);
    habitatTooMany.addDinosaur(new Velociraptor("raptor1", "Eric", 2));
    habitatTooMany.addDinosaur(new Velociraptor("raptor2", "Kianna", 2));
    habitatTooMany.addDinosaur(new Velociraptor("raptor3", "Nora", 2));

    int tooMany = habitatTooMany.countHumanSafetyViolations();

    if (tooMany != 1) {
      System.out.println("Expected 1 violation for too many dinos");
      allTestsPassed = false;
    }

    Habitat habitatTwoViolations = new Habitat("habitat4", 40, 1);
    habitatTwoViolations.addDinosaur(new Velociraptor("raptor1", "Eric", 2));
    habitatTwoViolations.addDinosaur(new Velociraptor("raptor2", "Kianna", 2));
    habitatTwoViolations.addDinosaur(new Velociraptor("raptor3", "Nora", 2));

    int twoViolations = habitatTwoViolations.countHumanSafetyViolations();

    if (twoViolations != 2) {
      System.out.println("Expected 2 violation for too many dinos/" 
                + "area too large");
      allTestsPassed = false;
    }

    if (allTestsPassed) {
      System.out.println("PASS");
    }   

    System.out.println();
    return allTestsPassed;
  }

  public static boolean testHabitatDinoSafetyChecks() {
    System.out.println("Testing Habitat Dinosaur safety checks...");
    boolean allTestsPassed = true;

    Habitat habitatEnoughAreaAndCoexist = new Habitat("habitat1", 16, 1);
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatEnoughAreaAndCoexist.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));

    int dinoSafetyEnoughAreaAndCoexist = habitatEnoughAreaAndCoexist.countDinoSafetyViolations();

    if (dinoSafetyEnoughAreaAndCoexist != 0) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
                + "Enough area and all dinos can coexist.\n"
                + "Expected: 0\n"
                + "Actual: " + dinoSafetyEnoughAreaAndCoexist);
      allTestsPassed = false;
    }

    Habitat habitatNotEnoughArea = new Habitat("habitat1", 2, 1);
    habitatNotEnoughArea.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatNotEnoughArea.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));

    int notEnoughArea = habitatNotEnoughArea.countDinoSafetyViolations();

    if (notEnoughArea != 1) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
                + "Not enough area but all dinos can coexist.\n"
                + "Expected: 1\n"
                + "Actual: " + notEnoughArea);
      allTestsPassed = false;
    }

    Habitat habitatCanNotCoexist = new Habitat("habitat1", 2, 1);
    habitatCanNotCoexist.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
    habitatCanNotCoexist.addDinosaur(new Velociraptor("raptor2", "Ellie", 2));

    int canNotCoexist = habitatCanNotCoexist.countDinoSafetyViolations();

    if (canNotCoexist != 1) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
                + "Enough area but dinos can not coexist.\n"
                + "Expected: 1\n"
                + "Actual: " + canNotCoexist);
      allTestsPassed = false;
    }


    Habitat habitatCanNotWork = new Habitat("habitat1", 1, 1);
    habitatCanNotWork.addDinosaur(new Triceratops("triceratops1", "Alan", 10));
    habitatCanNotWork.addDinosaur(new TRex("trex1", "Ellie", 10));

    int canNotWork = habitatCanNotWork.countDinoSafetyViolations();

    if (canNotWork != 2) {
      System.out.println("Habitat countDinoSafetyViolations incorrect for case: "
                + "Not enough area, dinos can not coexist.\n"
                + "Expected: 2\n"
                + "Actual: " + canNotWork);
      allTestsPassed = false;
    }

    if (allTestsPassed) {
      System.out.println("PASS");
    }   


    System.out.println();
    return allTestsPassed;
  }

  public static boolean testCompleteWorkflow() {
    System.out.println("Testing complete DinoPark workflow integration...");
    boolean allTestsPassed = true;

    try {
      DinoPark dinoPark = new DinoPark();

      LabTour lab1 = new LabTour("EricLab", 2);
      lab1.addExperiment(new Experiment("Hatching raptors", 5));
      lab1.addExperiment(new Experiment("Making slime", 4));


      Habitat habitat1 = new Habitat("Habitat1", 4, 3);
      Habitat habitat2 = new Habitat("Habitat2", 3, 4);

      habitat1.addDinosaur(new Velociraptor("raptor1", "Alan", 2));
      habitat1.addDinosaur(new Spinosaurus("spinosaurus1", "Spain", 2));

      habitat2.addDinosaur(new Triceratops("triceratops1", "Erica", 2));
      habitat2.addDinosaur(new Brachiosaurus("brach1", "Poop", 2));

      dinoPark.addAttraction(lab1);
      dinoPark.addAttraction(habitat1);
      dinoPark.addAttraction(habitat2);

      System.out.println(dinoPark.generateSafetyReport());
      if (dinoPark.generateSafetyReport() == null || 
                dinoPark.generateSafetyReport().trim().isEmpty()) {
        System.out.println("Report is empty");
        allTestsPassed = false;
      }
    } catch (Exception e) {
      System.out.println("Complete workflow threw exception: "
                + e.getMessage());
      allTestsPassed = false;
    }

    

    if (allTestsPassed) {
      System.out.println("PASS");
    }

    return allTestsPassed;
  }
}
