package Json;

import AutomataLib.Automata;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AutomataJson {
    public Details details;
    public State states[]; //tmp container for parsing
    public Map<String, Transition[]> transitions;

    public Debug debug;
    public static AutomataJson readAutomata(String path)
    {
        ObjectMapper mapper = new ObjectMapper();
        AutomataJson automataJson = null;

        try {
            automataJson = mapper.readValue(new File(path), AutomataJson.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + ": " + path);
        }

        return automataJson;
    }

    public Automata toAutomata()
    {
        Automata automata = new Automata(this.details.name);
        automata.addState(this.details.start);
        automata.addState(this.details.finals);

        for (State state: this.states) {
            state.transitions.forEach((orig, transitions) -> {

                //adicionando estado
                automata.addState(orig);

                for (Transition transition : transitions) {
                    automata.addState(transition.dest);
                    automata.addTransition(new AutomataLib.Transition(
                            orig,
                            transition.dest,
                            transition.sym));
                }
            });
        }

        //estados iniciais e finais
        automata.setStart(this.details.start)
                .setFinal(this.details.finals);
        return automata;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NAME: ")
               .append(details.name)
               .append("\n");

        for (State state: this.states) {
            state.transitions.forEach((orig, transitions) -> {
                System.out.println();
                builder.append("ORIGEN: ")
                       .append(orig)
                       .append("\n");
                for (Transition transition : transitions) {
                    builder.append("    ")
                           .append(transition)
                           .append("\n");
                }
            });
        }

        return builder.toString();
    }
}
