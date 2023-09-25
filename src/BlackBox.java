//Classe responsavel por metodos auxuliares ao automata
//No momento apenas testes da lib
public final class BlackBox {

    public static void main(String[] args)
    {
        //automato nao deterministico
        {
            Automata nfa = new Automata();
            String line = "ab";

            //Estados
            nfa.addState(0);
            nfa.addState(1);
            nfa.addState(2);

            //Transições
            nfa.addTransition(0, 1, "a"); //0 --- a ---> 1
            nfa.addTransition(0, 2, "a"); //0 --- a ---> 2
            nfa.addTransition(1, 2, "b"); //1 --- b ---> 2

            //debug
            System.out.println("NIER TRANSITIONS");
            nfa.getTransitions().forEach(System.out::println);

            //Iniciais e Finais
            nfa.setStartState(0);
            nfa.setFinalState(2);
            System.out.println("IS_NFA::NFA = " + nfa.isNFA());
        }

        {
            Automata nier = new Automata();
            String line = "abcd";

            //Estados
            nier.addState(0);
            nier.addState(1);
            nier.addState(2);
            nier.addState(3);
            nier.addState(4);

            //Transições
            nier.addTransition(0, 1, "a");
            nier.addTransition(1, 2, "b");
            nier.addTransition(2, 3, "c");
            nier.addTransition(3, 4, "d");

            //Iniciais e Finais
            nier.setStartState(0);
            nier.setFinalState(4);

            System.out.print("RECOGNIZES::NIER = ");
            if(nier.recognize(line))
                System.out.println("SUCCESS");
            else
                System.out.println("FAILED");
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

            System.out.print("RECOGNIZES::A2 = ");
            if(a2.recognize(line))
                System.out.println("SUCCESS");
            else
                System.out.println("FAILED");
        }
    }
}
