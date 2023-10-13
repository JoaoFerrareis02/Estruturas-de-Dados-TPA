package app;

import structures.generic_comparable.GenericLinkedListWithComparable;
import entities.Aluno;

public class App {
    public static void main(String[] args) {
        
        GenericLinkedListWithComparable<Aluno> listaEncadeada = new GenericLinkedListWithComparable<>(true);
        Aluno a1 = new Aluno(1, "Zé", 50);
        Aluno a2 = new Aluno(2, "Mané", 50);
        listaEncadeada.inserirElemento(a1);
        listaEncadeada.inserirElemento(a2);
        System.out.println(listaEncadeada);

    }
}
