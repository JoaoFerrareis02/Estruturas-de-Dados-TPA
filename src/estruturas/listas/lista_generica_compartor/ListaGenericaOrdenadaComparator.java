package estruturas.listas.lista_generica_compartor;

import java.util.Comparator;

import estruturas.listas.no.NoGenerico;

public class ListaGenericaOrdenadaComparator<T> {
    

    private NoGenerico<T> prim;
    private NoGenerico<T> ult;
    private int quant;
    private final boolean ordenada;
    private Comparator<T> comparator;

    public ListaGenericaOrdenadaComparator(boolean ordenada, Comparator<T> comparator){
        this.prim = this.ult = null;
        this.quant = 0;
        this.ordenada = ordenada;
        this.comparator = comparator;
    }

    public NoGenerico<T> getPrim() {
        return prim;
    }

    public NoGenerico<T> getUlt() {
        return ult;
    }

    public int getQuant() {
        return quant;
    }

    public boolean getOrdenada() {
        return ordenada;
    }

    public void inserirElemento(T elem){
        if (!this.ordenada) {
            inserirElementoNaoOrd(elem);
        } else {
            inserirElementoOrd(elem);
        }
    }

    private void inserirElementoOrd(T elem) {
        NoGenerico<T> novo = new NoGenerico<>(elem);
        NoGenerico<T> atual;
        NoGenerico<T> ant;
        atual = this.prim;
        ant = null;
        //Se a lista estiver vazia o novo elemento será primeiro e ultimo
        if (this.prim == null) {
            this.prim = this.ult = novo;
        }
        //Se não estiver vazia 
        else {
            //Enquanto não for o fim da lista e o atual estiver
            //em um elemento menor que o novo vou para o proximo
            //Note que a ordem das condições faz a diferença 
            //Se inverter, pode dar null pointer exception...
            while (atual != null && comparator.compare(atual.getValor(), elem) < 0) {
                ant = atual;
                atual = atual.getProx();
            }
            //Se ant estiver null é sinal de que não encontrou no laço
            //Logo, o novo é menor do que o prim e deve entrar como novo prim
            if (ant == null) {
                novo.setProx(this.prim);
                this.prim = novo;
            }
            //Se atual for null é sinal que varreu toda a lista e o novo deve
            //entrar como ult
            else if (atual == null){
                this.ult.setProx(novo);
                this.ult = novo;
            }
            //Se nao for prim nem ult, entrara entre o ant e o atual
            else {
                ant.setProx(novo);
                novo.setProx(atual);
            }
        }
        this.quant++;
    }

    public void inserirElementoNaoOrd(T elem){
        NoGenerico<T> novo = new NoGenerico<>(elem);
        //Se a lista está vazia
        if(this.prim == null){
            this.prim = novo;
            this.ult = novo;
        }
        //Se não estiver colocarei o elemento no fim da lista
        else {
            this.ult.setProx(novo);
            this.ult = novo;
        }
        this.quant++;
    }

    public boolean contemElemento(T elem){
        NoGenerico<T> aux = this.prim;
        while(aux != null){
            if(aux.getValor().equals(elem)) return true;
            aux = aux.getProx();
        }
        return false;
    }

    public boolean excluirElemento(T elem){
        NoGenerico<T> aux = this.prim;
        NoGenerico<T> ant = null;
        while(aux != null){
            //Se encontrou remove o elemento
            if (aux.getValor().equals(elem)) {
                //Se for o primeiro elemento, o prim passa a apontar para o proximo
                if (aux == this.prim) {
                    this.prim = this.prim.getProx();
                    //Verifico se tbm é o ult, ou seja, era o unico da lista
                    if (aux == this.ult) this.ult = null;
                }
                //Se não é o primeiro, o anterior passa a apontar para o proximo
                else {
                    ant.setProx(aux.getProx());
                    //Se ele for o ult, o ult passa a ser o ant
                    if (aux == this.ult) this.ult = ant;
                }
                //Decremento a quantidade
                this.quant--;
                return true;
            }
            //Se nao encontrou, ant vai pra aux e aux vai para o proximo.
            ant = aux;
            aux = aux.getProx();
        }
        //Se rodou tudo sem encontrar retorna false
        return false;
    }

    @Override
    public String toString() {
        NoGenerico<T> aux = this.prim;
        StringBuilder s = new StringBuilder();
        s.append("[");
        while (aux != null) {
            s.append(aux.getValor());
            if (aux != this.ult) s.append(",");
            aux = aux.getProx();
        }
        s.append("]");
        return s.toString();
    }

}
