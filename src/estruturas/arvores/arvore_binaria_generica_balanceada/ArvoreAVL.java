package estruturas.arvores.arvore_binaria_generica_balanceada;

import java.util.Comparator;

import estruturas.arvores.arvore_binaria_generica.ArvoreBinaria;
import estruturas.arvores.no.NoArvore;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Função que faz a rotação simples a esquerda, cada nó se move a posição direita da atual
     * @param r - Nó raiz 
     * @return Nó direito rotacionado para a esquerda
     */
    private NoArvore<T> rotacaoEsquerda(NoArvore<T> r) {
        NoArvore<T> f = r.getFilhoDireita();
        r.setFilhoDireita(f.getFilhoEsquerda());
        f.setFilhoEsquerda(r);
        return f;
    }
    
    /**
     * Função que faz a rotação simples a direita, cada nó se move a posição esquerda da atual
     * @param r - Nó raiz 
     * @return Nó esquerdo rotacionado para a direita
     */
    private NoArvore<T> rotacaoDireita(NoArvore<T> r) {
        NoArvore<T> f = r.getFilhoEsquerda();
        r.setFilhoEsquerda(f.getFilhoDireita());
        f.setFilhoDireita(r);
        return f;
    }

    /**
     * Função que faz a rotação a esquerda do nó filho a esquerda, apontando para o filho a esquerda. Após isso, se faz uma rotação a direita. 
     * @param r - Nó raiz
     */
    private NoArvore<T> rotacaoEsquerdaDireita(NoArvore<T> r) {
        r.setFilhoEsquerda(rotacaoEsquerda(r.getFilhoEsquerda()));
        return rotacaoDireita(r);
    }

    /**
     * Função que faz a rotação a direita do nó filho a direita, apontando para o filho a direita. Após isso, se faz uma rotação a esquerda. 
     * @param r - Nó raiz
     */
    private NoArvore<T> rotacaoDireitaEsquerda(NoArvore<T> r) {
        r.setFilhoDireita(rotacaoDireita(r.getFilhoDireita()));
        return rotacaoEsquerda(r);
    }

    /**
     * Alem de adicionar recursivamente, ele faz o balanceamento da árvore. 
     */
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
