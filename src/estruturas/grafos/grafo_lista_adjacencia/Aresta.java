package estruturas.grafos.grafo_lista_adjacencia;

public class Aresta<T> {
    
    private Vertice<T> destino;
    private float peso;
    
    public Aresta(Vertice<T> destino, float peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice<T> getDestino() {
        return destino;
    }
    
    public void setDestino(Vertice<T> destino) {
        this.destino = destino;
    }
    
    public float getPeso() {
        return peso;
    }
    
    public void setPeso(float peso) {
        this.peso = peso;
    }
    
}
