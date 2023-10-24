package AutomataLib;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

//Classe responsavel por metodos auxuliares ao automata
public class BlackBox {
    final Automata automata;

    public BlackBox(
            Automata automata)
    {
        this.automata = automata;
    }

    boolean recognize(
            String string)
    {
        //estado atual sera o inicial
        this.automata.setCurrent(
                this.automata.getStart());

        //avance enquanto tiver simbolos transitaveis
        for (char symbol : string.toCharArray()) {
            next(symbol);
        }

        //atingindo o final a linguagem é reconhecida
        return this.automata.isFinal(
                this.automata.getCurrent());
    }

    private void next(
            char symbol)
    {
        State current = this.automata.getCurrent();
        State next = trace(
                current,
                symbol);

        this.automata.setCurrent(
                next);
    }

    private Automata minimize()
    {
        List<Map<Integer, State>> partition_new = new ArrayList<>();
        List<Map<String, State>> partition_old = new ArrayList<>();

        Map<String, State> finals     = this.automata.getFinals();
        Map<String, State> non_finals = this.automata.getNon_finals();

        partition_old.add(this.automata.getFinals());
        partition_old.add(this.automata.getNon_finals());

        while(partition_old.size() != partition_new.size())
        {

        }


        return null;
    }

    /**
     * Verifica se o automata tem as condiçoes de
     * nao ser deterministico
     * @return 'true' se o automata for nao deterministico
     */
    public boolean isNFA()
    {
        AtomicBoolean result = new AtomicBoolean(false);

        this.automata.getStates().forEach((id, state) -> {
            for (String symbol : this.automata.getAlphabet()) {
                if(search(state.getId(), symbol).size() > 1)
                {
                    result.set(true);
                    return;
                }
            }
        });

        return result.get();
    }

    private State trace(
            State current,
            char symbol)
    {
        if(current == null)
            return null;

        List<Transition> next = search(
                current.getId(),
                String.valueOf(symbol));

        if(next.isEmpty())
            return null;

        return next.get(0).getDestiny();
    }

    /**
     * procura uma transição para um estado, com base no symbolo
     * @param  stateId  ID do estado pertencente ao automato
     * @param  symbol  simbolo pertencente ao alfabeto do automato
     * @return transição para o 'state' com 'symbol'
     */
    public List<Transition> search(
            String stateId,
            String symbol)
    {
        State state = this.automata.getState(stateId);

        if(state == null)
            return new ArrayList<>();

        return state.search(symbol);
    }

    enum Reserved
    {
        NULL(" ");
        public final String label;

        Reserved(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

}
