package covid.tracker.covid19tracker.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class IndiaLocationStats {

    Map<String, States> states;
}
