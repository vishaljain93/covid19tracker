package covid.tracker.covid19tracker.beans;

import covid.tracker.covid19tracker.constants.Constants;
import covid.tracker.covid19tracker.services.IndiaCoronaVirusTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StateData {

    @Autowired
    private IndiaCoronaVirusTrackerService indiaCoronaVirusTrackerService;

    private static final String BOLD = "*";
    private static final String BLOCK = "```";
    private static final String CODE = "`";
    private static final String NEW_LINE = "\\n";
    private static final String QUOTES = "\"";
    private static final String PIPE = "|";
    private static final String SPACE = " ";
    private static final String TEXT = "{\"text\": %s }";

    public String getStateData() {
        return formatData();
    }

    private String getTotalStateCount() {
        return QUOTES + BOLD + "Total Confirmed Cases:" + BOLD + SPACE + CODE + IndiaCoronaVirusTrackerService.getTotalConfirmedCases() + CODE + NEW_LINE +
                BOLD + "Total Active Cases:" + BOLD + SPACE + CODE + IndiaCoronaVirusTrackerService.getTotalActiveCases() + CODE + NEW_LINE +
                BOLD + "Total Recovered Cases:" + BOLD + SPACE + CODE + IndiaCoronaVirusTrackerService.getTotalRecoveredCases() + CODE + NEW_LINE +
                BOLD + "Total Deceased Cases:" + BOLD + SPACE + CODE + IndiaCoronaVirusTrackerService.getTotalDeceasedCases() + CODE + NEW_LINE;
    }

    private String formatData() {
        Map<String, Map<String, Integer>> stateData = indiaCoronaVirusTrackerService.getSTATES();
        StringBuilder totalStateCount = new StringBuilder(getTotalStateCount());

        totalStateCount.append("|          State Name                        | Count  " + NEW_LINE)
                       .append("|--------------------------------------|--------|" + NEW_LINE);


        for (String stateName : stateData.keySet()) {
            Map<String, Integer> map = stateData.get(stateName);
            StringBuilder spaces = new StringBuilder(PIPE + SPACE + stateName);
            for (int i = 0; i < 30 - stateName.length(); i++) {
                spaces.append(" ");
            }
            totalStateCount.append(spaces + PIPE + SPACE + map.get(Constants.CONFIRMED) + SPACE + PIPE + NEW_LINE);
        }
        return String.format(TEXT, totalStateCount.append(QUOTES));
    }
}
