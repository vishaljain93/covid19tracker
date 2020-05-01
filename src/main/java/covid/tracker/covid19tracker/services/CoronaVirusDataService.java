package covid.tracker.covid19tracker.services;

import covid.tracker.covid19tracker.beans.Config;
import covid.tracker.covid19tracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    public CoronaVirusDataService(IndiaHttpConnectionImpl indiaHttpConnection) {
        this.indiaHttpConnection = indiaHttpConnection;
    }

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    private List<LocationStats> allStats = new ArrayList<>();

    @Autowired
    private IndiaHttpConnectionImpl indiaHttpConnection;

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();

        HttpResponse<String> response = indiaHttpConnection.getResponse(Config.WORLD_DATA_CSV.getConstants());
        StringReader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        setLocationStatas(records, newStats);
    }

    private void setLocationStatas(Iterable<CSVRecord> records, List<LocationStats> newStats) {
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int previousDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPreviousDay(latestCases - previousDayCases);
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }
}