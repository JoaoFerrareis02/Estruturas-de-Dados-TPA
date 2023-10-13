package structures.node;

public class GenericNode<T> {
    
    private T valor;
    private GenericNode<T> prox;
    
    public GenericNode(T valor) {
        this.valor = valor;
        this.prox = null;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public GenericNode<T> getProx() {
        return prox;
    }

    public void setProx(GenericNode<T> prox) {
        this.prox = prox;
    }

}
