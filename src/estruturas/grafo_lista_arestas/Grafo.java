package estruturas.grafo_lista_arestas;

import java.util.ArrayList;
import java.util.List;

public class Grafo<T> {

    private List<Aresta<T>> arestas = new ArrayList<>();
    private List<Vertice<T>> vertices = new ArrayList<>();

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
     * Método para adicionar uma aresta a lista de arestas.
     * @param origem - Valor do vértice de origem
     * @param destino - Valor do vértice de destino
     * @param peso - Peso da Aresta
     */

    public void adicionarAresta(T origem, T destino, float peso){
        Vertice<T> verticeOrigem;
        Vertice<T> verticeDestino;
        Aresta<T> novaAresta;
        verticeOrigem = obterVertice(origem);
        if (verticeOrigem == null) verticeOrigem = adicionarVertice(origem);
        verticeDestino = obterVertice(destino);
        if (verticeDestino == null) verticeDestino = adicionarVertice(destino);
        novaAresta = new Aresta<>(verticeOrigem, verticeDestino, peso);
        this.arestas.add(novaAresta);
    }

    /**
     * Método que imprime os vértices do grafo, semelhante com o caminhamento em nível de árvore.
     */

    public void buscaEmLargura(){
        List<Vertice<T>> marcados = new ArrayList<>();
        List<Vertice<T>> fila = new ArrayList<>();
        Vertice<T> atual = this.vertices.get(0);
        fila.add(atual);
        while (!fila.isEmpty()) {
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());
            List<Aresta<T>> destinos = this.obterDestino(atual);
            Vertice<T> proximo;
            for (int i = 0; i < destinos.size(); i++) {
                proximo = destinos.get(i).getDestino();
                if (!marcados.contains(proximo) && !fila.contains(proximo)) {
                    fila.add(proximo);
                }
            }
        }
    }

    /**
     * Método que busca pelas arestas que fazem conexão com o vértice.
     * @param v - Vértice de conexão.
     * @return Uma lista com as arestas encontradas.
     */

    private ArrayList<Aresta<T>> obterDestino(Vertice<T> v) {
        ArrayList<Aresta<T>> destinos = new ArrayList<>();
        Aresta<T> atual;
        for (int i = 0; i < this.arestas.size(); i++) {
            atual = this.arestas.get(i);
            if (atual.getOrigem().equals(v)) {
                destinos.add(atual);
            }
        }
        return destinos;
    }


    
}
