package app;

import structures.generic_comparator.GenericLinkedListWithComparator;
import comparators.ComparatorAlunoPorMatricula;
import comparators.ComparatorAlunoPorNome;
import entities.Aluno;

public class App {
    public static void main(String[] args) {
        
        GenericLinkedListWithComparator<Aluno> listaOrdNome = new GenericLinkedListWithComparator<>(true, new ComparatorAlunoPorNome());
        GenericLinkedListWithComparator<Aluno> listaOrdMatricula = new GenericLinkedListWithComparator<>(true, new ComparatorAlunoPorMatricula());
        
        Aluno a1 = new Aluno(1, "Zé", 50);
        Aluno a2 = new Aluno(2, "Mané", 50);
        
        listaOrdNome.inserirElemento(a1);
        listaOrdNome.inserirElemento(a2);

        listaOrdMatricula.inserirElemento(a1);
        listaOrdMatricula.inserirElemento(a2);

        System.out.println("Lista ordenada por Nome: " + listaOrdNome);
        System.out.println("Lista ordenada por Matricula: " + listaOrdMatricula);

    }
}
