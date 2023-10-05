import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

//Classe responsavel por metodos auxuliares ao automata
public final class BlackBox {
    private final Automata automata;

    public BlackBox(
            Automata automata)
    {
        this.automata = automata;
    }

    boolean recognize(
            String string)
    {
        State next = null;

        //estado atual sera o inicial
        this.automata.setCurrent(
                this.automata.getStartState());

        //for each character in the string
        for (char symbol: string.toCharArray())
        {
            //continue indo enquanto houver transições
            next = next(
                    this.automata.getCurrent(),
                    String.valueOf(symbol));

            //estado atual como o proximo da transição
            this.automata.setCurrent(
                    next);
        }

        //se tiver atingido o estado final
        //a linguagem é reconhecida
        return this.automata.isFinal(
                this.automata.getCurrent());
    }

    Automata minimize()
    {
        List<Map<Integer, State>> partition_new = new ArrayList<>();
        List<Map<Integer, State>> partition_old = new ArrayList<>();

        Map<Integer, State> finals     = this.automata.getFinals();
        Map<Integer, State> non_finals = this.automata.getNon_finals();

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
                if(search(state, symbol).size() > 1)
                {
                    result.set(true);
                    return;
                }
            }
        });

        return result.get();
    }

    private List<Map<Integer, State>> split( List<Map<Integer, State>> partition)
    {
        List<Map<Integer, State>> result =  new ArrayList<>();

        return result;
    }


    private State next(
            State current,
            String symbol)
    {
        List<Transition> next = search(
                current,
                String.valueOf(symbol));

        if(next.isEmpty())
            return null;

        return next.get(0).getDestiny();
    }

    /**
     * procura uma transição para um estado, com base no symbolo
     * @param  state   estado pertencente ao automato
     * @param  symbol  simbolo pertencente ao alfabeto do automato
     * @return transição para o 'state' com 'symbol'
     */
    private List<Transition> search(
            State state,
            String symbol)
    {
        return this.automata.getTransitions().stream()
                .filter(transition -> transition.getOrigin() == state)
                .filter(transition -> transition.getSymbol().equals(symbol))
                .collect(Collectors.toList());
    }
}
