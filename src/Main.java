//Implementação de Automato, baseado no artigo 'Implementação de Autômatos Finitos'
//De Autoria de 'Paulo Renato Kinjo', foram usadas suas interfaces de 'Transition, Automata e State'
//foram implementadas com algumas modificações

public class Main {
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
            System.out.println(nfa.getName() + " TRANSITIONS");
            nfa.getTransitions().forEach(System.out::println);

            //Iniciais e Finais
            nfa.setStart(0);
            nfa.setFinal(2);
            System.out.println("IS_NFA::NFA = " + nfa.isNFA());
        }

        //automato nier
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
            nier.setStart(0);
            nier.setFinal(4);

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
            a2.setStart(0);
            a2.setFinal(2);

            System.out.print("RECOGNIZES::" + a2.getName() + " = ");
            if(a2.recognize(line))
                System.out.println("SUCCESS");
            else
                System.out.println("FAILED");
        }
    }
}