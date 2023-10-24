package AutomataLib;

import java.util.*;

public class MetalBox extends BlackBox {

    public MetalBox(Automata automata) {
        super(automata);
    }

    class Group extends HashSet<State>
    {
        private State state;

        //generates an id based in the concat of all elements
        public String generateId()
        {
            String id = "";

            //the id will be the concat of all states
            for (State state : this)
                id = String.format("%s %s", id, state.getId());

            return id;
        }

        public void autoAssignId()
        {
            setId(generateId());
        }

        public void addTransition(Transition transition)
        {
            this.state.addTransition(transition);
        }

        public void setId(String id)
        {
            this.state.setId(id);
        }

        public String getId()
        {
            return this.state.getId();
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

    public Automata toDfa()
    {
        Automata result = new Automata(this.automata.getName());
        String nullSym  = BlackBox.Reserved.NULL.toString();
        Group start     = closure(this.automata.getStart());

        Queue<Group> queue = new ArrayDeque<>();
        HashMap<String, Group> groups  = new HashMap<>();

        queue.add(start);

        //while(!queue.isEmpty())
        for (int i = 0; i < 1; i++)
        {
            //{[1,2,4], [5,3,6], [4], ....}
            Group now = queue.remove();

            //
            for (String symbol : this.automata.alphabet) {
                //skip null
                if(symbol.equalsIgnoreCase(nullSym))
                    continue;

                //each symbol has its own group
                System.out.println("SYMBOL: " + symbol);
                Set<State> states = new HashSet<>();

                //
                for (State state : now) {
                    //transitions
                    createGroup(symbol, state);
                }


                //TODO: only add groups that are not in the set
                //saving groups
                //group = new Group();
                //groups.add(group);

                //adding group
                //queue.add(group);
            }
        }

        //for (Group g : groups) {
        //    System.out.println("TESTE: " + g);
        //}

        return result;
    }

    private void createGroup(String symbol, State state) {
        Group group = new Group();
        for (Transition transition : state.search(symbol))
            group.add(transition.getDestiny());
    }

    public Group closure(State state)
    {
        return depthSearch(state, " ");
    }

    public Group depthSearch(
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
            paths = search(now.getId(), symbol);

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
}
