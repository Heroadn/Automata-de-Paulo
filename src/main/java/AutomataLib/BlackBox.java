package AutomataLib;

import java.util.concurrent.atomic.AtomicBoolean;

//Classe responsavel por metodos auxuliares ao automata
public class BlackBox {
    private final Automata automata;
    private final SearchComponent search;
    private final ConversionComponent conversion;
    private final LanguageComponent language;

    public BlackBox(
            Automata automata)
    {
        this.automata   = automata;
        this.search     = new SearchComponent(this.automata);
        this.conversion = new ConversionComponent(this.automata, this.search);
        this.language   = new LanguageComponent(this.automata, this.search);
    }

    boolean recognize(
            String string)
    {
        return this.language.recognize(string);
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

    public Automata toDfa()
    {
        return conversion.toDfa();
    }

    public Group closure(State start)
    {
        return search.closure(start);
    }
}
