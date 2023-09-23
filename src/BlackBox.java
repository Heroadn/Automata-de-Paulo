//Classe responsavel por metodos auxuliares ao automata
public final class BlackBox {

    public static void main(String[] args)
    {
        {
            Automata nfa = new Automata();
            String line = "ab";

            //Estados
            nfa.addState(0);
            nfa.addState(1);
            nfa.addState(2);

            //Transições
            nfa.addTransition(0, 1, "a");
            nfa.addTransition(0, 2, "a");
            nfa.addTransition(1, 2, "b");

            nfa.getTransitions().forEach(System.out::println);

            //Iniciais e Finais
            nfa.setStartState(0);
            nfa.setFinalState(2);

            System.out.println("IS_NFA::NFA: " + nfa.isNFA());
            if(nfa.recognize(line))
                System.out.println("DEBUG::NFA::SUCCESS");
            else
                System.out.println("DEBUG::NFA::FAILED");
        }

        //com loop no estado 1
        {
            Automata a2 = new Automata();
            String line = "abbbbbba";

            //Estados
            a2.addState(0);
            a2.addState(1);
            a2.addState(2);

            //Transições
            a2.addTransition(0, 1, "a");
            a2.addTransition(1, 1, "b");
            a2.addTransition(1, 2, "a");

            //Iniciais e Finais
            a2.setStartState(0);
            a2.setFinalState(2);

            System.out.println("IS_NFA::A2: " + a2.isNFA());
            if(a2.recognize(line))
                System.out.println("DEBUG::A2::SUCCESS");
            else
                System.out.println("DEBUG::A2::FAILED");
        }
    }
}
