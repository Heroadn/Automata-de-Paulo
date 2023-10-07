package Json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class State {
    @JsonProperty("map")
    public Map<String, Transition[]> transitions;

    @JsonCreator
    public State(Map<String, Transition[]> map) {
        this.transitions = map;
    }

}
