import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

//Classe responsavel por metodos auxuliares ao automata
public final class BlackBox {
    private final Automata automata;

    public BlackBox(Automata automata) {
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
        Transition transition = getTransition(
                current,
                String.valueOf(symbol));

        if(transition == null)
            return null;

        return transition.getDestiny();
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

        return this.automata.getTransitions().stream()
                .filter(transition -> transition.getOrigin() == state)
                .filter(transition -> transition.getSymbol().equals(symbol))
                .findFirst().get();
    }

    private long countTransition(
            State state,
            String symbol)
    {
        return this.automata.getTransitions().stream()
                .filter(transition -> transition.getOrigin() == state)
                .filter(transition -> transition.getSymbol().equals(symbol))
                .count();
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
                if(countTransition(state, symbol) > 1)
                {
                    result.set(true);
                    return;
                }
            }
        });

        return result.get();
    }

    public static void main(String[] args)
    {
        //automato nao deterministico
        {
            Automata nfa = new Automata("NFA");
            String line = "ab";

            //Estados
            nfa.addState(0, 1, 2);

            //Transições
            nfa.addTransition(
                    new Transition(0, 1, "a"),  //0 --- a ---> 1
                    new Transition(0, 2, "a"),  //0 --- a ---> 2
                    new Transition(1, 2, "b")); //1 --- b ---> 2

            //debug
            System.out.println("NFA TRANSITIONS");
            nfa.getTransitions().forEach(System.out::println);

            //Iniciais e Finais
            nfa.setStartState(0);
            nfa.setFinalState(2);
            System.out.println("IS_NFA::NFA = " + nfa.isNFA());
        }

        {
            Automata nier = new Automata("NIER");
            String line = "abcd";

            //Estados
            nier.addState(0, 1, 2, 3, 4);

            //Transições
            nier.addTransition(
                    new Transition(0, 1, "a"),
                    new Transition(1, 2, "b"),
                    new Transition(2, 3, "c"),
                    new Transition(3, 4, "d"));

            //Iniciais e Finais
            nier.setStartState(0);
            nier.setFinalState(4);

            System.out.print("RECOGNIZES::" + nier.getName() + " = ");
            if(nier.recognize(line))
                System.out.println("SUCCESS");
            else
                System.out.println("FAILED");
        }

        //com loop no estado 1
        {
            Automata a2 = new Automata("A2");
            String line = "abbbbbba";

            //Estados
            a2.addState(0, 1, 2);

            //Transições
            a2.addTransition(
                    new Transition(0, 1, "a"),
                    new Transition(1, 1, "b"),
                    new Transition(1, 2, "a"));

            //Iniciais e Finais
            a2.setStartState(0);
            a2.setFinalState(2);

            System.out.print("RECOGNIZES::" + a2.getName() + " = ");
            if(a2.recognize(line))
                System.out.println("SUCCESS");
            else
                System.out.println("FAILED");
        }
    }
}
