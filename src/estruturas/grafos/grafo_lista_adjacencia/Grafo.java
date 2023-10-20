package estruturas.grafos.grafo_lista_adjacencia;

import java.util.ArrayList;
import java.util.List;

public class Grafo<T> {
    
    private List<Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    public List<Vertice<T>> getVertices() {
        return vertices;
    }

    /**
     * Método para adicionar um vértice na lista de vértices.
     * @param valor
     * @return O novo vértice adicionado.
     */
    public Vertice<T> adicionarVertice(T valor){
        Vertice<T> novo = new Vertice<>(valor);
        this.vertices.add(novo);
        return novo;
    }

    /** Método para verificar se o vértice com o valor solicitado existe. 
     * @param valor
     * @return O vértice com o valor solicitado se for encontrado, caso contrário retornara null.
     */

    private Vertice<T> obterVertice(T valor){
        Vertice<T> v;
        for (int i = 0; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Método para adicionar uma aresta a lista de arestas do vértice de origem.
     * @param origem - Valor do vértice de origem
     * @param destino - Valor do vértice de destino
     * @param peso - Peso da Aresta
     */

     public void adicionarAresta(T origem, T destino, float peso){
        Vertice<T> verticeOrigem;
        Vertice<T> verticeDestino;
        verticeOrigem = obterVertice(origem);
        if (verticeOrigem == null) verticeOrigem = adicionarVertice(origem);
        verticeDestino = obterVertice(destino);
        if (verticeDestino == null) verticeDestino = adicionarVertice(destino);
        verticeOrigem.adicionarDestino(new Aresta<>(verticeDestino, peso));
    }

    /**
     * Método que imprime os vértices do grafo, em um busca em Largura.
     */
    public void buscaEmLargura(){
        List<Vertice<T>> marcados = new ArrayList<>();
        List<Vertice<T>> fila = new ArrayList<>();
        Vertice<T> atual = this.vertices.get(0);
        fila.add(atual);
        System.out.println("Busca em Largura a partir do vértice: " + atual.getValor());
        while (!fila.isEmpty()) {
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());
            List<Aresta<T>> destinos = atual.getDestinos();   
            Vertice<T> proximo;
            for (int i = 0; i < destinos.size(); i++) {
                proximo = destinos.get(i).getDestino();
                if (!marcados.contains(proximo) && !fila.contains(proximo)) {
                    fila.add(proximo);
                }
            }
        }
    }    

}
