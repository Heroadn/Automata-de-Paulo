# AutomataLib.AutomataLib-de-Paulo
Implementação de AutomataLib.AutomataLib, segundo artigo 'Implementação de Autômatos Finitos' de Paulo Renato Kinjo.
Escrito em JAVA 8

# Exemplo 1
```
  AutomataLib.AutomataLib nier = new AutomataLib.AutomataLib();
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
```

# Exemplo 2 - Com loop no estado 1
```
  AutomataLib.AutomataLib a2 = new AutomataLib.AutomataLib();
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
```
