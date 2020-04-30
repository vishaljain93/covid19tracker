package covid.tracker.covid19tracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cities {

    private String notes;
    private int active;
    private int confirmed;
    private int deceased;
    private int recovered;
    Delta delta;
}
