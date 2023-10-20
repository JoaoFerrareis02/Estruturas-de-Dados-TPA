package app;

import estruturas.grafo_lista_arestas.Grafo;

public class App {
    public static void main(String[] args) {
        
        Grafo<Integer> grafo = new Grafo<>();

        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);

        grafo.adicionarAresta(1, 2, 2);
        grafo.adicionarAresta(2, 3, 1);

        grafo.buscaEmLargura();

    }

}
