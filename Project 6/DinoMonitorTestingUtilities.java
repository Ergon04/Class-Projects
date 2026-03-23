import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * A collection of useful methods for testing the Dinosaur Monitoring implementations.
 */
public class DinoMonitorTestingUtilities {

    /**
   * Random instance for tests that randomly generate inputs. We set the random seed so that we
   * still get deterministic results that we can reproduce.
   */
    public static Random randGen = new Random(205);

    /**
   * Create a new ResearcherList with researchers that have the given capabilities.
   *
   * @param capabilities the capabilities of the researchers; one researcher per row of the array.
   * @return a new ResearcherList with all the researchers
   */
    public static ResearcherList makeResearcherList(boolean[][] capabilities) {
        ResearcherList researchers = new ResearcherList();

        for (boolean[] capability : capabilities) {
            Researcher researcher = new Researcher(capability);
            researchers.add(researcher);
        }

        return researchers;
    }

    /**
   * Create a new ResearcherList with researchers that have the given capabilities and salaries.
   *
   * @param capabilities the capabilities of the researchers; one researcher per row of the array.
   * @param salaries     the annual salary for each researcher; the elements of this array should
   *                     correspond with the rows of the capabilities array.
   * @return a new ResearcherList with all the researchers
   */
    public static ResearcherList makeResearcherList(
        boolean[][] capabilities,
        int[] salaries
    ) {
        ResearcherList researchers = new ResearcherList();

        for (int r = 0; r < capabilities.length; ++r) {
            Researcher researcher = new Researcher(capabilities[r], salaries[r]);
            researchers.add(researcher);
        }

        return researchers;
    }

    /**
   * Generate a random set of researchers with monitoring capabilities across the given number
   * of stations.
   *
   * @param numStations    the number of monitoring stations
   * @param numResearchers the number of researchers to generate; the returned list will be this long
   * @param maxSalary      the maximum annual salary for any researcher, or -1 to indicate no salaries
   *
   * @return a list of randomly generated researchers
   */
    public static ResearcherList generateRandomInput(
        int numStations,
        int numResearchers,
        int maxSalary
    ) {
        ResearcherList researchers = new ResearcherList();

        for (int r = 0; r < numResearchers; ++r) {
            boolean[] capability = new boolean[numStations];
            for (int s = 0; s < numStations; s++) {
                // flip a coin to determine if the researcher can monitor station s
                capability[s] = randGen.nextDouble() < 0.50;
            }
            if (maxSalary == -1) {
                researchers.add(new Researcher(capability));
            } else {
                int salary = randGen.nextInt(maxSalary) + 1;
                researchers.add(new Researcher(capability, salary));
            }
        }

        return researchers;
    }

    /**
   * Generate a random set of researchers with monitoring capabilities across the given number
   * of stations.
   *
   * @param numStations    the number of monitoring stations
   * @param numResearchers the number of researchers to generate; the returned list will be this long
   *
   *
   * @return a list of randomly generated researchers
   */
    public static ResearcherList generateRandomInput(
        int numStations,
        int numResearchers
    ) {
        // This only works because we know that -1 is the sentinel value that indicates
        // no salaries...
        return generateRandomInput(numStations, numResearchers, -1);
    }

    /**
   * Compare two lists of researchers. If there are differences, print some helpful output and return
   * false. Otherwise, print nothing and return true.
   *
   * @param expected the list representing the expected result
   * @param actual   the list representing the result from calling the method we are testing
   *
   * @return true if and only if the contents of actual match expected
   */
    public static boolean compareResearcherLists(
        ResearcherList expected,
        ResearcherList actual
    ) {
        ArrayList<ResearcherList> lists = new ArrayList<>(1);
        if (expected != null) {
            lists.add(expected);
        }
        return compareResearcherLists(lists, actual);
    }

    /**
   * Check if actual is in the list of expected solutions. If actual is not in the expected list,
   * print some helpful output and return false. Otherwise, print nothing and return true.
   *
   * @param expectedLists a list of lists representing the expected results. If the list is empty,
   *                      then actual must be null.
   * @param actual        the list representing the result from calling the method we are testing.
   *
   * @return true if and only if the contents of actual match one of the lists in expected
   */
    public static boolean compareResearcherLists(
        ArrayList<ResearcherList> expectedLists,
        ResearcherList actual
    ) {
        // Handle the special case where we want to test the results of an impossible
        // problem (ie, one where there is no solution).
        if (expectedLists.isEmpty()) {
            if (actual == null) {
                return true;
            } else {
                System.out.println("Expected: null");
                System.out.println("Actual:   " + actual);
                return false;
            }
        } else if (actual == null) {
            System.out.println("Expected: Non-null");
            System.out.println("Actual:   null");
            return false;
        }

        // Build some output. If we don't find a match, then we will print it.
        StringBuilder diffOutput = new StringBuilder();

        diffOutput.append("Comparing actual against ")
            .append(expectedLists.size())
            .append(" expected lists.\n");

        // Sort by the researcher ID.
        actual.sort(Comparator.comparing(Researcher::getId));

        // Return true if the actual list matches ANY of the expected lists.
        for (ResearcherList expected : expectedLists) {
            // Sort by the researcher ID.
            expected.sort(Comparator.comparing(Researcher::getId));

            // Compare length.
            if (expected.size() != actual.size()) {
                diffOutput.append("Lists differ in size.\n");
                diffOutput.append("Expected: ").append(expected).append("\n");
                diffOutput.append("Actual:   ").append(actual).append("\n");
                continue;
            }

            boolean matchesExpected = true;

            // Compare element-wise.
            for (int r = 0; r < expected.size(); ++r) {
                if (!expected.get(r).equals(actual.get(r))) {
                    // The lists don't match. End the comparison and move to the next possible
                    // expected list.
                    diffOutput.append("Researchers differ.\n");
                    diffOutput
                        .append("Expected: ")
                        .append(expected.get(r))
                        .append(" in ")
                        .append(expected)
                        .append("\n");
                    diffOutput
                        .append("Actual:   ")
                        .append(actual.get(r))
                        .append(" in ")
                        .append(actual)
                        .append("\n");
                    matchesExpected = false;
                    break;
                }
            }

            if (matchesExpected) {
                // The lists match! Return without printing anything.
                return true;
            }
        }

        // None of the lists matched. Print and return false.
        System.out.println(diffOutput);
        return false;
    }

    /**
   * Returns the sub-list of researchers at the indices indicated by a bit-vector represented by a
   * long value.
   *
   * @param researchers original list of researchers
   * @param bits        long representing the researchers to select
   * @return the sub-list containing all researchers at the selected indices
   */
    public static ResearcherList bitSubset(ResearcherList researchers, long bits) {
        return new ResearcherList(
            IntStream.range(0, researchers.size())
                .filter(i -> (bits & (1L << i)) != 0)
                .mapToObj(researchers::get).toList()
        );
    }

    /**
   * Compute all possible optimal solutions in an iterative manner. This method takes the same
   * parameters with the same meanings as optimalMonitoring, but it computes all possible solutions.
   *
   * @param researchers the set of available researchers to hire from (excluding those already hired)
   * @param hiresLeft   the maximum number of researchers to hire
   * @return a list of all optimal solutions to the monitoring problem
   */
    public static ArrayList<ResearcherList> allOptimalSolutions(
        ResearcherList researchers,
        int hiresLeft
    ) {
        List<ResearcherList> options = LongStream.range(0, 1L << researchers.size())
            .filter(i -> Long.bitCount(i) <= hiresLeft)
            .mapToObj(i -> bitSubset(researchers, i))
            .toList();

        if (options.isEmpty()) {
            return new ArrayList<>();
        } else {
            int maxCoveredStations = options.stream()
                .map(ResearcherList::numCoveredStations)
                .max(Integer::compareTo)
                .get();

            List<ResearcherList> bestChoices = options.stream()
                .filter(r -> r.numCoveredStations() == maxCoveredStations)
                .toList();

            return new ArrayList<>(bestChoices);
        }
    }

    /**
   * Compute all possible min-cost solutions in an iterative manner. This method takes the same
   * parameters with the same meanings as minCostMonitoring, but it computes all optimal solutions.
   *
   * @param researchers the set of available researchers to hire from (excluding those already hired)
   * @param minStations the minimum number of stations we want to have covered total
   * @return a list containing all optimal solutions to the min-cost problem
   */
    public static ArrayList<ResearcherList> allMinCostSolutions(
        ResearcherList researchers,
        int minStations
    ) {
        List<ResearcherList> options = LongStream.range(0, 1L << researchers.size())
            .mapToObj(i -> bitSubset(researchers, i))
            .filter(r -> r.numCoveredStations() >= minStations)
            .toList();

        if (options.isEmpty()) {
            return new ArrayList<>();
        } else {
            int minTotalCost = options.stream()
                .map(ResearcherList::totalAnnualCost)
                .min(Integer::compareTo).get();

            List<ResearcherList> bestChoices = options.stream()
                .filter(r -> r.totalAnnualCost() == minTotalCost)
                .toList();

            return new ArrayList<>(bestChoices);
        }
    }
}
