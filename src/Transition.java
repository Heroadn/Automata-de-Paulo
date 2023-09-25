public class Transition {
    State origin;
    State destiny;
    String symbol;
    public Transition(State origin, State destiny, String symbol) {
        this.origin  = origin;
        this.destiny = destiny;
        this.symbol  = symbol;
    }

    public Transition(Integer origin, Integer destiny, String symbol) {
        this(
            new State(origin),
            new State(destiny),
            symbol);
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
    * return "Transition{" +
                "origin="    + origin.getId() +
                ", destiny=" + destiny.getId() +
                ", symbol='" + symbol + '\'' +
                '}';
    * */
}
