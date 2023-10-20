package estruturas.listas.lista_generica;

import estruturas.listas.no.NoGenerico;

public class ListaEncadeadaGenerica<T> {
    

    private NoGenerico<T> prim;
    private NoGenerico<T> ult;
    private int quant;

    public ListaEncadeadaGenerica(){
        this.prim = this.ult = null;
        this.quant = 0;
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

    public void inserirElemento(T elem){
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
