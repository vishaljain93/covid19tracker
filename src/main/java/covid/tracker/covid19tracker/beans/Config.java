package covid.tracker.covid19tracker.beans;

public enum Config {
    SLACK("https://hooks.slack.com/services/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"),
    WORLD_DATA_CSV("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv"),
    INDIA_DATA_API("https://api.covid19india.org/state_district_wise.json");
    private String url;

    Config(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
