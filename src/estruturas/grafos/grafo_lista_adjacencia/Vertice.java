package estruturas.grafos.grafo_lista_adjacencia;

import java.util.ArrayList;
import java.util.List;

public class Vertice<T> {
    
    private T valor;
    private List<Aresta<T>> destinos;
    
    
    
    public Vertice(T valor) {
        this.valor = valor;
        this.destinos = new ArrayList<>();
    }
   
    public T getValor() {
        return valor;
    }
    
    public void setValor(T valor) {
        this.valor = valor;
    }
    
    public List<Aresta<T>> getDestinos() {
        return destinos;
    }

    public void adicionarDestino(Aresta<T> a){
        destinos.add(a);
    }  

}
