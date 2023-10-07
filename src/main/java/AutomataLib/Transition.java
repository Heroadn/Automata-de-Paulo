package AutomataLib;

public class Transition {
    State origin;
    State destiny;
    String symbol;

    public Transition() {
    }

    public Transition(String origin) {
        this.origin  = new State(origin);
    }

    public Transition(State origin, State destiny, String symbol) {
        this.origin  = origin;
        this.destiny = destiny;
        this.symbol  = symbol;
    }

    public Transition(String origin, String destiny, String symbol) {
        this(
            new State(origin),
            new State(destiny),
            symbol);
    }

    public Transition to(String destiny, String symbol)
    {
        this.destiny = new State(destiny);
        this.symbol  = symbol;
        return this;
    }

    public Transition from(String origin)
    {
        this.origin = new State(origin);
        return this;
    }

    public Transition to(String destiny)
    {
        this.destiny = new State(destiny);
        return this;
    }

    public Transition withSymbol(String symbol)
    {
        this.symbol  = symbol;
        return this;
    }

    public Transition toLoop(String symbol)
    {
        this.destiny = this.getOrigin();
        this.symbol  = symbol;
        return this;
    }

    public State getOrigin() {
        return origin;
    }

    public void setOrigin(State origin) {
        this.origin = origin;
    }

    public State getDestiny() {
        return destiny;
    }

    public void setDestiny(State destiny) {
        this.destiny = destiny;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Transition{" +
                " " + origin.getId()  +
                " --- " + symbol + " ---> " +
                destiny.getId() +
                " }";
    }

    /*
    * return "Automata.Transition{" +
                "origin="    + origin.getId() +
                ", destiny=" + destiny.getId() +
                ", symbol='" + symbol + '\'' +
                '}';
    * */
}
