import java.util.List;
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

    private State next(
            State current,
            String symbol)
    {
        Transition next = search(
                current,
                String.valueOf(symbol)).get(0);

        return next.getDestiny();
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
}
