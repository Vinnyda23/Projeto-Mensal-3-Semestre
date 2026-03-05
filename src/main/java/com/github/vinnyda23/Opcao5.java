package com.github.vinnyda23;

import java.util.Scanner;

public class Opcao5 {
    public static void removerVeiculos(Garagem garagem) {

        Scanner sc = new Scanner(System.in);


        System.out.println("Digite o Modelo para remover");
        String modelo = sc.nextLine();//aqui ele ira procurar o modelo inserido dentro da string "modelo"
        Veiculo encontrado = garagem.buscarPorModelo(modelo);
        garagem.removerVeiculos(modelo);//lê toda a linha de entrada

        if (encontrado != null) {
            System.out.println("Veiculo Encontrado");
            System.out.println("Modelo: " + encontrado.getModelo());
            System.out.println("Marca: " + encontrado.getMarca());
            System.out.println("Ano: " + encontrado.getAno());
            System.out.println("Valor: " + encontrado.getValor());

        } else {//se nao achar o veiculo exibe a mensagem abaixo
            System.out.println("Veiculo nao encontrado");
        }
    }
}
