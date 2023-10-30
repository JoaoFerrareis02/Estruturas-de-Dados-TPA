package estruturas.arvores.arvore_binaria_generica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import estruturas.arvores.no.NoArvore;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected NoArvore<T> raiz = null;
    protected Comparator<T> comparador;
    private int quantidadeNos;

    public boolean primeira;
    private List<T> valoresEmOrdem;// ArrayList para armazenar valores em ordem crescente
    private int indiceAtual; // Índice do valor atual a ser retornado

    public ArvoreBinaria(Comparator<T> comp) {
        comparador = comp;
        quantidadeNos = indiceAtual = 0;
        valoresEmOrdem = new ArrayList<>();
        primeira = true;
    }

    @Override
    public void adicionar(T novoValor) {
        NoArvore<T> novoNo = new NoArvore<>(novoValor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            this.raiz = adicionarRecursivo(this.raiz, novoNo);
        }
        quantidadeNos++;
    }

    protected NoArvore<T> adicionarRecursivo(NoArvore<T> atual, NoArvore<T> novo) {
        int comp = comparador.compare(novo.getValor(), atual.getValor());
        if (comp < 0) {
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
        NoArvore<T> no = new NoArvore<>(valor);
        if (this.raiz == null) {
            return null;
        } else {
            return pesquisarRecursivo(this.raiz, no).getValor();
        }
    }

    protected NoArvore<T> pesquisarRecursivo(NoArvore<T> atual, NoArvore<T> novo) {
        int comparacao = comparador.compare(novo.getValor(), atual.getValor());
        if (comparacao == 0) {
            return atual;
        }
        if (comparacao < 0) {
            if (atual.getFilhoEsquerda() == null) {
                return null;
            } else {
                return pesquisarRecursivo(atual.getFilhoEsquerda(), novo);
            }
        } else {
            if (atual.getFilhoDireita() == null) {
                return null;
            } else {
                return pesquisarRecursivo(atual.getFilhoDireita(), novo);
            }
        }
    }

    @Override
    public T remover(T valor) {
        // Chama a função recursiva.
        raiz = removerRecursivo(raiz, valor);
        // Subtrai da quantidade de nós.
        quantidadeNos--;
        // Retorna o valor removido.
        return valor;
    }

    protected NoArvore<T> removerRecursivo(NoArvore<T> atual, T valor) {
        // Caso o nó atual for nula, retorne nulo.
        if (atual == null) {
            return null;
        }

        // Faz a comparacao entre o valor e o valor do nó atual
        int comparacao = comparador.compare(valor, atual.getValor());

        // Se a comparacao for menor que nulo, procure no nó filho a esquerda.
        if (comparacao < 0) {
            atual.setFilhoEsquerda(removerRecursivo(atual.getFilhoEsquerda(), valor));
        }
        // Se for maior que zero, procure no nó filho a direita.
        else if (comparacao > 0) {
            atual.setFilhoDireita(removerRecursivo(atual.getFilhoDireita(), valor));
        }
        // Caso encontrou (comparacao = 0)
        else {
            // Nó sem folhas
            if (atual.getFilhoEsquerda() == null && atual.getFilhoDireita() == null) {
                return null;
            } else if (atual.getFilhoEsquerda() == null) {
                // Nó com uma folha (nó filho a direita)
                return atual.getFilhoDireita();
            } else if (atual.getFilhoDireita() == null) {
                // Nó com uma folha (nó filho a esquerda)
                return atual.getFilhoEsquerda();
            } else {
                // Nó com duas folhas
                // Procure o menor valor da sub-arvore
                T menorValor = encontrarValorMinimo(atual.getFilhoDireita()).getValor();
                atual.setValor(menorValor);
                // Delete o nó com o menor valor da subárvore a direita.
                atual.setFilhoDireita(removerRecursivo(atual.getFilhoDireita(), menorValor));
            }
        }

        return atual;
    }

    private NoArvore<T> encontrarValorMinimo(NoArvore<T> no) {
        // Enquanto o no filho a esquerda não for nulo, chame a função de encontrar o
        // valor mínimo.
        if (no.getFilhoEsquerda() != null) {
            return encontrarValorMinimo(no.getFilhoEsquerda());
        }
        return no;
    }

    @Override
    public int altura() {
        return this.raiz.obterAltura(); // Retorna a altura calculada da árvore.
    }

    @Override
    public int quantidadeNos() {
        return this.quantidadeNos; // Retorna a quantidade de nós.
    }

    @Override
    public String caminharEmNivel() {
        StringBuilder resultado = new StringBuilder("[");
        if (raiz == null) {
            resultado.append("Vazio]");
            return resultado.toString();
        }

        List<NoArvore<T>> nivelAtual = new ArrayList<>();
        nivelAtual.add(raiz);

        while (!nivelAtual.isEmpty()) {
            List<NoArvore<T>> proximoNivel = new ArrayList<>();

            for (NoArvore<T> no : nivelAtual) {
                resultado.append(no.getValor()).append(", ");

                if (no.getFilhoEsquerda() != null) {
                    proximoNivel.add(no.getFilhoEsquerda());
                }

                if (no.getFilhoDireita() != null) {
                    proximoNivel.add(no.getFilhoDireita());
                }
            }

            nivelAtual = proximoNivel;
        }

        resultado.setLength(resultado.length() - 2); // Remover a vírgula e espaço em branco extras no final.
        resultado.append("]");
        return resultado.toString();
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder resultado = new StringBuilder("[");
        if (raiz == null) {
            resultado.append("Vazio]"); // Se a arvore não existir, printa vazio tambem.
        } else {
            caminharEmOrdemRecursivo(raiz, resultado);
            if (resultado.length() > 1) {
                resultado.setLength(resultado.length() - 2); // Remove a última vírgula e espaço em branco.
            }
            resultado.append("]");
        }
        return resultado.toString();
    }

    private void caminharEmOrdemRecursivo(NoArvore<T> no, StringBuilder resultado) {
        if (no != null) {
            caminharEmOrdemRecursivo(no.getFilhoEsquerda(), resultado);
            resultado.append(no.getValor());
            resultado.append(", ");
            caminharEmOrdemRecursivo(no.getFilhoDireita(), resultado);
        }
    }

    @Override
    public T obterProximo() {
        if (raiz == null) {
            return null;
        }
        if (primeira) {
            primeira = false; // Marque como não a primeira vez
            valoresEmOrdem.clear(); // Limpe o ArrayList
            encontrarValoresEmOrdem(raiz); // Encontre os valores em ordem crescente
            indiceAtual = 0; // Inicie a partir do primeiro valor
        }

        if (indiceAtual < valoresEmOrdem.size()) {
            T valor = valoresEmOrdem.get(indiceAtual);
            indiceAtual++; // Atualize o índice para o próximo valor
            return valor;
        }

        return null; // Não há mais elementos a serem obtidos.
    }

    private void encontrarValoresEmOrdem(NoArvore<T> no) {
        if (no != null) {
            encontrarValoresEmOrdem(no.getFilhoEsquerda());
            valoresEmOrdem.add(no.getValor());
            encontrarValoresEmOrdem(no.getFilhoDireita());
        }
    }

    @Override
    public void reiniciarNavegacao() {
        if (!primeira) {
            valoresEmOrdem.clear(); // Limpe o ArrayList
            primeira = true;
            indiceAtual = 0; // Inicie a partir do primeiro valor
        }
    }

}
