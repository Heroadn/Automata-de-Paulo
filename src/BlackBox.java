//Classe responsavel por metodos auxuliares ao automata
public final class BlackBox {

    public static void main(String[] args)
    {
        Automata nier = new Automata();
        String line = "ab";

        //Estados
        nier.addState(0);
        nier.addState(1);
        nier.addState(2);

        //Transições
        nier.addTransition(0, 1, "a");
        nier.addTransition(0, 2, "a");
        nier.addTransition(1, 2, "b");

        nier.getTransitions().forEach(System.out::println);

        //Iniciais e Finais
        nier.setStartState(0);
        nier.setFinalState(2);

        if(nier.recognize(line))
            System.out.println("DEBUG::SUCCESS");
        else
            System.out.println("DEBUG::FAILED");

    }
}
