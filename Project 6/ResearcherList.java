import java.util.ArrayList;
import java.util.List;

/**
 * This class extends ArrayList by adding utilities related to the wildlife monitoring problem.
 * Note that you may not need all of these utilities in your implementation.
 */
public class ResearcherList extends ArrayList<Researcher> {
    /**
   * Creates a new empty ResearcherList
   */
    public ResearcherList() {
        super();
    }

    /**
   * Creates a new ResearcherList containing the given ArrayList of researchers. Pass-through for the
   * ArrayList super class constructor.
   *
   * @param researchers list of Researchers for this ResearcherList to contain
   */
    public ResearcherList(List<Researcher> researchers) {
        super(researchers);
    }

    /**
   * Makes a deep copy (not deepest copy) of this ResearcherList while excluding the given researcher.
   *
   * @param r a researcher to exclude from the deep copy.
   *
   * @return a deep copy of this list without the given researcher.
   * @throws IllegalArgumentException if this list does not contain the given researcher.
   */
    public ResearcherList copyAndRemoveResearcher(Researcher r) {
        if (!this.contains(r)) {
            throw new IllegalArgumentException("List does not contain researcher.");
        }
        ResearcherList rl = this.deepCopy();
        rl.remove(r);
        return rl;
    }

    /**
   * Makes a deep copy (not deepest copy) of this ResearcherList while adding the given researcher.
   *
   * @param r a researcher to add to the deep copy.
   *
   * @return a deep copy of this list with the given researcher.
   * @throws IllegalArgumentException if this list already contains the given researcher.
   */
    public ResearcherList copyAndAddResearcher(Researcher r) {
        if (this.contains(r)) {
            throw new IllegalArgumentException("List already contains researcher.");
        }
        ResearcherList wl = this.deepCopy();
        wl.add(r);
        return wl;
    }

    /**
   * For the researchers in this list, this method returns the total number of monitoring stations
   * that have at least one hired researcher who can effectively monitor them.
   *
   * For example, consider the following scenario with three researchers and their capabilities
   * over four monitoring stations. <br>
   * <br>
   *
   * <pre>{@code
   * | Researcher | Station1 | Station2 | Station3 | Station4 | Hired |
   * | Alice      |    -     |    +     |    +     |    -     |  -    |
   * | Bob        |    +     |    +     |    -     |    +     |  +    |
   * | Carol      |    -     |    -     |    -     |    +     |  +    |
   * ------------------------------------------------------------------
   * | Covered    |    +     |    +     |    -     |    +     |  3/4 stations covered
   * }</pre>
   *
   * <br>
   * Of the two researchers we have hired, one can monitor Station1, one can monitor Station2,
   * neither can monitor Station3, and both can monitor Station4. Therefore, in this case the
   * method should return 3, as Stations 1, 2, and 4 have at least one hired researcher capable
   * of monitoring them.
   *
   * @return the total number of monitoring stations with at least one capable hired researcher
   */
    public int numCoveredStations() {
        if (this.size() == 0) 
            return 0;
        
        int stations = 0;
        // For each station...
        for (int s = 0; s < this.get(0).getStationCapability().length; ++s) {
            for (Researcher r : this) {
                if (r.canMonitor(s)) {
                    // If ANY researcher can monitor this station, increment the count
                    stations++;
                    // To stop double-counting
                    break;
                }
            }
        }

        return stations;
    }

    /**
   * Compute the total annual cost of hiring this team of researchers.
   *
   * @return the sum of annual costs for all researchers in this list.
   */
    public int totalAnnualCost() {
        int total = 0;
        for (Researcher r : this) {
            total += r.getAnnualCost();
        }
        return total;
    }

    /**
   * Returns a human-readable view of the researchers in this list.
   *
   * @return a helpful debugging view of the researchers in this list.
   */
    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        for (Researcher r : this) {
            s.append(r).append(" ; ");
        }
        if (this.isEmpty()) {
            s.append("<empty team> ; ");
        }

        s.append("(stations: ").append(numCoveredStations());

        if (totalAnnualCost() > 0) {
            s.append(", cost: $").append(totalAnnualCost()).append("K)");
        } else {
            s.append(")");
        }

        return s.toString();
    }

    /**
   * Creates a deep copy (not deepest copy) of this list.
   *
   * @return a deep copy of this list.
   */
    public ResearcherList deepCopy() {
        return new ResearcherList(this);
    }
}
