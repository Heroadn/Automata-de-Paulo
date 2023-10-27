package AutomataLib;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

class Group extends HashSet<State> {
    private final State main;

    public Group() {
        main = new State("");
    }

    //generates an id based in the concat of all elements
    public String generateIdConcat() {
        String id = "";

        //the id will be the concat of all states
        for (State state : this)
            id = String.format("%s %s", id, state.getId());

        return id;
    }

    public String generateIdHash() {
        return String.valueOf(this.hashCode());
    }

    public void autoAssignId() {
        setId(generateIdConcat());
        //setId(generateIdHash());
    }

    public void addTransition(Transition transition) {
        this.main.addTransition(transition);
    }

    public void setId(String id) {
        this.main.setId(id);
    }

    public String getId() {
        return this.main.getId();
    }

    public List<Transition> getTransitions()
    {
        return this.main;
    }

    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Group group = (Group) o;
        return Objects.equals(((Group) o).getId(), group.getId());
    }
}
