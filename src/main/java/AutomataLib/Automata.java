package AutomataLib;

import java.util.*;

public class Automata {
    HashMap<String, State> states;
    HashMap<String, State> finals;
    HashMap<String, State> non_finals;
    HashSet<Transition> transitions;
    HashSet<String> alphabet;
    BlackBox blackBox;     //Metodos que o automato pode usar
    String startStateId;   //Estado inicial
    String currentId;      //Estado atual / head
    String name;           //Nome do automata(usado para testes)

    public Automata()
    {
        this.states = new HashMap<String, State>();
        this.finals = new HashMap<String, State>();
        this.transitions = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.non_finals = new HashMap<String, State>();

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
        //System.out.print("RECOGNIZES::" + this.getName() + " = ");
        //System.out.println(value ? "SUCCESS" : "FAILED");
        return blackBox.recognize(string);
    }

    public boolean accepts(String string, boolean debug)
    {
        boolean value = recognize(string);
        if(debug)
        {
            System.out.print("ACCEPTS::" + this.getName() + " = ");
            System.out.println(value ? "SUCCESS" : "FAILED");
        }
        return value;
    }

    public boolean rejects(String string, boolean debug)
    {
        boolean value = recognize(string);
        if(debug)
        {
            System.out.print("REJECTS::" + this.getName() + " = ");
            System.out.println(!value ? "SUCCESS" : "FAILED");
        }
        return value;
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
        non_finals.put(id, state);
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
        finals.put(id, new State(id));
        non_finals.remove(id);
    }

    /**
     * Faz com o estado 'id' seja final
     * @param  ids   identificadores do estado final
     */
    public void setFinal(
            String[] ids)
    {
        for (String id: ids)
            finals.put(id, new State(id));
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

        transitions.add(transition);
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

        return finals.containsKey(state.getId());
    }

    //TODO: REMOVE AFTER TESTING
    public Set<State> closure(String id)
    {
        return this.blackBox.closure(getState(id));
    }

    public Set<State> searchSequence(
            String id,
            String symbol)
    {
        return this.blackBox.depthSearch(getState(id), symbol);
    }


    public State getState(String id)
    {
        return getStates().get(id);
    }

    public Map<String, State> getFinals() {
        return finals;
    }

    public Map<String, State> getNon_finals() {
        return non_finals;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public State getStartState() {
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

        getTransitions().forEach((transition)->{
            result.append(" ").append(transition).append("\n");
        });

        result.append(" START: ")
              .append(this.getStartState().getId())
              .append("\n")
              .append(" FINAL: ");
        getFinals().forEach((key, value)->{
            result.append(key).append(" | ");
        });

        return result.toString();
    }
}
