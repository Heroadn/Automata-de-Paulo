//Implementação de Automato, baseado no artigo 'Implementação de Autômatos Finitos'
//De Autoria de 'Paulo Renato Kinjo', foram usadas suas interfaces de 'Transition, Automata e State'
//foram implementadas com algumas modificações

public class Main {
    public static void main(String[] args)
    {
        //automato nier
        {
            Automata nier = new Automata("NIER");
            String line = "abcd";

            //Estados
            nier.addState(0, 1, 2, 3, 4);

            //Transições
            nier.addTransition(
                    new Transition(0).move(1).symbol("a"),
                    new Transition(1).move(2).symbol("b"),
                    new Transition(2).move(3).symbol("c"),
                    new Transition(3).move(4).symbol("d"));

            //Iniciais e Finais
            nier.setStart(0).setFinal(4);

            //executando
            nier.recognize(line, true);
        }

        //com loop no estado 1
        {
            Automata a2 = new Automata("A2");
            String line = "abbbbbbaaaa";

            //Estados
            a2.addState(0, 1, 2);

            //Transições
            a2.addTransition(
                    new Transition(0).move(1).symbol("a"),
                    new Transition(1).move(2).symbol("a"),
                    new Transition(1).loop("b"),
                    new Transition(2).loop("a"));

            //Iniciais e Finais
            a2.setStart(0).setFinal(2);

            //executando
            a2.recognize(line, true);
        }
    }

    public static void nfa(String[] args) {
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

            //Iniciais e Finais
            nfa.setStart(0);
            nfa.setFinal(2);

            //debug
            System.out.println(nfa.getName() + " TRANSITIONS");
            nfa.getTransitions().forEach(System.out::println);

            System.out.println("IS_NFA::NFA = " + nfa.isNFA());
        }
    }
}