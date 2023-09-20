//Implementação de Automato, baseado no artigo 'Implementação de Autômatos Finitos'
//De Autoria de 'Paulo Renato Kinjo', foram usadas suas interfaces de 'Transition, Automata e State'
//foram implementadas com algumas modificações

public class Main {
    public static void main(String[] args) {
        {
            Automata nier = new Automata();
            String line = "ab";

            //Estados
            nier.addState(0);
            nier.addState(1);
            nier.addState(2);

            //Transições
            nier.addTransition(0, 1, "a");
            nier.addTransition(1, 2, "b");

            //Iniciais e Finais
            nier.setStartState(0);
            nier.setFinalState(2);

            System.out.println("AUTOMATO RECONHECE A LINGUAGEM: " + nier.recognize(line));
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

            System.out.println("AUTOMATO RECONHECE A LINGUAGEM: " + a2.recognize(line));
        }
    }
}