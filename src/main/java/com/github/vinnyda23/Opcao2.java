package com.github.vinnyda23;

import java.util.Scanner;

public class Opcao2 {

    public static void listaVeiculos(Garagem garagem) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Lista de Veiculos");
        garagem.listarVeiculos();//lê toda a linha de entrada
    }
}
