package comparators;

import java.util.Comparator;

import entidades.Aluno;

public class ComparatorAlunoPorNome  implements Comparator<Aluno>{

    @Override
    public int compare(Aluno o1, Aluno o2) {
        return o1.getNome().compareTo(o2.getNome());
    }
    
}
