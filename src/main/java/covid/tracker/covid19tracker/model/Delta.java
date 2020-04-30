package covid.tracker.covid19tracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Delta {

    private int confirmed;
    private int deceased;
    private int recovered;

}
