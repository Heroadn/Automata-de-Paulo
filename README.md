# Automata-de-Paulo
Implementação de Automata, segundo artigo 'Implementação de Autômatos Finitos' de Paulo Renato Kinjo.
Escrito em JAVA

# Exemplo
```
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
```
