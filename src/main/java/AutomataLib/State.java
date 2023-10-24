package AutomataLib;

import java.util.*;
import java.util.stream.Collectors;

public class State extends ArrayList<Transition> {
    private String id;

    public State(String id) {
        this.id = id;
    }

    public void addTransition(Transition transition)
    {
        this.add(transition);
    }

    public List<Transition> search(String symbol)
    {
        return this.stream()
                .filter(transition -> transition.getSymbol().equalsIgnoreCase(symbol))
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
