//De Autoria de 'Paulo Renato Kinjo', foram usadas suas interfaces de 'Automata.Transition, Automata.Automata e Automata.State'
//foram implementadas com algumas modificações

import AutomataLib.Automata;
import AutomataLib.Transition;
import Json.AutomataJson;

public class Main {
    public static void main(String[] args)
    {
        dfa();
        nfa();
    }

    public static void dfa()
    {
        //automato nier
        {
            Automata nier = new Automata("NIER");
            String line = "abcd";

            //Estados
            nier.addState("0", "1", "2", "3", "4");

            //Transições
            nier.addTransition(
                    new Transition("a").from("0").to("1"),
                    new Transition("b").from("1").to("2"),
                    new Transition("c").from("2").to("3"),
                    new Transition("d").from("3").to("4"));

            //Iniciais e Finais
            nier.setStart("0").setFinal("4");

            //executando e mostrando resultados
            nier.accepts(line);
        }

        //com loop no estado 1
        {
            Automata a2 = new Automata("A2");
            String line = "abbbbbbaaaa";

            //Estados
            a2.addState("0", "1", "2");

            //Transições
            a2.addTransition(
                    new Transition("a").from("0").to("1"),
                    new Transition("a").from("1").to("2"),
                    new Transition("b").from("1").toLoop(),
                    new Transition("a").from("2").toLoop());

            //Iniciais e Finais
            a2.setStart("0").setFinal("2");

            //executando e mostrando resultados
            a2.accepts(line);
        }

        AutomataJson json = AutomataJson.readAutomata("2b.json");
        Automata automata = json.toAutomata();

        if(json.debug.exec)
        {
            for (String line: json.debug.accepts) {
                automata.accepts(line);
            }

            for (String line: json.debug.rejects) {
                automata.rejects(line);
            }

        }

    }

    public static void nfa() {
        //automato nao deterministico
        {
            Automata basicNFA = new Automata("BASIC_NFA");
            String line = "ab";

            //Estados
            basicNFA.addState("0", "1", "2", "3", "4", "5", "6", "7");

            //Transições
            basicNFA.addTransition(
                    new Transition("a").from("0").to("1"),  //0 --- a ---> 1
                    new Transition("a").from("0").to("2"),  //0 --- a ---> 2
                    new Transition("b").from("1").to("2"),
                    new Transition("").from("2").to("3"),
                    new Transition("").from("3").to("4"),
                    new Transition("").from("4").to("5"),
                    new Transition("").from("5").to("6"),
                    new Transition("").from("6").to("7"));

            //Iniciais e Finais
            basicNFA.setStart("0");
            basicNFA.setFinal("7");

            //debug e mostrando resultados
            basicNFA = basicNFA.toDfa();
            basicNFA.accepts(line);
        }


        AutomataJson nfaJson = AutomataJson.readAutomata("nfa.json");
        Automata nfa = nfaJson.toAutomata().toDfa();

        if(nfaJson.debug.exec)
        {
            for (String line: nfaJson.debug.accepts)
                nfa.accepts(line);

            for (String line: nfaJson.debug.rejects)
                nfa.rejects(line);
        }

        AutomataJson a2Json = AutomataJson.readAutomata("a2.json");
        Automata a2 = a2Json.toAutomata().toDfa();

        if(a2Json.debug.exec)
        {
            for (String line: a2Json.debug.accepts)
                a2.accepts(line);

            for (String line: a2Json.debug.rejects)
                a2.rejects(line);
        }
    }
}