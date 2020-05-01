package covid.tracker.covid19tracker.controllers;

import covid.tracker.covid19tracker.beans.Config;
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
        model.addAttribute(Config.TOTAL_ACTIVE_CASES.getConstants(), IndiaCoronaVirusTrackerService.getTotalActiveCases());
        model.addAttribute(Config.TOTAL_CONFIRMED_CASES.getConstants(), IndiaCoronaVirusTrackerService.getTotalConfirmedCases());
        model.addAttribute(Config.TOTAL_DECEASED_CASES.getConstants(), IndiaCoronaVirusTrackerService.getTotalDeceasedCases());
        model.addAttribute(Config.TOTAL_RECOVERED_CASES.getConstants(), IndiaCoronaVirusTrackerService.getTotalRecoveredCases());
        model.addAttribute(Config.STATES.getConstants(), indiaCoronaVirusTrackerService.getListOfStates());
        model.addAttribute(Config.CITIES.getConstants(), indiaCoronaVirusTrackerService.getListOfCities());
        model.addAttribute(Config.STATE_DATA.getConstants(), indiaCoronaVirusTrackerService.getSTATES());
        return Config.COVID_19_INDIA.getConstants();
    }
}
