package covid.tracker.covid19tracker.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import covid.tracker.covid19tracker.beans.Config;
import covid.tracker.covid19tracker.beans.StateData;
import covid.tracker.covid19tracker.model.SlackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class SendNotification implements Covid19SlackApp {

    private RestTemplate restTemplate;

    private StateData stateData;

    @Autowired
    public SendNotification(RestTemplate restTemplate, StateData stateData) {
        this.restTemplate = restTemplate;
        this.stateData = stateData;
    }

    @Override
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void sendMessage() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SlackMessage request = mapper.readValue(stateData.getStateData(), SlackMessage.class);
        String response = restTemplate.postForObject(Config.SLACK.getUrl(), request, String.class);
        log.info(response);
    }
}
