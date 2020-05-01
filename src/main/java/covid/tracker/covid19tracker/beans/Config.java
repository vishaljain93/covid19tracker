package covid.tracker.covid19tracker.beans;

public enum Config {
    SLACK("https://hooks.slack.com/services/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"),
    WORLD_DATA_CSV("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv"),
    INDIA_DATA_API("https://api.covid19india.org/state_district_wise.json"),
    ACTIVE("active"),
    CONFIRMED("confirmed"),
    DECEASED("deceased"),
    RECOVERED("recovered"),
    TOTAL_ACTIVE_CASES("totalActiveCases"),
    TOTAL_CONFIRMED_CASES("totalConfirmedCases"),
    TOTAL_RECOVERED_CASES("totalRecoveredCases"),
    TOTAL_DECEASED_CASES("totalDeceasedCases"),
    STATES("States"),
    CITIES("Cities"),
    STATE_DATA("StatesData"),
    COVID_19_INDIA("covid19india");

    private String constants;

    Config(String constants) {
        this.constants = constants;
    }

    public String getConstants() {
        return constants;
    }
}
