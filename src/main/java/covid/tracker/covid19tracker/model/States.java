package covid.tracker.covid19tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class States {

    @JsonProperty("districtData")
    Map<String, Cities> cities;
    private String statecode;
}
