package structures.nodes;

public class GenericTreeNode<T> {
    
    private T valor;
    private GenericTreeNode<T> filhoEsquerda;
    private GenericTreeNode<T> filhoDireita;
    
    public GenericTreeNode(T valor) {
        this.valor = valor;
        this.filhoEsquerda = null;
        this.filhoDireita = null;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public GenericTreeNode<T> getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoEsquerda(GenericTreeNode<T> filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

    public GenericTreeNode<T> getFilhoDireita() {
        return filhoDireita;
    }

    public void setFilhoDireita(GenericTreeNode<T> filhoDireita) {
        this.filhoDireita = filhoDireita;
    }

    public int obterAltura(){
        return obterAltura(this);
    }

    private int obterAltura(GenericTreeNode<T> r) {
        if (r == null) {
            return -1;
        }
        else {
            int hd = obterAltura(r.getFilhoDireita());
            int he = obterAltura(r.getFilhoEsquerda());
            if (hd > he) {
                return hd + 1;
            } else {
                return he + 1;
            }
        }
    }

    public int fatorBalanceamento(){
        return obterAltura(this.filhoDireita) + obterAltura(this.filhoEsquerda);
    }

}
