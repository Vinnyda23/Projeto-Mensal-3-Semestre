package com.github.vinnyda23;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Garagem garagem = new Garagem();

        int opcao = 0; //zero para sair do programa


        while (opcao != 5) {

            //menu de possibilidades - Classe estatica
            System.out.println("==== CONCESSEIONARIA ====");
            System.out.println("1 - Cadastrar Veiculo");
            System.out.println("2 - Listar Veiculos");
            System.out.println("3 - Buscar Veiculos");
            System.out.println("4 - Remover Veiculos");
            System.out.println("5 - Sair");
            //menu encerramento para opcao da classe estatica
            opcao = sc.nextInt();
            sc.nextLine();//serve para pimpar o buffer

            switch (opcao) {
                case 1:
                    //classe opcao 1
                    Opcao1.cadastroVeiculo();
                    break;
                case 2:
                    Opcao2.listaVeiculos();
                    break;
                case 3:
                    Opcao3.buscarVeiculos();
                    break;
                case 4:
                    Opcao4.removerVeiculos();
                    break;
                case 5:
                    Opcao5.encerrar();
                    opcao = 5;
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        }
    }
}

