package AutomataLib;

import java.util.*;

public class ConversionComponent {

    private final Automata automata;

    private final SearchComponent search;

    public ConversionComponent(Automata automata) {
        this.automata = automata;
        this.search   = new SearchComponent(this.automata);
    }

    public Automata toDfa()
    {
        Automata result = new Automata(this.automata.getName());
        String nullSym  = Reserved.NULL.toString();
        Group start     = search.closure(this.automata.getStart());
        start.autoAssignId();

        Queue<Group> queue = new ArrayDeque<>();
        Set<Group> groups  = new HashSet<>();

        //
        queue.add(start);
        groups.add(start);

        while(!queue.isEmpty())
        {
            //{[1,2,4], [5,3,6], [4], ....}
            Group now = queue.remove();

            //
            for (String symbol : this.automata.alphabet) {
                //skip null
                if(symbol.equalsIgnoreCase(nullSym))
                    continue;

                //each symbol has its own group
                Group group = search.closure(mergeGroup(symbol, now));

                //linking
                if (group.size() > 0 && now.size() > 0)
                {
                    Transition transition = new Transition(symbol);
                    transition.from(now.getId());
                    transition.to(group.getId());
                    now.addTransition(transition);
                }

                //adding group
                if(!groups.contains(group))
                    queue.add(group);

                //saving groups, that are already worked on
                groups.add(group);
            }
        }

        groups.forEach( group -> {
            result.addState(group.getId());

            this.automata.getFinals().forEach( value -> {
                if(group.contains(this.automata.getState(value)))
                    result.setFinal(group.getId());
            });
        });

        groups.forEach( group -> {
            for (Transition transition : group.getTransitions())
                result.addTransition(transition);
        });

        result.setStart(start.getId());


        return result;
    }

    private Group mergeGroup(String symbol, Group set) {
        Group group = new Group();

        //it will search each state in set
        //and group its transitions according to symbol
        for (State state : set)
            for (Transition transition : state.search(symbol))
                group.add(transition.getDestiny());

        group.autoAssignId();
        return group;
    }
}
