package AutomataLib;

import java.util.*;

public class Automata {
    final HashMap<String, State> states;
    final HashSet<String> finals;
    final HashSet<String> non_finals;
    HashSet<String> alphabet;
    final BlackBox blackBox;     //Metodos que o automato pode usar
    String startStateId;   //Estado inicial
    String currentId;      //Estado atual / head
    String name;           //Nome do automata(usado para testes)

    public Automata()
    {
        this.states = new HashMap<>();
        this.finals = new HashSet<>();
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
        debugEvaluate("ACCEPTS::", value);
    }

    public void rejects(String string)
    {
        boolean value = recognize(string);
        debugEvaluate("REJECTS::", !value);
    }

    /**
     * Adiciona um estado com identificador 'id'
     * @param  id   identificado do estado
     */
    public void addState(
            String id)
    {
        State state = new State(id);
        addState(state);
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

    public String getName() {
        return name;
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

    private void debugEvaluate(String x, boolean value) {
        System.out.print(x + this.getName() + " = ");
        System.out.println(value ? "SUCCESS" : "FAILED");
    }
}
