package Json;

import AutomataLib.Automata;

public class Json {

    public static void main(String[] args){
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

        AutomataJson marianJson = AutomataJson.readAutomata("marian.json");
        Automata marian = json.toAutomata();
        System.out.println(marian);
    }
}
