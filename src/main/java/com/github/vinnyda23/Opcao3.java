package com.github.vinnyda23;

import java.util.Scanner;

public class Opcao3 {
    public static void buscarVeiculos() {

        Scanner sc = new Scanner(System.in);

        Garagem garagem = new Garagem();

        System.out.println("informe o modelo do veiculo que esta buscando");
        String modelo = sc.nextLine();//lê toda a linha de entrada
        Veiculo encontrado = garagem.buscarPorModelo(modelo);

        if (encontrado != null) {
            System.out.println("Veículo Encontrado!");
            System.out.println("Categoria: " + encontrado.getCategoria());
            System.out.println("Modelo: " + encontrado.getModelo());
            System.out.println("Marca: " + encontrado.getMarca());
            System.out.println("Ano: " + encontrado.getAno());
            System.out.println("Cor: " + encontrado.getCor());
            System.out.println("Valor: R$ " + encontrado.getValor());
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
}
