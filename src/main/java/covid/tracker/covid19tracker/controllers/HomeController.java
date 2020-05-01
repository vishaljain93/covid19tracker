package covid.tracker.covid19tracker.controllers;

import covid.tracker.covid19tracker.model.LocationStats;
import covid.tracker.covid19tracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPreviousDay).sum();
        model.addAttribute("LocationStats", allStats);
        model.addAttribute("totalCasesReported", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
