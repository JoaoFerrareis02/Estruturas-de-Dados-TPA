package app;

import estruturas.grafos.grafo_lista_adjacencia.Grafo;

public class App {
    public static void main(String[] args) {
        
        Grafo<Integer> grafo = new Grafo<>();

        grafo.adicionarAresta(1, 4, 1);
        grafo.adicionarAresta(4, 2, 1);
        grafo.adicionarAresta(2, 3, 1);
        grafo.adicionarAresta(4, 3, 1);
        grafo.adicionarAresta(4, 6, 1);
        grafo.adicionarAresta(6, 5, 1);
        grafo.adicionarAresta(5, 6, 1);
        grafo.adicionarAresta(5, 4, 1);

        grafo.buscaEmLargura();

    }

}
