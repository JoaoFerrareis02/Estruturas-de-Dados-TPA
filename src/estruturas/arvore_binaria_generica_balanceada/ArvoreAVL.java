package estruturas.arvore_binaria_generica_balanceada;

import java.util.Comparator;

import estruturas.arvore_binaria_generica.ArvoreBinaria;
import estruturas.no.NoArvore;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    private NoArvore<T> rotacaoEsquerda(NoArvore<T> r) {
        NoArvore<T> f = r.getFilhoDireita();
        r.setFilhoDireita(f.getFilhoEsquerda());
        f.setFilhoEsquerda(r);
        return f;
    }
    
    private NoArvore<T> rotacaoDireita(NoArvore<T> r) {
        NoArvore<T> f = r.getFilhoEsquerda();
        r.setFilhoEsquerda(f.getFilhoDireita());
        f.setFilhoDireita(r);
        return f;
    }

    private NoArvore<T> rotacaoEsquerdaDireita(NoArvore<T> r) {
        r.setFilhoEsquerda(rotacaoEsquerda(r.getFilhoDireita()));
        return rotacaoDireita(r);
    }

    private NoArvore<T> rotacaoDireitaEsquerda(NoArvore<T> r) {
        r.setFilhoDireita(rotacaoDireita(r.getFilhoDireita()));
        return rotacaoEsquerda(r);
    }

    @Override
    protected NoArvore<T> adicionarRecursivo(NoArvore<T> raiz, NoArvore<T> novo) {
        raiz = super.adicionarRecursivo(raiz, novo);
        if (raiz.fatorBalanceamento() > 1) {
            if (raiz.getFilhoDireita().fatorBalanceamento() > 0) {
                raiz = this.rotacaoEsquerda(raiz);
            } else {
                raiz = this.rotacaoDireitaEsquerda(raiz);
            }
        } else if (raiz.fatorBalanceamento() < -1) {
            if (raiz.getFilhoEsquerda().fatorBalanceamento() < 0) {
                raiz = this.rotacaoDireita(raiz);
            } else {
                raiz = this.rotacaoEsquerdaDireita(raiz);
            }
        }
        return raiz;
    }

    

}
