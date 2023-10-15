package structures.generic_binary_tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import structures.nodes.GenericTreeNode;

public class GenericBinaryTree<T> implements IGenericBinaryTree<T> {
    
    private GenericTreeNode<T> raiz = null;

    private GenericTreeNode<T> atual;

    private List<GenericTreeNode<T>> listaNodes; 

    private int quantidade = 0;

    private Comparator<T> comparator;

    public GenericBinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void adicionar(T novoValor) {
        GenericTreeNode<T> novoNo =  new GenericTreeNode<>(novoValor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            this.raiz = adicionarRecursivo(this.raiz, novoNo);
        }
        this.quantidade++;
    }

    protected GenericTreeNode<T> adicionarRecursivo(GenericTreeNode<T> atual, GenericTreeNode<T> novo) {
        if (comparator.compare(novo.getValor(), atual.getValor()) < 0) {
            if (atual.getFilhoEsquerda() == null) {
                atual.setFilhoEsquerda(novo);
            } else {
                atual.setFilhoEsquerda(adicionarRecursivo(atual.getFilhoEsquerda(), novo));
            }
        } else {
            if (atual.getFilhoDireita() == null) {
                atual.setFilhoDireita(novo);
            } else {
                atual.setFilhoDireita(adicionarRecursivo(atual.getFilhoDireita(), novo));
            }
        }
        return atual;
    }

    @Override
    public T pesquisar(T valor) {
        return pesquisarRecursivo(this.raiz, valor);
    }

    private T pesquisarRecursivo(GenericTreeNode<T> raiz, T valor) {
        if (raiz == null) {
            return null;
        }
        int comp = comparator.compare(valor, raiz.getValor());
        if (comp < 0) {
            return pesquisarRecursivo(raiz.getFilhoEsquerda(), valor);
        } else if (comp > 0) {
            return pesquisarRecursivo(raiz.getFilhoDireita(), valor);
        } else {
            return raiz.getValor();
        }
    }

    @Override
    public T remover(T valor) {
        this.raiz = removerRecursivo(this.raiz, valor); 
        this.quantidade--;
        return valor;
    }

    private GenericTreeNode<T> removerRecursivo(GenericTreeNode<T> raiz, T valor) {
        if (raiz.getFilhoEsquerda() == null) {
            return null;
        }
        int comp = comparator.compare(valor, raiz.getValor());
        if (comp == 0) {
            if (raiz.getFilhoEsquerda() == null) {
                return raiz.getFilhoDireita();
            } else if (raiz.getFilhoDireita() == null){
                return raiz.getFilhoEsquerda();
            }
            GenericTreeNode<T> valorMinimo = encontrarValorMinimo(raiz.getFilhoDireita());
            raiz.setValor(valorMinimo.getValor());
            raiz.setFilhoDireita(removerRecursivo(raiz.getFilhoDireita(), valorMinimo.getValor()));
        } else if ( comp < 0) {
            raiz.setFilhoEsquerda(removerRecursivo(raiz.getFilhoEsquerda(), valor));
        } else {
            raiz.setFilhoDireita(removerRecursivo(raiz.getFilhoDireita(), valor));
        }
        return raiz;
    }

    @Override
    public int altura() {
        return alturaRecursivo(this.raiz);
    }

    private int alturaRecursivo(GenericTreeNode<T> raiz) {
        if (raiz == null) {
            return 0;
        }
        int alturaEsquerda = alturaRecursivo(raiz.getFilhoEsquerda());
        int alturaDireita = alturaRecursivo(raiz.getFilhoDireita());
        return 1 + Math.max(alturaDireita,alturaEsquerda);
    }

    @Override
    public int quantidadeNos() {
        return this.quantidade;
    }

    @Override
    public String caminharEmNivel() {
        StringBuilder sb = new StringBuilder();
        if (this.raiz == null) {
            return "[]";
        }
        Queue<GenericTreeNode<T>> listaDeNos = new LinkedList<>();
        listaDeNos.add(this.raiz);
        sb.append("[\n");
        while (!listaDeNos.isEmpty()) {
            GenericTreeNode<T> node = listaDeNos.poll();
            sb.append(node.getValor() + "\n");
            if (node.getFilhoEsquerda() != null) {
                listaDeNos.add(node.getFilhoEsquerda());
            }
            if (node.getFilhoDireita() != null) {
                listaDeNos.add(node.getFilhoDireita());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String caminharEmOrdem() {

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        caminharEmOrdem(this.raiz, sb);
        sb.append("]");
        return sb.toString();
        
    }

    private void caminharEmOrdem(GenericTreeNode<T> raiz, StringBuilder sb) {
        
        if (raiz == null) {
            return;
        }

        caminharEmOrdem(raiz.getFilhoEsquerda(), sb);
        sb.append(raiz.getValor() + "\n");
        caminharEmOrdem(raiz.getFilhoDireita(), sb);

    }

    @Override
    public T obterProximo() {
        
        if (this.atual == null && this.listaNodes.isEmpty()) {
            // Primeira chamada, inicialize a iteração
            this.atual = encontrarValorMinimo(raiz);
            this.listaNodes = new ArrayList<>();
            empilharCaminhoParaMenorValor(this.atual);
        } else if (!this.listaNodes.isEmpty()) {
            // Já em iteração, obtenha o próximo valor em ordem crescente
            this.atual = this.listaNodes.remove(-1);
            empilharCaminhoParaMenorValor(this.atual.getFilhoDireita());
        } else {
            // Iteração concluída, retorne null
            this.atual = null;
        }

        if (this.atual != null) {
            return this.atual.getValor();
        }

        return null;

    }

    private GenericTreeNode<T> encontrarValorMinimo(GenericTreeNode<T> no) {
        
        while (no.getFilhoEsquerda() != null) {
            no = no.getFilhoEsquerda();
        }
        
        return no;
    
    }

    private void empilharCaminhoParaMenorValor(GenericTreeNode<T> no) {
        while (no != null) {
            this.listaNodes.add(no);
            no = no.getFilhoEsquerda();
        }
    }

    @Override
    public void reiniciarNavegacao() {
        this.atual = null;
        this.listaNodes = null;
    }



}
