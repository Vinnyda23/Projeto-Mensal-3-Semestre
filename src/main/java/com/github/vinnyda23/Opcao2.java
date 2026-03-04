package com.github.vinnyda23;

import java.util.Scanner;

public class Opcao2 {

    public static void listaVeiculos() {

        Scanner sc = new Scanner(System.in);

        Garagem garagem = new Garagem();

        System.out.println("Lista de Veiculos");
        garagem.listarVeiculos();//lê toda a linha de entrada
    }
}
