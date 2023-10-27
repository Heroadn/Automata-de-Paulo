package AutomataLib;

import java.util.*;

public class Automata {
    HashMap<String, State> states;
    HashSet<String> finals;
    HashSet<String> non_finals;
    HashSet<Transition> transitions;
    HashSet<String> alphabet;
    BlackBox blackBox;     //Metodos que o automato pode usar
    String startStateId;   //Estado inicial
    String currentId;      //Estado atual / head
    String name;           //Nome do automata(usado para testes)

    public Automata()
    {
        this.states = new HashMap<>();
        this.finals = new HashSet<>();
        this.transitions = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.non_finals = new HashSet<>();

        blackBox = new BlackBox(this);
    }

    public Automata(
            String name)
    {
        this();
        this.name = name;
    }

    public Automata(String name,
                    String[] states,
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

    public void accepts(String string)
    {
        boolean value = recognize(string);
        System.out.print("ACCEPTS::" + this.getName() + " = ");
        System.out.println(value ? "SUCCESS" : "FAILED");
    }

    public void rejects(String string)
    {
        boolean value = recognize(string);
        System.out.print("REJECTS::" + this.getName() + " = ");
        System.out.println(!value ? "SUCCESS" : "FAILED");
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
            String id)
    {
        State state = new State(id);

        if(contains(id))
            return;

        states.put(id, state);
        non_finals.add(id);
    }

    public void addState(
            State state)
    {
        String id = state.getId();

        if(contains(id))
            return;

        states.put(id, state);
        non_finals.add(id);
    }

    /**
     * Cria um estado com identificadores 'ids'
     * @param  ids   identificadores de estado
     */
    public void addState(
            String... ids)
    {
        for (String id: ids)
            addState(id);
    }

    /**
     * Faz com o estado 'id' seja final
     * @param  id   identificador do estado, setado por @addState
     */
    public void setFinal(
            String id)
    {
        //new State(id)
        finals.add(id);
        non_finals.remove(id);
    }

    /**
     * Faz com o estado 'id' seja final
     * @param  ids   identificadores do estado final
     */
    public void setFinal(
            String[] ids)
    {
        //new State(id)
        Collections.addAll(finals, ids);
    }

    /**
     * Faz com o estado 'id' seja inicial
     * @param  id   identificador do estado, setado por @addState
     */
    public Automata setStart(
            String id)
    {
        startStateId = id;
        return this;
    }

    /**
     * Faz a linkagem/chaining do estado
     * na posição 'origin' com 'destiny' usando o 'symbol'
     * @param  origin   estado de origem
     * @param  destiny  estado destino
     * @param  symbol   simbolo pertencente ao alfabeto do automato
     */
    public void addTransition(
            String origin,
            String destiny,
            String symbol)
    {
        State a = states.get(origin);
        State b = states.get(destiny);
        Transition transition = new Transition(a, b, symbol);
        a.addTransition(transition);

        //transitions.add(transition);
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


        return finals.contains(state.getId());
    }

    //TODO: REMOVE AFTER TESTING
    public Set<State> closure(String id)
    {
        return this.blackBox.closure(getState(id));
    }

    public Automata toDfa()
    {
        return blackBox.toDfa();
    }
    public State getState(String id)
    {
        return getStates().get(id);
    }

    public Set<String> getFinals() {
        return finals;
    }

    public Set<String> getNon_finals() {
        return non_finals;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public State getStart() {
        return getState(startStateId);
    }

    public Map<String, State> getStates() {
        return states;
    }

    public State getCurrent() {
        return getState(currentId);
    }

    public void setCurrent(
            State current)
    {
        if(current == null)
        {
            this.currentId = null;
            return;
        }

        this.currentId = current.getId();
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

    boolean contains(String id)
    {
        return getState(id) != null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getName())
              .append("\n");

        getStates().forEach((key, value) -> {
            result.append(value).append("\n");
            value.forEach((transition)-> result.append(" ").append(transition).append("\n"));
        });

        result.append(" START: ")
              .append(this.getStart().getId())
              .append("\n")
              .append(" FINAL: ");
        getFinals().forEach( value -> result.append(value).append(" | "));

        return result.toString();
    }
}
