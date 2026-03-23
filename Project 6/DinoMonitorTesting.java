/*
 * Author: Eric Gonzalez-Montijo
 * Email: gonzlezmonti@wisc.edu
 * Course: Computer Science 300, Spring 2026
 * Assignment: Project 6 - Dino Monitor
 * Citations: No LLM used, Canvas Style Guide, 
 * Collaboration with Gavin Garrison, ArrayList JavaDoc, 
 * Recursion geeksforgeeks.com
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * Test harness for the Dinosaur Monitoring implementation.
 */
public class DinoMonitorTesting {

  /**
   * Main method to run this tester class.
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.print("Test testGreedyMonitoring: ");
    boolean greedyPass = testGreedyMonitoring();
    System.out.println(
        greedyPass
        ? "Passed"
        : "Failed"
    );

    System.out.print("\n\nTest testOptimalMonitoring: ");
    boolean optPass = testOptimalMonitoring();
    System.out.println(
        optPass
        ? "Passed"
        : "Failed"
    );

    System.out.print("\n\nTest testMinCostMonitoring: ");
    boolean minCostPass = testMinCostMonitoring();
    System.out.println(
        minCostPass
        ? "Passed"
        : "Failed"
    );

    boolean allPass = greedyPass && optPass && minCostPass;

    System.out.println("-----------------------------------------------");
    System.out.println("Overall: " + (
        allPass
        ? "Pass"
        : "Failed!"
      ));
  }


  // //////////////////////////// GREEDY MONITORING TESTS
  // /////////////////////////////////
  public static boolean testGreedyMonitoring() {
    return greedyMonitoringBaseTest() && greedyMonitoringRecursiveTest();
  }

  public static boolean greedyMonitoringBaseTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(
      new boolean[][]{
        {
          true,
          false,
          false,
          false
        }, { // Researcher 0: covers station 0
          false,
          true,
          true,
          true
        }, { // Researcher 1: covers stations 1,2,3
          true,
          false,
          true,
          false
        }, { // Researcher 2: covers stations 0,2
          true,
          true,
          false,
          false
        }
      },
          // Researcher 3: covers stations 0,1
          new int[]{20, 25, 30, 15}
    ); // Researcher salaries: $20k, $25k, $30k, $15k

    ResearcherList expectedSolution = new ResearcherList();

    ResearcherList actualSolution = DinoMonitor.greedyMonitoring(
        candidates,
      new ResearcherList(),
        0
    );

    return DinoMonitorTestingUtilities.compareResearcherLists(
      expectedSolution,
      actualSolution
    );
  }

  public static boolean greedyMonitoringRecursiveTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(
      new boolean[][]{
        {
          true,
          false,
          false,
          false
        }, {
          false,
          true,
          true,
          true
        }, {
          true,
          false,
          true,
          false
        }, {
          true,
          true,
          false,
          false
        }
      },
      new int[]{20, 25, 30, 15}
    );

    ResearcherList expectedSolution = new ResearcherList();

    expectedSolution.add(candidates.get(0));
    expectedSolution.add(candidates.get(1));


    ResearcherList actualSolution = DinoMonitor.greedyMonitoring(
        candidates,
      new ResearcherList(),
        2
    );

    return DinoMonitorTestingUtilities.compareResearcherLists(
      expectedSolution,
      actualSolution
    );
  }

  // //////////////////////////// OPTIMAL MONITORING TESTS
  // ////////////////////////////////
  public static boolean testOptimalMonitoring() {
    return optimalMonitoringBaseTest() && 
        optimalMonitoringRecursiveTest() && optimalMonitoringFuzzTest();
  }

  public static boolean optimalMonitoringBaseTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(
      new boolean[][]{
        {
          true,
          false,
          false,
          false
        }, {
          false,
          true,
          true,
          true
        }, {
          true,
          false,
          true,
          false
        }, {
          true,
          true,
          false,
          false
        }
      },
      new int[]{20, 25, 30, 15}
    );

    ResearcherList expectedSolution = new ResearcherList();

    ResearcherList actualSolution = DinoMonitor.optimalMonitoring(
        candidates,
      new ResearcherList(),
        0
    );

    return DinoMonitorTestingUtilities.compareResearcherLists(
      expectedSolution,
      actualSolution
    );
  }

  public static boolean optimalMonitoringRecursiveTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(
      new boolean[][]{
        {
          false,
          true,
          true,
          true
        }, { // 1 2 3
          false,
          false,
          true,
          false
        }, { //  2
          true,
          false,
          false,
          false
        }, { // 0
          true,
          true,
          false,
          false
        }
      },
          // 0 1
          new int[]{20, 25, 30, 15}
    );

    ResearcherList expectedSolution = new ResearcherList();
    expectedSolution.add(candidates.get(0));
    expectedSolution.add(candidates.get(2));

    ResearcherList actualSolution = DinoMonitor.optimalMonitoring(
        candidates,
      new ResearcherList(),
        2
    );

    return DinoMonitorTestingUtilities.compareResearcherLists(
      expectedSolution,
      actualSolution
    );
  }

  public static boolean optimalMonitoringFuzzTest() {
    Random rnd = new Random(12345);

    for (int i = 0; i <= 150; i++) {
      int numStations = rnd.nextInt(5) + 1;
      int numResearchers = rnd.nextInt(10) + 1;
      int hiresLeft = rnd.nextInt(numResearchers);

      ResearcherList candidates = DinoMonitorTestingUtilities.generateRandomInput(
          numStations,
          numResearchers
      );

      ResearcherList actual = DinoMonitor.optimalMonitoring(
          candidates,
        new ResearcherList(),
          hiresLeft
      );

      ArrayList<ResearcherList> expected = DinoMonitorTestingUtilities.allOptimalSolutions(
          candidates,
          hiresLeft
      );

      if (!DinoMonitorTestingUtilities.compareResearcherLists(expected, actual)) {
        return false;
      }
    }

    return true;

  }

  // //////////////////////////// MIN COST MONITORING TESTS
  // ////////////////////////////////
  public static boolean testMinCostMonitoring() {
    return minCostMonitoringBaseTest() && 
        minCostMonitoringRecursiveTest() && minCostMonitoringFuzzTest();
  }

  public static boolean minCostMonitoringBaseTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(
      new boolean[][]{
        {
          true,
          false,
          false,
          false
        }, {
          false,
          true,
          true,
          true
        }, {
          true,
          false,
          true,
          false
        }, {
          true,
          true,
          false,
          false
        }
      },
      new int[]{20, 25, 30, 15}
    );

    ResearcherList expectedSolution = new ResearcherList();

    ResearcherList actualSolution = DinoMonitor.minCostMonitoring(
        candidates,
      new ResearcherList(),
        0
    );

    return DinoMonitorTestingUtilities.compareResearcherLists(
      expectedSolution,
      actualSolution
    );
  }

  public static boolean minCostMonitoringRecursiveTest() {
    ResearcherList candidates = DinoMonitorTestingUtilities.makeResearcherList(
      new boolean[][]{
        {
          false,
          true,
          true,
          true
        }, { // 1 2 3
          false,
          false,
          true,
          false
        }, { // 0 2
          true,
          false,
          false,
          false
        }, { // 0
          true,
          true,
          false,
          false
        }
      },
          // 0 1
          new int[]{20, 25, 30, 1}
    );

    ResearcherList expectedSolution = new ResearcherList();
    expectedSolution.add(candidates.get(0));
    expectedSolution.add(candidates.get(3));


    ResearcherList actualSolution = DinoMonitor.minCostMonitoring(
        candidates,
      new ResearcherList(),
        4
    );

    return DinoMonitorTestingUtilities.compareResearcherLists(
        expectedSolution,
        actualSolution
    );
  }

  public static boolean minCostMonitoringFuzzTest() {
    Random rnd = new Random(12345);

    for (int i = 0; i <= 150; i++) {
      int numStations = rnd.nextInt(5) + 1;
      int numResearchers = rnd.nextInt(10) + 1;
      int hiresLeft = rnd.nextInt(numResearchers);
      int salary = rnd.nextInt(30) + 1;

      ResearcherList candidates = DinoMonitorTestingUtilities.generateRandomInput(
          numStations,
          numResearchers,
          salary
      );

      ResearcherList actual = DinoMonitor.minCostMonitoring(
          candidates,
        new ResearcherList(),
          hiresLeft
      );

      ArrayList<ResearcherList> expected = DinoMonitorTestingUtilities.allMinCostSolutions(
          candidates,
          hiresLeft
      );

      if (!DinoMonitorTestingUtilities.compareResearcherLists(expected, actual)) {
        return false;
      }
    }

    return true;
  }
}
