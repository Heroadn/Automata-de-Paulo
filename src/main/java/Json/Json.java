package Json;

import AutomataLib.Automata;
import AutomataLib.Transition;

public class Json {

    public static void main(String[] args){
        AutomataJson json = AutomataJson.readAutomata("2b.json");
        Automata automata = json.toAutomata();

        if(json.debug.exec)
        {
            for (String line: json.debug.accepts) {
                automata.recognize(line, true);
            }

            for (String line: json.debug.rejects) {
                automata.recognize(line, true);
            }
        }
    }
}
