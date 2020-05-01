package covid.tracker.covid19tracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int diffFromPreviousDay;

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
