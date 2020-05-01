package covid.tracker.covid19tracker.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import covid.tracker.covid19tracker.beans.Config;
import covid.tracker.covid19tracker.model.Cities;
import covid.tracker.covid19tracker.model.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service
public class IndiaCoronaVirusTrackerService {

    private Map<String, Map<String, Integer>> STATES;
    private Map<String, Map<String, Integer>> CITIES;

    private static int TOTAL_ACTIVE_CASES;
    private static int TOTAL_CONFIRMED_CASES;
    private static int TOTAL_RECOVERED_CASES;
    private static int TOTAL_DECEASED_CASES;
    private static final int LENGTH = 4;

    @Autowired
    IndiaHttpConnectionImpl indiaHttpConnection;

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCoronaVirusData() throws IOException, InterruptedException {
        Map<String, Map<String, Integer>> newStates = new TreeMap<>();
        Map<String, Map<String, Integer>> newCities = new TreeMap<>();

        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<String> response = indiaHttpConnection.getResponse(Config.INDIA_DATA_API.getConstants());
        Map<String, States> dataMap = mapper.readValue(response.body(), new TypeReference<TreeMap<String, States>>() {
        });

        setData(dataMap, newStates, newCities);
    }

    private void setData(Map<String, States> dataMap, Map<String, Map<String, Integer>> newStates, Map<String, Map<String, Integer>> newCities) {

        for (String stateName : dataMap.keySet()) {
            int[] stateCaseCount = new int[LENGTH];
            Map<String, Integer> newStateCasesMap = new TreeMap<>();
            Map<String, Cities> citiesMap = dataMap.get(stateName).getCities();
            for (String cityName : citiesMap.keySet()) {
                Map<String, Integer> newCityCasesMap = new TreeMap<>();
                Cities city = citiesMap.get(cityName);
                populateNewCitiesMap(city, newCityCasesMap);
                newCities.put(cityName, newCityCasesMap);
                populateStateCaseCount(stateCaseCount, city);
            }

            populateCasesCount(stateCaseCount);
            populateNewStatesMap(newStateCasesMap, stateCaseCount);
            newStates.put(stateName, newStateCasesMap);
        }

        this.STATES = newStates;
        this.CITIES = newCities;
    }

    private void populateStateCaseCount(int[] stateCaseCount, Cities city) {
        stateCaseCount[0] += city.getActive();
        stateCaseCount[1] += city.getConfirmed();
        stateCaseCount[2] += city.getDeceased();
        stateCaseCount[3] += city.getRecovered();
    }

    private static void populateCasesCount(int[] stateCaseCount) {
        TOTAL_ACTIVE_CASES += stateCaseCount[0];
        TOTAL_CONFIRMED_CASES += stateCaseCount[1];
        TOTAL_DECEASED_CASES += stateCaseCount[2];
        TOTAL_RECOVERED_CASES += stateCaseCount[3];
    }

    private void populateNewStatesMap(Map<String, Integer> newStateCasesMap, int[] stateCaseCount) {
        newStateCasesMap.put(Config.ACTIVE.getConstants(), stateCaseCount[0]);
        newStateCasesMap.put(Config.CONFIRMED.getConstants(), stateCaseCount[1]);
        newStateCasesMap.put(Config.DECEASED.getConstants(), stateCaseCount[2]);
        newStateCasesMap.put(Config.RECOVERED.getConstants(), stateCaseCount[3]);
    }

    private void populateNewCitiesMap(Cities city, Map<String, Integer> newCityCasesMap) {
        newCityCasesMap.put(Config.ACTIVE.getConstants(), city.getActive());
        newCityCasesMap.put(Config.CONFIRMED.getConstants(), city.getConfirmed());
        newCityCasesMap.put(Config.DECEASED.getConstants(), city.getDeceased());
        newCityCasesMap.put(Config.RECOVERED.getConstants(), city.getRecovered());
    }


    public Map<String, Map<String, Integer>> getSTATES() {
        return this.STATES;
    }

    public static Integer getTotalActiveCases() {
        return TOTAL_ACTIVE_CASES;
    }

    public static Integer getTotalConfirmedCases() {
        return TOTAL_CONFIRMED_CASES;
    }

    public static Integer getTotalRecoveredCases() {
        return TOTAL_RECOVERED_CASES;
    }

    public static Integer getTotalDeceasedCases() {
        return TOTAL_DECEASED_CASES;
    }

    public Set<String> getListOfStates() {
        return this.STATES.keySet();
    }

    public Set<String> getListOfCities() {
        return this.CITIES.keySet();
    }
}
