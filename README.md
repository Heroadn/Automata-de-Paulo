# Automata-de-Paulo
Implementação de Automata, segundo artigo 'Implementação de Autômatos Finitos' de Paulo Renato Kinjo.
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
  Automata a2 = new Automata();
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
