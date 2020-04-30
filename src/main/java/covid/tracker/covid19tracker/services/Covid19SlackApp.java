package covid.tracker.covid19tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tomcat.util.json.ParseException;

import java.net.URISyntaxException;

public interface Covid19SlackApp {

    void sendMessage() throws JsonProcessingException, ParseException, URISyntaxException;
}
