package AutomataLib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class State {
    private String id;
    private List<Transition> transitions;

    public State(String id) {
        this.id = id;
        this.transitions = new ArrayList<>();
    }

    public void addTransition(Transition transition)
    {
        transitions.add(transition);
    }

    public List<Transition> search(String symbol)
    {
        return transitions.stream()
                .filter(transition -> transition.getSymbol().equalsIgnoreCase(symbol))
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                '}';
    }
}
