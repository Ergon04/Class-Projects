import java.util.Arrays;

/**
 * This class stores information related to a wildlife researcher.
 */
public class Researcher {
    /**
   * Stores the next available researcher ID.
   */
    private static int nextResearcherId = 0;

    /**
   * The unique ID of this researcher.
   */
    private final int researcherId;

    /**
   * The capability of this researcher at each monitoring station.
   * true indicates this researcher can effectively monitor that station;
   * false indicates they cannot.
   */
    private final boolean[] stationCapability;

    /**
   * The annual cost to employ this researcher (in thousands of dollars),
   * or -1 if cost is not being considered.
   */
    private final int annualCost;

    /**
   * Create a new researcher with the given monitoring capabilities
   * and no cost tracking. This is useful for the case where we want to ignore
   * costs and budgets, and compare all researchers based solely on monitoring
   * station coverage.
   *
   * @param stationCapability a boolean array with one boolean for each monitoring station.
   *                          The boolean should be false if the researcher CANNOT effectively
   *                          monitor that station and true if they can.
   */
    public Researcher(boolean[] stationCapability) {
        this.researcherId = nextResearcherId++;
        this.stationCapability = stationCapability;
        this.annualCost = -1;
    }

    /**
   * Create a new researcher with the given monitoring capabilities and annual cost.
   *
   * @param stationCapability a boolean array with one boolean for each monitoring station.
   *                          The boolean should be false if the researcher CANNOT effectively
   *                          monitor that station and true if they can.
   * @param annualCost        the annual salary/cost to employ this researcher
   */
    public Researcher(boolean[] stationCapability, int annualCost) {
        this.researcherId = nextResearcherId++;
        this.stationCapability = stationCapability;
        this.annualCost = annualCost;
    }

    /**
   * @return the researcher's unique ID.
   */
    public int getId() {
        return researcherId;
    }

    /**
   * @return the monitoring capability of this researcher across all stations.
   */
    public boolean[] getStationCapability() {
        return stationCapability;
    }

    /**
   * @param station the monitoring station index to check; index starts at 0.
   * @return true if this researcher can effectively monitor the given station.
   */
    public boolean canMonitor(int station) {
        return stationCapability[station];
    }

    /**
   * @return the annual cost to employ this researcher.
   */
    public int getAnnualCost() {
        return annualCost;
    }

    /**
   * Given a set of stations that need monitoring, return the set of remaining stations
   * after hiring this researcher.
   *
   * For example, if stationsNeeded is {true, false, true, true}
   * and this researcher's capability is {true, true, false, true},
   * returns the array {false, false, true, false}.
   *
   * @param stationsNeeded a set of stations that need monitoring coverage.
   *
   * @return stationsNeeded minus any stations covered by this researcher.
   */
    public boolean[] hire(boolean[] stationsNeeded) {
        boolean[] newStationsNeeded = Arrays.copyOf(
            stationsNeeded,
            stationsNeeded.length
        );
        for (int s = 0; s < newStationsNeeded.length; ++s) {
            if (stationCapability[s]) {
                newStationsNeeded[s] = false;
            }
        }
        return newStationsNeeded;
    }

    /**
   * @param obj another Researcher to compare against this one
   *
   * @return true if obj is a Researcher with the same unique id
   */
    @Override public boolean equals(Object obj) {
        if (obj instanceof Researcher other) {
            return this.researcherId == other.researcherId;
        } else {
            return false;
        }
    }

    /**
   * Prints a debugging view of this researcher. The output will be either
   *
   * <p>
   * [id]CAPABILITY
   * <p>
   *
   * OR
   *
   * <p>
   * [id]CAPABILITY/COST
   * <p>
   *
   * where CAPABILITY is a list of '#' and '.', one for each monitoring station,
   * with '#' indicating the researcher can monitor that station and '.' indicating
   * they cannot. COST is the researcher's annual cost in thousands of dollars.
   *
   * @return a debugging string representing this researcher's info.
   */
    @Override public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[").append(researcherId).append("]");

        for (boolean b : stationCapability) {
            s.append(b ? "#" : ".");
        }

        if (annualCost != -1) {
            s.append("/").append(annualCost);
        }
        return s.toString();
    }
}
