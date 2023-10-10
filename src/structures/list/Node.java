package structures.list;

public class Node {
    
    private Object valor;
    private Node prox;

    public Node (Object valor){
        this.valor = valor;
        this.prox = null;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Node getProx() {
        return prox;
    }

    public void setProx(Node prox) {
        this.prox = prox;
    }   

}
