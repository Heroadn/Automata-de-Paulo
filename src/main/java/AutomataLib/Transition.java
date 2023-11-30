package AutomataLib;

public class Transition {
    State origin;
    State destiny;
    String symbol;

    public Transition() {
    }

    public Transition(String symbol) {
        this.symbol = symbol;
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

    public Transition(Transition transition) {
        this.origin = transition.getOrigin();
        this.destiny = transition.getOrigin();
        this.symbol = transition.getSymbol();
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

    public Transition toLoop()
    {
        this.destiny = this.getOrigin();
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
