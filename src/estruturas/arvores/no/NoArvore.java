package estruturas.arvores.no;

public class NoArvore<T> {
    
    private T valor;
    private NoArvore<T> filhoEsquerda;
    private NoArvore<T> filhoDireita;
    
    public NoArvore(T valor) {
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

    public NoArvore<T> getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoEsquerda(NoArvore<T> filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

    public NoArvore<T> getFilhoDireita() {
        return filhoDireita;
    }

    public void setFilhoDireita(NoArvore<T> filhoDireita) {
        this.filhoDireita = filhoDireita;
    }

    public int obterAltura(){
        return obterAltura(this);
    }

    private int obterAltura(NoArvore<T> r) {
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
        return obterAltura(this.filhoDireita) - obterAltura(this.filhoEsquerda);
    }

}
