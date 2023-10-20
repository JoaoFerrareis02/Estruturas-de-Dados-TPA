package estruturas.no;

public class NoGenerico<T> {
    
    private T valor;
    private NoGenerico<T> prox;
    
    public NoGenerico(T valor) {
        this.valor = valor;
        this.prox = null;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NoGenerico<T> getProx() {
        return prox;
    }

    public void setProx(NoGenerico<T> prox) {
        this.prox = prox;
    }

}
