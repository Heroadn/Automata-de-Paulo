import java.util.*;

public class Automata {
    HashMap<Integer, State> states;
    HashMap<Integer, State> finalStates;
    HashSet<Transition> transitions;
    HashSet<String> alphabet;
    State startState;   //Estado inicial
    State current;      //Estado atual
    BlackBox blackBox;  //Metodos que o automato pode usar
    String name;        //Nome do automata(usado para testes)

    public Automata()
    {
        this.states = new HashMap<>();
        this.finalStates = new HashMap<>();
        this.transitions = new HashSet<>();
        this.alphabet = new HashSet<>();

        blackBox = new BlackBox(this);
    }

    public Automata(
            String name)
    {
        this();
        this.name = name;
    }

    public Automata(String name,
                    Integer[] states,
                    Transition[] transitions)
    {
        this();
        this.addState(states);
        this.addTransition(transitions);
        this.name = name;
    }

    /**
     * Retorna se o automato reconhece a linguagem 'string'
     * @param  string  sequencia de simbolos
     * @return se 'string' for reconhecida pelo automato retorna true
     */
    boolean recognize(
            String string)
    {
        return blackBox.recognize(string);
    }

    /**
     * Verifica se o automata tem as condiçoes de
     * nao ser deterministico
     * @return 'true' se o automata for nao deterministico
     */
    public boolean isNFA()
    {
        return blackBox.isNFA();
    }

    /**
     * Adiciona um estado com identificador 'id'
     * @param  id   identificado do estado
     */
    public void addState(
            Integer id)
    {
        states.put(id, new State(id));
    }

    /**
     * Cria um estado com identificadores 'ids'
     * @param  ids   identificadores de estado
     */
    public void addState(
            Integer... ids)
    {
        for (Integer id: ids)
            addState(id);
    }

    /**
     * Faz com o estado 'id' seja final
     * @param  id   identificador do estado, setado por @addState
     */
    public void setFinal(
            Integer id)
    {
        finalStates.put(id, new State(id));
    }

    /**
     * Faz com o estado 'id' seja final
     * @param  ids   identificadores do estado final
     */
    public void setFinal(
            Integer... ids)
    {
        for (Integer id: ids)
            finalStates.put(id, new State(id));
    }

    /**
     * Faz com o estado 'id' seja inicial
     * @param  id   identificador do estado, setado por @addState
     */
    public void setStart(
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

    public void addTransition(
            Transition... transitions)
    {
        for (Transition transition : transitions)
            addTransition(
                    transition.getOrigin().getId(),
                    transition.getDestiny().getId(),
                    transition.getSymbol());
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
     * Verifica se o estado em questão é final
     * @param  state  estado pertencente ao automato
     * @return      se o estado é final
     */
    public boolean isFinal(
            State state)
    {
        if(state == null)
            return false;

        return finalStates.containsKey(state.getId());
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

    public State getCurrent() {
        return current;
    }

    public void setCurrent(
            State current) {
        this.current = current;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(
            HashSet<String> alphabet) {
        this.alphabet = alphabet;
    }

    public String getName() {
        return name;
    }

    public void setName(
            String name) {
        this.name = name;
    }
}
