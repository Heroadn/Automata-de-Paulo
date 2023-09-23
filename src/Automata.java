import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Automata {
    HashMap<Integer, State> states;
    HashMap<Integer, State> finalStates;
    HashSet<Transition> transitions;
    HashSet<String> alphabet;
    State startState;
    boolean blackBox;

    public Automata()
    {
        this.states = new HashMap<>();
        this.finalStates = new HashMap<>();
        this.transitions = new HashSet<>();
        this.alphabet = new HashSet<>();
    }

    public Automata(boolean blackBox)
    {
        this();
        this.blackBox = blackBox;
    }

    /**
     * Retorna se o automato reconhece a linguagem 'string'
     * @param  string  sequencia de simbolos
     * @return se 'string' for reconhecida pelo automato retorna true
     */
    boolean recognize(String string)
    {
        State start = this.startState;
        State current = start;

        //for each character in the string
        for (char symbol: string.toCharArray())
        {
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

        //se for o ultimo estado atingido
        //for final a linguagem é reconhecida
        return isFinal(current);
    }

    /**
     * Adiciona um estado com identificador 'id'
     * @param  id   identificado do estado
     */
    public State addState(
            Integer id)
    {
        return states.put(id, new State(id));
    }

    /**
     * Faz com o estado 'id' seja final
     * @param  id   identificador do estado, setado por @addState
     */
    public void setFinalState(
            Integer id)
    {
        finalStates.put(id, new State(id));
    }

    /**
     * Faz com o estado 'id' seja inicial
     * @param  id   identificador do estado, setado por @addState
     */
    public void setStartState(
            int id)
    {
        startState = states.get(id);
    }

    /**
     * Faz a linkagem/chaining do estado
     * na posição 'origin' com 'destiny' usando o 'symbol'
     * @param  origin   estado de origem
     * @param  destiny  estado destino
     * @param  symbol   simbolo pertencente ao alfabeto do automato
     */
    public void addTransition(
            int origin,
            int destiny,
            String symbol)
    {
        State a = states.get(origin);
        State b = states.get(destiny);
        transitions.add(new Transition(a, b, symbol));
        alphabet.add(symbol);
    }

    /**
     * Adiciona o alphabeto do automata,
     * seu alfabeto tambem pode ser adicionado
     * indiretamente pelo metodo addTransition
     * @param  alphabet   alfabeto do automata
     */
    public void setAlphabet(
            List<String> alphabet)
    {
        this.alphabet.addAll(alphabet);
    }

    /**
     * Verifica se o automata tem as condiçoes de
     * nao ser deterministico
     * @return 'true' se o automata for nao deterministico
     */
    public boolean isNFA()
    {
        AtomicBoolean result = new AtomicBoolean(false);

        this.states.forEach((id, state) -> {
            for (String symbol : this.alphabet) {
                if(countTransition(state, symbol) > 1)
                {
                    result.set(true);
                    return;
                }
            }
        });

        return result.get();
    }

    /**
     * Retorna uma transição para um estado, com base no symbolo
     * @param  state   estado pertencente ao automato
     * @param  symbol  simbolo pertencente ao alfabeto do automato
     * @return transição para o 'state' com 'symbol'
     */
    private Transition getTransition(
            State state,
            String symbol)
    {
        for (Transition transition : transitions)
        {
            if(transition.getOrigin() == state
                    && transition.getSymbol().equals(symbol))
                return transition;
        }

        return null;
    }

    private int countTransition(
            State state,
            String symbol)
    {
        int count = 0;

        for (Transition transition : transitions)
        {
            if(transition.getOrigin() == state
                    && transition.getSymbol().equals(symbol))
                count++;
        }

        return count;
    }

    /**
     * Verifica se o estado em questão é final
     * @param  state  estado pertencente ao automato
     * @return      se o estado é final
     */
    private boolean isFinal(
            State state)
    {
        return finalStates.containsKey(state.getId());
    }

    /**
     * ;)
     * @param  transition  Transição
     */
    private void debug(
            Transition transition)
    {
        System.out.println(transition);
    }

    public Map<Integer, State> getFinalStates() {
        return finalStates;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public State getStartState() {
        return startState;
    }

    public Map<Integer, State> getStates() {
        return states;
    }

}
