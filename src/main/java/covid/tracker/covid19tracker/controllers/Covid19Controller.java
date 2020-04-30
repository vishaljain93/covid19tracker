package covid.tracker.covid19tracker.controllers;

import covid.tracker.covid19tracker.constants.Constants;
import covid.tracker.covid19tracker.services.IndiaCoronaVirusTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Covid19Controller {

    @Autowired
    IndiaCoronaVirusTrackerService indiaCoronaVirusTrackerService;

    @GetMapping("/india")
    public String getIndiaCovid19Data(Model model) {
        model.addAttribute(Constants.TOTAL_ACTIVE_CASES, IndiaCoronaVirusTrackerService.getTotalActiveCases());
        model.addAttribute(Constants.TOTAL_CONFIRMED_CASES, IndiaCoronaVirusTrackerService.getTotalConfirmedCases());
        model.addAttribute(Constants.TOTAL_DECEASED_CASES, IndiaCoronaVirusTrackerService.getTotalDeceasedCases());
        model.addAttribute(Constants.TOTAL_RECOVERED_CASES, IndiaCoronaVirusTrackerService.getTotalRecoveredCases());
        model.addAttribute(Constants.STATES, indiaCoronaVirusTrackerService.getListOfStates());
        model.addAttribute(Constants.CITIES, indiaCoronaVirusTrackerService.getListOfCities());
        model.addAttribute(Constants.STATE_FULL_DATA, indiaCoronaVirusTrackerService.getSTATES());
        return Constants.COVID_19_INDIA;
    }
}
