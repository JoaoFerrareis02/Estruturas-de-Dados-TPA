package estruturas.arvores.arvore_binaria_generica;

import java.util.ArrayList;
import java.util.Comparator;
import estruturas.arvores.no.NoArvore;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected NoArvore<T> raiz = null;
        protected Comparator<T> comparador;

        protected NoArvore<T> atual = null;
        private ArrayList<NoArvore<T>> pilhaNavegacao = new ArrayList<>();
        private boolean primeiraChamada = true;

        public ArvoreBinaria(Comparator<T> comp) {
            comparador = comp;
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
    
        /**
         * Função de adicionar elemento na árvore binária, retornando o elemento atual adicionado.
         * @param atual - Elemento atual.
         * @param novo - Elemento a ser adicionado
         */
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

            if (raiz == null) { // A árvore está vazia, não há nada.
                return null;
            }

            atual = raiz;

            while (atual != null) {
                int comparacao = comparador.compare(valor, atual.getValor());

                /*
                 * Seguinte, se o valor atual for igual a valor, então comparacao == 0.
                 * Se for menor, comparacao < 0. Se for maior, comparacao > 0.
                 * Ele vai percorrer a árvore até achar o valor correspondente e retornar.
                 * Se não achar, retorna null.
                 */

                if (comparacao == 0) {
                    return atual.getValor(); // Valor encontrado na árvore.
                } else if (comparacao < 0) {
                    atual = atual.getFilhoEsquerda(); // O valor pode estar na subárvore esquerda.
                } else {
                    atual = atual.getFilhoDireita(); // O valor pode estar na subárvore direita.
                }
            }

            return null; // Valor não encontrado na árvore.
        }

        @Override
        public T remover(T valor) {

            if (raiz == null) {
                return null;
            }

            NoArvore<T> pai = null;
            atual = raiz;

            while (atual != null) {
                int comparacao = comparador.compare(valor, atual.getValor());

                /*
                 * Seguinte, aqui é o mesmo esquema que pesquisar, a arvore vai começar a ser
                 * varrida até encontrar o valor. Uma vez encontrado a var atual vai receber
                 * o valor e a var pai vai referenciar o nó pai de atual. Então quando,
                 * comparacao == 0 ele termina o loop de busca já que atual vai ser valor.
                 */
                if (comparacao == 0) {
                    break;
                } else if (comparacao < 0) {
                    pai = atual;
                    atual = atual.getFilhoEsquerda();
                } else {
                    pai = atual;
                    atual = atual.getFilhoDireita();
                }
            }

            if (atual == null) {
                return null;
            }

            // Fim da pesquisa. Agora temos que remover o nó, mas caso ele tenha filhos?
            // Caso 1: Se o nó atual for uma folha (não tem filhos), simplesmente remova-o.
            if (atual.getFilhoEsquerda() == null && atual.getFilhoDireita() == null) {
                if (pai == null) { //Se pai é nulo, então atual é raiz.
                    raiz = null;
                } else if (atual == pai.getFilhoEsquerda()) { //Se não for, verifica se o nó atual é o filho esquerda ou direita do pai (talvez dê para melhorar já dizendo qual lado do pai é atual. PENSEM!!)
                    pai.setFilhoEsquerda(null);
                } else if (atual == pai.getFilhoDireita()) {
                    pai.setFilhoDireita(null);
                }
                return atual.getValor();
            }

            // Caso 2: Se o nó atual tem um filho (esquerda ou direita).
            // Se for filho a esquerda:
            if (atual.getFilhoEsquerda() != null && atual.getFilhoDireita() == null) {
                if (pai == null) { //Se pai é nulo, então atual é raiz e tem filho a esquerda.
                    raiz = atual.getFilhoEsquerda();
                } else if (atual == pai.getFilhoEsquerda()) { //Se não for, verifica se o nó atual é o filho esquerda ou direita do pai.
                    pai.setFilhoEsquerda(atual.getFilhoEsquerda());
                } else {
                    pai.setFilhoDireita(atual.getFilhoEsquerda());
                }
                return atual.getValor();
            }

            // Se for filho a direita:
            if (atual.getFilhoDireita() != null && atual.getFilhoEsquerda() == null) {
                if (pai == null) {
                    raiz = atual.getFilhoDireita();
                } else if (atual == pai.getFilhoEsquerda()) {
                    pai.setFilhoEsquerda(atual.getFilhoDireita());
                } else {
                    pai.setFilhoDireita(atual.getFilhoDireita());
                }
                return atual.getValor();
            }

            /*
             * Caso 3: O nó atual tem dois filhos.
             * Vamos à lógica:
             * Para remover um nó com dois filhos, precisamos encontrar um substituto.
             * Seja escolhendo o lado da direita ou da esquerda.
             * Vamos à subárvore à esquerda do nó atual para encontrar o substituto.
             * O substituto será o valor mais à direita nessa subárvore, pois é o próximo
             * valor mais próximo e maior que pode substituir o nó atual.
             * Salvamos o pai do substituto, pois o substituto pode ter um filho à esquerda.
             * Caso tenha um filho à esquerda, o pai do substituto precisa referenciar
             * esse filho como seu filho à direita.
             * Após a substituição, atualizamos a estrutura da árvore conforme necessário
             * e retornamos o valor do substituto.
             */
            if (atual.getFilhoEsquerda() != null && atual.getFilhoDireita() != null) {

                NoArvore<T> paiSubstituto = atual;
                NoArvore<T> substituto = atual.getFilhoEsquerda();

                while (substituto.getFilhoDireita() != null) {
                    paiSubstituto = substituto;
                    substituto = substituto.getFilhoDireita();
                }

                atual.setValor(substituto.getValor()); // Agora substituto é o nó que deve substituir o nó atual.

                if (paiSubstituto != atual) { // Verificar se o substituto tem filho à esquerda.
                    paiSubstituto.setFilhoDireita(substituto.getFilhoEsquerda());
                } else {
                    atual.setFilhoEsquerda(substituto.getFilhoEsquerda()); // Caso especial: o substituto é filho esquerda do nó atual.
                }
                return substituto.getValor();
            }
            return null;
        }


        @Override
        public int altura() {
            return this.raiz.obterAltura(); // Retorna a altura calculada da árvore.
        }

        @Override
        public int quantidadeNos() {
            return contarNos(raiz);
        }

        private int contarNos(NoArvore<T> no) { //Ele vai atrás de todos os nós da arvore e soma. Por incrivel que pareça isso não dá erro e a recursão da altura sim, vai saber.
            if (no == null) {
                return 0; // Caso base: nó nulo, significa que a raiz é nula então temos 0 nós.
            }

            int nosNaEsquerda = contarNos(no.getFilhoEsquerda());
            int nosNaDireita = contarNos(no.getFilhoDireita());

            return 1 + nosNaEsquerda + nosNaDireita;
        }

        @Override
        public String caminharEmNivel() {
            StringBuilder resultado = new StringBuilder("["); // Inicializa um StringBuilder para construir em nível.
            if (raiz == null) {
                resultado.append("Vazio]"); //Se a arvore não existir, printa vazio.
            } else {
                ArrayList<NoArvore<T>> nivelAtual = new ArrayList<>(); // Cria uma lista para armazenar os nós do nível atual e inicia com a raiz.
                nivelAtual.add(raiz);

                while (!nivelAtual.isEmpty()) { // Enquanto houver nós no nível atual, continue
                    ArrayList<NoArvore<T>> proximoNivel = new ArrayList<>(); // Cria uma lista para armazenar os nós do próximo nível.

                    for (NoArvore<T> no : nivelAtual) { // Itera pelos nós do nível atual.
                        resultado.append(no.getValor().toString()); // Adiciona o valor do nó atual à representação em string.
                        /*
                         * Se houver um filho à esquerda, adiciona ao próximo nível.
                         * Se houver um filho à direita, adiciona ao proximo nível.
                         */
                        if (no.getFilhoEsquerda() != null) {
                            proximoNivel.add(no.getFilhoEsquerda());
                        }

                        if (no.getFilhoDireita() != null) {
                            proximoNivel.add(no.getFilhoDireita());
                        }

                        // Quando passar pelo nívelAtual, faz uma quebra de linha, pode ser um - ou um | tambem.
                        if (nivelAtual.indexOf(no) != nivelAtual.size() - 1) {
                            resultado.append("\n");
                        }
                    }

                    nivelAtual = proximoNivel; // Atualiza a lista do nível atual para a lista do próximo nível.
                }
            }

            resultado.append("]");
            return resultado.toString(); // Retorna a string final.
        }

        @Override
        public String caminharEmOrdem() {
            StringBuilder resultado = new StringBuilder("[");
            if (raiz == null) {
                resultado.append("Vazio]"); //Se a arvore não existir, printa vazio tambem.
            } else {
                reiniciarNavegacao(); // Limpa a pilha de navegação.
                atual = raiz;

                /*
                 * Aqui segue o mesmo raciocínio que obterPróximo. No primeiro loop interno,
                 * navegamos o mais à esquerda possível, empilhando cada nó visitado.
                 * Quando não é possível ir mais à esquerda, saímos do loop interno.
                 * Se atual tiver um filho direito, isso significa que ainda existem nós na
                 * subárvore à direita que precisam ser visitados antes de subir para o pai.
                 * Portanto, atual é atualizado para o filho direito e, em seguida, um novo loop
                 * interno começa, navegando o mais à esquerda possível novamente, empilhando
                 * cada nó visitado na pilha. Esse processo se repete até que não haja mais
                 * filhos à esquerda no nó atual, e então retornamos ao estágio anterior,
                 * onde removemos o último nó da pilha e adicionamos seu valor ao resultado.
                 */
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

            /*
             * Se esta não for a primeira chamada do método (o que é controlado pela variável
             * primeiraChamada), o método continua a partir do último nó visitado. Caso seja a primeira
             * chamada, ele realiza a inicialização.Na primeira chamada, ele marca primeiraChamada
             * como false e define o nó atual como a raiz da árvore. Em seguida, ele
             * começa a percorrer a árvore, indo para o filho mais à esquerda
             * e adicionando cada nó visitado à pilha de navegação.
             */
            if (primeiraChamada) {
                primeiraChamada = false;
                atual = raiz;

                while (atual != null) {
                    pilhaNavegacao.add(atual);
                    atual = atual.getFilhoEsquerda();
                }
            } else {
                /*
                 * Nas próximas chamadas de obterProximo, o método verifica se o nó atual tem um filho
                 * à direita. Se tiver, ele move-se para a direita e continua indo para o filho mais à
                 * esquerda desse nó, adicionando todos os nós ao longo do caminho à pilha de
                 * navegação.
                 */
                if (atual.getFilhoDireita() != null) {
                    atual = atual.getFilhoDireita();

                    while (atual != null) {
                        pilhaNavegacao.add(atual);
                        atual = atual.getFilhoEsquerda();
                    }
                } else {
                    /*
                     * Se o nó atual não tiver um filho à direita, significa que todos os nós à direita
                     * já foram visitados. Nesse caso, ele remove o nó atual da pilha de navegação e
                     * verifica se o nó atual é o filho direito do pai. Se for, continua subindo na
                     * árvore até encontrar um pai que não seja um filho direito.
                     */
                    NoArvore<T> pai = pilhaNavegacao.remove(pilhaNavegacao.size() - 1); // Se o nó atual não tiver filho à direita

                    while (!pilhaNavegacao.isEmpty() && atual == pai.getFilhoDireita()) {
                        atual = pai; // Volta para o pai enquanto o nó atual for o filho direito do pai.
                        pai = pilhaNavegacao.remove(pilhaNavegacao.size() - 1); // Remove o próximo pai da pilha.
                    }

                    atual = pai; // Define o nó atual como o pai atual.
                }
            }

            /*
             * Ou seja... desse jeito percorremos toda a árvore em ordem crescente, e vamos marcando
             * os nós na pilha durante a descida, quando voltamos para o pai desses nós, retiramos
             * os nós da pilha desmarcando eles e retornando seu valor.
             */
            return atual != null ? atual.getValor() : null; // Retorna o valor do nó atual ou null quando não houver mais elementos.
        }


        @Override
        public void reiniciarNavegacao() {
            if (pilhaNavegacao != null) { //Limpa a pilha caso tenha algo e seta a primeiraChamada.
                primeiraChamada = true;
                pilhaNavegacao.clear();
            }
        }
    
}
