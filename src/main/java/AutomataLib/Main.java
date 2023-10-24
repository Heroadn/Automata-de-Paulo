package AutomataLib;//Implementação de Automato, baseado no artigo 'Implementação de Autômatos Finitos'
//De Autoria de 'Paulo Renato Kinjo', foram usadas suas interfaces de 'Automata.Transition, Automata.Automata e Automata.State'
//foram implementadas com algumas modificações

import Json.AutomataJson;

public class Main {
    public static void main(String[] args)
    {
        /*
        //automato nier
        {
            Automata nier = new Automata("NIER");
            String line = "abcd";

            //Estados
            nier.addState("0", "1", "2", "3", "4");

            //Transições
            nier.addTransition(
                    new Transition().from("0").to("1").withSymbol("a"),
                    new Transition().from("1").to("2").withSymbol("b"),
                    new Transition().from("2").to("3").withSymbol("c"),
                    new Transition().from("3").to("4").withSymbol("d"));

            //Iniciais e Finais
            nier.setStart("0").setFinal("4");

            //executando
            nier.accepts(line, true);
        }

        //com loop no estado 1
        {
            Automata a2 = new Automata("A2");
            String line = "abbbbbbaaaa";

            //Estados
            a2.addState("0", "1", "2");

            //Transições
            a2.addTransition(
                    new Transition().from("0").to("1").withSymbol("a"),
                    new Transition().from("1").to("2").withSymbol("a"),
                    new Transition().from("1").toLoop("b"),
                    new Transition().from("2").toLoop("a"));

            //Iniciais e Finais
            a2.setStart("0").setFinal("2");

            //executando
            a2.accepts(line, true);
        }*/

        nfa(null);
    }

    public static void nfa(String[] args) {
        //automato nao deterministico
        /*
        {
            Automata nfa = new Automata("NFA");
            String line = "ab";

            //Estados
            nfa.addState("0", "1", "2", "3", "4", "5", "6", "7");

            //Transições
            nfa.addTransition(
                    new Transition("0", "1", "a"),  //0 --- a ---> 1
                    new Transition("0", "2", "a"),  //0 --- a ---> 2
                    new Transition("1", "2", "b"),
                    new Transition("2", "3", " "),
                    new Transition("3", "4", " "),
                    new Transition("4", "5", " "),
                    new Transition("6", "7", " "));

            //Iniciais e Finais
            nfa.setStart("0");
            nfa.setFinal("7");

            //debug
            //System.out.println(nfa);
        }*/


        AutomataJson a2Json = AutomataJson.readAutomata("a2.json");
        Automata a2 = a2Json.toAutomata();
        //System.out.println(a2);

        a2.toDfa();
        //System.out.println(a2);
        //for (State state:a2.closure("4")) {
        //    System.out.println(state.getId());
        //}
    }
}