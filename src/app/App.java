package app;

import comparators.ComparatorAlunoPorMatricula;
import entities.Aluno;
import generator.TreeGenerator;
import structures.generic_binary_tree.GenericBinaryTree;
import structures.generic_binary_tree.IGenericBinaryTree;

public class App {
    public static void main(String[] args) {
        
        //Instancio o meu gerador de árvores (que também foi fornecido)
        TreeGenerator gerador = new TreeGenerator();
        //Instancio um comparador de alunos por matricula (também fornecido)
        ComparatorAlunoPorMatricula comparador = new ComparatorAlunoPorMatricula();
        IGenericBinaryTree<Aluno> arv;

        try {

            arv = new GenericBinaryTree<>(comparador);
            //Chamo o gerador para inserir 100 elementos nessa árvore de forma que fique degenerada
            gerador.geraArvoreDegenerada(100, arv);
            System.out.println("Árvore Degenerada Criada");
            //Imprimo a quantidade de nós e a altura da árvore resultante
            System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ " Altura: " + arv.altura());
            arv = new GenericBinaryTree<>(comparador);
            gerador.geraArvoreDegenerada(200, arv);
            System.out.println("Árvore Degenerada Criada");
            System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ " Altura: " + arv.altura());
            arv = new GenericBinaryTree<>(comparador);
            gerador.geraArvoreDegenerada(1000, arv);
            System.out.println("Árvore Degenerada Criada");
            System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ " Altura: " + arv.altura());
            //------Fim do trecho citado nas questões 1, 2 e 3 do relatório-------------------------------

            //------Início do trecho citado nas questões 5, 6 e 7 do relatório-------------------------------
            arv = new GenericBinaryTree<>(comparador);
            //Chamo o gerador para inserir 100 elementos nessa árvore de forma que ela fique perfeitamente balanceada
            gerador.geraArvorePerfeitamenteBalanceada(1,100,arv);
            System.out.println("Árvore Perfeitamente Balanceada Criada");
            System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ " Altura: " + arv.altura());
            arv = new GenericBinaryTree<>(comparador);
            gerador.geraArvorePerfeitamenteBalanceada(1,200,arv);
            System.out.println("Árvore Perfeitamente Balanceada Criada");
            System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ " Altura: " + arv.altura());
            arv = new GenericBinaryTree<>(comparador);
            gerador.geraArvorePerfeitamenteBalanceada(1,1000,arv);
            System.out.println("Árvore Perfeitamente Balanceada Criada");
            System.out.println("Quantidade de Nós: " + arv.quantidadeNos()+ " Altura: " + arv.altura());
            //------Fim do trecho citado nas questões 5, 6 e 7 do relatório-------------------------------

            //------Início do trecho citado na questão 9 do relatório-------------------------------
            arv = new GenericBinaryTree<>(comparador);
            gerador.geraArvorePerfeitamenteBalanceada(1,50000,arv);
            System.out.println("Árvore Perfeitamente Balanceada Criada");
            System.out.println("Altura: " + arv.altura());
            arv = new GenericBinaryTree<>(comparador);
            gerador.geraArvoreDegenerada(50000, arv);
            System.out.println("Árvore Degenerada Criada");
            System.out.println(" Altura: " + arv.altura());
            //------Fim do trecho citado na questão 9 do relatório-------------------------------
            
        } catch (StackOverflowError e) {
            
            System.err.println("Ouve StackOverflow!!!!");

        }
        
    
    }

}
