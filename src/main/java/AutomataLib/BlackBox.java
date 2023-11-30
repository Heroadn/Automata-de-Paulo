package AutomataLib;

//Classe responsavel por metodos auxuliares ao automata
public class BlackBox {
    private final ConversionComponent conversion;
    private final LanguageComponent language;

    public BlackBox(
            Automata automata)
    {
        this.conversion = new ConversionComponent(automata);
        this.language   = new LanguageComponent(automata);
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
        return this.language.isNFA();
    }

    public Automata toDfa()
    {
        return conversion.toDfa();
    }
}
