# AutomataLib.AutomataLib-de-Paulo
Implementação de AutomataLib.AutomataLib, segundo artigo 'Implementação de Autômatos Finitos' de Paulo Renato Kinjo.
Escrito em JAVA 8

# Exemplo 1
```
  Automata nier = new Automata("NIER");
  String line = "ab";
  
  //Estados
  nier.addState(0, 1, 2);
  
  //Transições
  nier.addTransition(0, 1, "a");
  nier.addTransition(1, 2, "b");
  
  //Iniciais e Finais
  nier.setStart(0);
  nier.setFinal(2);
  
  System.out.println("AUTOMATO RECONHECE A LINGUAGEM: " + nier.recognize(line));
```

# Exemplo 2 - Com loop no estado 1
```
  Automata a2 = new Automata("A2");
  String line = "abbbbbba";

  //Estados
  a2.addState(0, 1, 2);

  //Transições
  a2.addTransition(0, 1, "a");
  a2.addTransition(1, 1, "b");
  a2.addTransition(1, 2, "a");

  //Iniciais e Finais
  a2.setStart(0);
  a2.setFinal(2);

  System.out.println("AUTOMATO RECONHECE A LINGUAGEM: " + a2.recognize(line));
```

# Exemplo 3 - Carregando automato de json e o executando
```
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
```
- Arquivo exemplo 2b.json
```
{
  "details": {
    "name": "2B",
    "start": "0",
    "finals" : ["4", "5"]
  },
  "states": [
    {"0": [
      {"sym": "a", "dest": "1" }]
    },
    {"1": [
      {"sym": "b", "dest": "2" }]
    },
    {"2": [
      {"sym": "c", "dest": "3" }]
    },
    {"3": [
      {"sym": "d", "dest": "4" }]
    },
    {"4": [
      {"sym": "e", "dest": "5" }]
    }
  ],
  "debug": {
    "exec": true,
    "accepts": ["abcd", "abcde"],
    "rejects": ["afcd", "abcdef"]
  }
}
```






