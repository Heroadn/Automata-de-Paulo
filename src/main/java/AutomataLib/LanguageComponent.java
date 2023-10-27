package AutomataLib;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LanguageComponent {
    private final Automata automata;
    private final SearchComponent search;

    public LanguageComponent(Automata automata) {
        this.automata = automata;
        this.search   = new SearchComponent(this.automata);
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

    private State trace(
            State current,
            char symbol)
    {
        if(current == null)
            return null;

        List<Transition> next = search.find(
                current.getId(),
                String.valueOf(symbol));

        if(next.isEmpty())
            return null;

        return next.get(0).getDestiny();
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
                if(search.find(state.getId(), symbol).size() > 1)
                {
                    result.set(true);
                    return;
                }
            }
        });

        return result.get();
    }
}
