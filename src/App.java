import entities.Aluno;
import structures.list.ListaEncadeada;

public class App {
    public static void main(String[] args) {
        
        ListaEncadeada listaEncadeada = new ListaEncadeada();
        Aluno a1 = new Aluno(1, "Zé", 50);
        Aluno a2 = new Aluno(2, "Mané", 50);
        listaEncadeada.inserirElemento(a1);
        listaEncadeada.inserirElemento(a2);
        System.out.println(listaEncadeada);
        
    }
}
