package com.github.vinnyda23;
import java.util.Scanner;

public class Opcao1{
    public static void cadastroVeiculo(Garagem garagem) {

        //ativa o Scanner
        Scanner sc = new Scanner(System.in);


        System.out.println("Cadastro de Veiculo");

        System.out.println("Categoria: ");
        String categoria = sc.nextLine();// aqui ele armazena a categoria do veiculo, como "veiculo esporivo" , direto na String categoria de Veiculo

        System.out.println("Modelo: ");
        String modelo = sc.nextLine();

        System.out.println("Marca: ");
        String marca = sc.nextLine();

        System.out.println("Ano: ");
        String ano = sc.nextLine();

        System.out.println("Cor: ");
        String cor = sc.nextLine();

        System.out.println("Valor: ");
        double valor = sc.nextDouble(); // aqui usamos "nextDouble" pois o valor pode ser numero quebrado
        sc.nextLine();


        Veiculo v = new Veiculo(categoria, modelo, marca, ano, cor, valor);
        garagem.adicionarVeiculos(v);


    }

}
