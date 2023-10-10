package entities;

public class Aluno {
    
    private int matricula;

    private String nome;

    private int nota;

    public Aluno(int matricula, String nome, int nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return Integer.toString(matricula) + " - " + nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + matricula;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + nota;
        return result;
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof Aluno) {
            return this.matricula==((Aluno)a).matricula;
        }
        else {
            return false;
        }
    }

    

        

    
}
