package app;
import structures.generic.GenericLinkedList;
import entities.Aluno;

public class App {
    public static void main(String[] args) {
        
        GenericLinkedList<Aluno> listaEncadeada = new GenericLinkedList<>();
        Aluno a1 = new Aluno(1, "Zé", 50);
        Aluno a2 = new Aluno(2, "Mané", 50);
        listaEncadeada.inserirElemento(a1);
        listaEncadeada.inserirElemento(a2);
        System.out.println(listaEncadeada);

    }
}
