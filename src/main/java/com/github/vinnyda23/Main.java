package com.github.vinnyda23;
import com.github.vinnyda23.Garagem;
import com.github.vinnyda23.Veiculo;


import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);//aqui criamos nosso Scaner , parqa quando inseriroms algo no terminal ele ler, System in significa que via ler oque o usuario digitar
        Garagem garagem = new Garagem();

        System.out.println("Cadastro de Veiculo");

        System.out.println("Categoria: ");
        String categoria = sc.nextLine();// aqui ele armazena a categoria do veiculo, como "veiculo esporivo" , direto na String categoria de Veiculo

        System.out.println("Modelo: ");
        String modelo = sc.nextLine();

        System.out.println("Macar: ");
        String marca = sc.nextLine();

        System.out.println("Ano: ");
        String ano = sc.nextLine();

        System.out.println("Cor; ");
        String cor = sc.nextLine();

        System.out.println("Valor: ");
        double valor = sc.nextDouble(); // aqui usamos "nextDouble" pois o valor pode ser numero quebrado

        Veiculo v = new Veiculo(categoria, modelo, marca, ano, cor, valor);

        garagem.adicionarVeiculos(v);

        System.out.println("\nVeiculos Cadastrados: ");
        garagem.listarVeiculos();

        sc.close(); // aqui encerra o Scanner

    }
}