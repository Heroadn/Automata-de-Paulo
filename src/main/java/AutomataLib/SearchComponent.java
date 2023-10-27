package AutomataLib;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SearchComponent {
    private final Automata automata;

    public SearchComponent(Automata automata) {
        this.automata = automata;
    }

    /**
     * procura uma transição para um estado, com base no symbolo
     * @param  stateId  ID do estado pertencente ao automato
     * @param  symbol  simbolo pertencente ao alfabeto do automato
     * @return transição para o 'state' com 'symbol'
     */
    public List<Transition> find(
            String stateId,
            String symbol)
    {
        State state = automata.getState(stateId);

        if(state == null)
            return new ArrayList<>();

        return state.search(symbol);
    }

    public Group closure(
            State state,
            String symbol)
    {

        Stack<State> backtrack = new Stack<>();
        Group result = new Group();
        List<Transition> paths;

        //at the beginning
        //add the start to the stack
        result.add(state);
        backtrack.add(state);

        //so while there's elements
        //in the stack the search can't stop
        while(!backtrack.isEmpty())
        {
            //getting possibles paths
            State now = backtrack.pop();
            paths = this.find(now.getId(), symbol);

            //add all to result
            for (Transition transition : paths) {
                result.add(transition.getDestiny());
            }

            //add all to the stack(to be visited later on)
            for (Transition transition : paths) {
                backtrack.add(transition.getDestiny());
            }
        }

        return result;
    }

    public Group closure(
            Group group)
    {
        Group result = new Group();

        for (State state : group) {
            result.addAll(closure(state));
        }

        result.autoAssignId();
        return result;
    }

    public Group closure(
            State start)
    {
        String nullSym  = Reserved.NULL.toString();
        return closure(start, nullSym);
    }
}
