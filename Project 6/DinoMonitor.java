/*
 * Author: Eric Gonzalez-Montijo
 * Email: gonzlezmonti@wisc.edu
 * Course: Computer Science 300, Spring 2026
 * Assignment: Project 6 - Dino Monitor
 * Citations: No LLM used, Canvas Style Guide, 
 * Collaboration with Gavin Garrison, ArrayList JavaDoc, 
 * Recursion geeksforgeeks.com
 */

/**
 * This class contains methods to solve variants of the dinosaur monitoring coverage problem:
 * given a set of researchers with different monitoring capabilities,
 * determine which researchers to hire to maximize coverage subject to certain constraints.
 */
public class DinoMonitor {
  /**
     * Greedy monitoring approach: iteratively select the researcher that covers
     * the most previously-uncovered monitoring stations.
     *
     * This method works recursively: at each step, identify which remaining researcher
     * would increase the total coverage the most, add them to the hired list, and recurse.
     *
     * @param candidates available researchers not yet considered for hiring
     * @param hired currently hired researchers
     * @param hiresLeft number of additional researchers we can hire
     * @return the selected team of researchers (union of hired and greedily selected)
     */
  public static ResearcherList greedyMonitoring(
        ResearcherList candidates,
        ResearcherList hired,
        int hiresLeft
  ) {
    if (hiresLeft == 0 || candidates.isEmpty()) {
      return hired;
    }

    int maxCoverage = hired.numCoveredStations();
    Researcher best = null;

    for (int i = 0; i < candidates.size(); i++) {
      ResearcherList hypothetical = hired.copyAndAddResearcher(candidates.get(i));
      int hypCoverage = hypothetical.numCoveredStations();

      if (hypCoverage > maxCoverage) {
        best = candidates.get(i);
        maxCoverage = hypCoverage;
      }
    }

    if (best != null) {
      hired = hired.copyAndAddResearcher(best);
      candidates = candidates.copyAndRemoveResearcher(best);
      hiresLeft -= 1;
      return greedyMonitoring(candidates, hired, hiresLeft);
    }

    return hired;

  }

  /**
     * Optimal monitoring approach: exhaustively search all possible teams
     * up to the hire limit to find the team covering the most unique stations.
     *
     * This method works recursively: try adding each remaining researcher to the hired set,
     * recurse, and keep track of the solution with the highest coverage.
     *
     * @param candidates available researchers not yet considered for hiring
     * @param hired currently hired researchers
     * @param hiresLeft number of additional researchers we can hire
     * @return the optimal team of researchers (union of hired and optimally selected)
     */
  public static ResearcherList optimalMonitoring(
        ResearcherList candidates,
        ResearcherList hired,
        int hiresLeft
  ) {
    if (hiresLeft == 0) {
      return hired;
    }

    if (candidates == null || candidates.isEmpty()) {
      return hired;
    }

    Researcher current = candidates.get(0);

    ResearcherList withCurr = hired.copyAndAddResearcher(current);
    ResearcherList withoutCurr = candidates.copyAndRemoveResearcher(current);

    ResearcherList option1 = optimalMonitoring(
        withoutCurr,
        withCurr,
        hiresLeft - 1
    );
    ResearcherList option2 = optimalMonitoring(withoutCurr, hired, hiresLeft);

    if (option1.numCoveredStations() >= option2.numCoveredStations()) {
      return option1;
    }


    return option2;
  }

  /**
     * Cost-aware monitoring: find the least expensive team that covers
     * at least the required number of monitoring stations.
     *
     * This method works recursively: try adding each remaining researcher to the hired set,
     * recurse until minimum coverage is achieved, and keep track of the solution with
     * the minimum total annual salary.
     *
     * @param candidates available researchers not yet considered for hiring
     * @param hired currently hired researchers
     * @param minStations minimum monitoring stations that must be covered
     * @return the lowest-cost team meeting the coverage requirement, or null if impossible
     */
  public static ResearcherList minCostMonitoring(
      ResearcherList candidates,
      ResearcherList hired,
      int minStations
  ) {

    if (minStations == 0) {
      return hired;
    }

    if (candidates == null || candidates.isEmpty()) {
      if (hired.numCoveredStations() >= minStations) {
        return hired;
      }
      return null;
    }

    Researcher current = candidates.get(0);
    ResearcherList candidatesWithoutCurr = candidates.copyAndRemoveResearcher(
          current
    );

    ResearcherList withCurr = minCostMonitoring(
        candidatesWithoutCurr,
        hired.copyAndAddResearcher(current),
        minStations
    );
    ResearcherList withoutCurr = minCostMonitoring(
        candidatesWithoutCurr,
        hired,
        minStations
    );

    if (withCurr != null && withoutCurr != null) {
      if (withCurr.totalAnnualCost() < withoutCurr.totalAnnualCost()) {
        return withCurr;
      } else {
        return withoutCurr;
      }
    }

    if (withCurr != null) {
      return withCurr;
    } else {
      return withoutCurr;
    }

  }
}
