import java.util.HashMap;
import java.util.HashSet;

public class Automata {
    HashMap<Integer, State> states;
    HashMap<Integer, State> finalStates;
    HashSet<Transition> transitions;
    State startState;

    boolean blackBox;

    public Automata(boolean blackBox)
    {
        this();
        this.blackBox = blackBox;
    }

    public Automata()
    {
        this.states = new HashMap<>();
        this.finalStates = new HashMap<>();
        this.transitions = new HashSet<>();
    }

    boolean recognize(String string)
    {
        State start = this.startState;
        State current = start;

        //for each character in the string
        for (char symbol: string.toCharArray()) {
            Transition transition = getTransition(current, String.valueOf(symbol));

            //continue indo enquanto houver transições
            if(transition == null)
                return false;

            if(blackBox)
                debug(transition);

            State origin  = transition.getOrigin();
            State destiny = transition.getDestiny();
            current = destiny;
        }

        //verifica se o ultimo estado atingido é final
        if(isFinal(current))
            return true;

        return false;
    }

    State addState(
            Integer id)
    {
        return states.put(id, new State(id));
    }

    void setFinalState(
            Integer id)
    {
        finalStates.put(id, new State(id));
    }

    public void setStartState(
            int id)
    {
        startState = states.get(id);
    }

    public void addTransition(
            int origin,
            int destiny,
            String symbol)
    {
        State a = states.get(origin);
        State b = states.get(destiny);
        transitions.add(new Transition(a, b, symbol));
    }

    private Transition getTransition(State state, String symbol)
    {
        for (Transition transition : transitions)
        {
            if(transition.getOrigin() == state
                    && transition.getSymbol().equals(symbol))
                return transition;
        }

        return null;
    }

    private boolean isFinal(State state)
    {
        return finalStates.containsKey(state.getId());
    }

    private void debug(Transition transition)
    {
        System.out.println(transition);
    }

    private HashMap<Integer, State> getStates() {
        return states;
    }

    private void setStates(
            HashMap<Integer, State> states)
    {
        this.states = states;
    }

    private HashMap<Integer, State> getFinalStates() {
        return finalStates;
    }

    private void setFinalStates(
            HashMap<Integer, State> finalStates)
    {
        this.finalStates = finalStates;
    }

    private State getStartState()
    {
        return startState;
    }

    private void setStartState(State startState)
    {
        this.startState = startState;
    }

    public HashSet<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(HashSet<Transition> transitions) {
        this.transitions = transitions;
    }
}
