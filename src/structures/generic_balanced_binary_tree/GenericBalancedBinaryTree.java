package structures.generic_balanced_binary_tree;

import java.util.Comparator;

import structures.generic_binary_tree.GenericBinaryTree;
import structures.nodes.GenericTreeNode;

public class GenericBalancedBinaryTree<T> extends GenericBinaryTree<T> {

    public GenericBalancedBinaryTree(Comparator<T> comparator) {
        super(comparator);
    }

    private GenericTreeNode<T> rotacaoEsquerda(GenericTreeNode<T> r) {
        GenericTreeNode<T> f = r.getFilhoDireita();
        r.setFilhoDireita(f.getFilhoEsquerda());
        f.setFilhoEsquerda(r);
        return f;
    }
    
    private GenericTreeNode<T> rotacaoDireita(GenericTreeNode<T> r) {
        GenericTreeNode<T> f = r.getFilhoEsquerda();
        r.setFilhoEsquerda(f.getFilhoDireita());
        f.setFilhoDireita(r);
        return f;
    }

    private GenericTreeNode<T> rotacaoEsquerdaDireita(GenericTreeNode<T> r) {
        r.setFilhoEsquerda(rotacaoEsquerda(r.getFilhoDireita()));
        return rotacaoDireita(r);
    }

    private GenericTreeNode<T> rotacaoDireitaEsquerda(GenericTreeNode<T> r) {
        r.setFilhoDireita(rotacaoDireita(r.getFilhoDireita()));
        return rotacaoEsquerda(r);
    }

    @Override
    protected GenericTreeNode<T> adicionarRecursivo(GenericTreeNode<T> raiz, GenericTreeNode<T> novo) {
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
