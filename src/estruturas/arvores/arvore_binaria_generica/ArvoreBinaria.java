package estruturas.arvores.arvore_binaria_generica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import estruturas.arvores.no.NoArvore;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected NoArvore<T> raiz = null;

    protected Comparator<T> comparador;

    protected NoArvore<T> atual = null;

    private ArrayList<NoArvore<T>> pilhaNavegacao;

    private boolean primeiraChamada = true;

    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
        pilhaNavegacao = new ArrayList<>();
    }

    @Override
    public void adicionar(T novoValor) {
        NoArvore<T> novoNo = new NoArvore<>(novoValor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            this.raiz = adicionarRecursivo(this.raiz, novoNo);
        }
    }

    protected NoArvore<T> adicionarRecursivo(NoArvore<T> atual, NoArvore<T> novo) {
        if (comparador.compare(novo.getValor(), atual.getValor()) < 0) {
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
        if (raiz == null) {
            return null;
        }
        atual = raiz;
        while (atual != null) {
            int comparacao = comparador.compare(valor, atual.getValor());
            if (comparacao == 0) {
                return atual.getValor();
            } else if (comparacao < 0) {
                atual = atual.getFilhoEsquerda();
            } else {
                atual = atual.getFilhoDireita();
            }
        }
        return null;
    }

    @Override
    public T remover(T valor) {
        raiz = removerRecursivo(raiz, valor);
        return valor;
    }

    protected NoArvore<T> removerRecursivo(NoArvore<T> no, T valor) {
        if (no == null) {
            return null;
        }
        int comparacao = comparador.compare(valor, no.getValor());
        if (comparacao < 0) {
            no.setFilhoEsquerda(removerRecursivo(no.getFilhoEsquerda(), valor));
        } else if (comparacao > 0) {
            no.setFilhoDireita(removerRecursivo(no.getFilhoDireita(), valor));
        } else {
            if (no.getFilhoEsquerda() == null && no.getFilhoDireita() == null) {
                return null;
            } else if (no.getFilhoEsquerda() == null) {
                return no.getFilhoDireita();
            } else if (no.getFilhoDireita() == null) {
                return no.getFilhoEsquerda();
            } else {
                NoArvore<T> substituto = getSubstituto(no);
                no.setValor(substituto.getValor());
                no.setFilhoEsquerda(removerRecursivo(no.getFilhoEsquerda(), substituto.getValor()));
            }
        }
        return no;
    }

    private NoArvore<T> getSubstituto(NoArvore<T> no) {
        NoArvore<T> substituto = no.getFilhoEsquerda();
        while (substituto.getFilhoDireita() != null) {
            substituto = substituto.getFilhoDireita();
        }
        return substituto;
    }

    @Override
    public int altura() {
        return alturaRecursivo(raiz);
    }

    private int alturaRecursivo(NoArvore<T> no) {
        if (no == null) {
            return -1;
        }
        int alturaEsquerda = alturaRecursivo(no.getFilhoEsquerda());
        int alturaDireita = alturaRecursivo(no.getFilhoDireita());
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    @Override
    public int quantidadeNos() {
        return contarNos(raiz);
    }

    private int contarNos(NoArvore<T> no) {
        if (no == null) {
            return 0;
        }
        int nosNaEsquerda = contarNos(no.getFilhoEsquerda());
        int nosNaDireita = contarNos(no.getFilhoDireita());
        return 1 + nosNaEsquerda + nosNaDireita;
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null) {
            return "[Vazio]";
        }
        StringBuilder resultado = new StringBuilder("[");
        Queue<NoArvore<T>> queue = new LinkedList<>();
        queue.add(raiz);
        while (!queue.isEmpty()) {
            NoArvore<T> no = queue.poll();
            resultado.append(no.getValor().toString());
            if (no.getFilhoEsquerda() != null) {
                queue.add(no.getFilhoEsquerda());
            }
            if (no.getFilhoDireita() != null) {
                queue.add(no.getFilhoDireita());
            }
            if (!queue.isEmpty()) {
                resultado.append("\n");
            }
        }
        resultado.append("]");
        return resultado.toString();
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder resultado = new StringBuilder("[");
        if (raiz == null) {
            resultado.append("Vazio]");
        } else {
            reiniciarNavegacao();
            atual = raiz;
            while (atual != null || !pilhaNavegacao.isEmpty()) {
                while (atual != null) {
                    pilhaNavegacao.add(atual);
                    atual = atual.getFilhoEsquerda();
                }
                int tamanho = pilhaNavegacao.size();
                atual = pilhaNavegacao.remove(tamanho - 1);
                resultado.append(atual.getValor().toString()).append("\n");
                atual = atual.getFilhoDireita();
            }
        }
        resultado.append("]");
        return resultado.toString();
    }

    @Override
    public T obterProximo() {
        if (raiz == null) {
            return null;
        }
        if (primeiraChamada) {
            primeiraChamada = false;
            atual = raiz;
            while (atual != null) {
                pilhaNavegacao.add(atual);
                atual = atual.getFilhoEsquerda();
            }
        } else {
            if (!pilhaNavegacao.isEmpty()) {
                atual = pilhaNavegacao.remove(pilhaNavegacao.size() -1);
                NoArvore<T> temp = atual.getFilhoDireita();
                while (temp != null) {
                    pilhaNavegacao.add(temp);
                    temp = temp.getFilhoEsquerda();
                }
            }
        }
        return atual != null ? atual.getValor() : null;
    }

    @Override
    public void reiniciarNavegacao() {
        if (pilhaNavegacao != null) {
            primeiraChamada = true;
            pilhaNavegacao.clear();
        }
    }

}
