package com.github.vinnyda23;
import java.util.ArrayList;
import java.util.Scanner;


public class Opcao4 {
    public static void ComprarVeiculo(Garagem garagem, Usuario usuarioLogado){

        Scanner sc = new Scanner(System.in);

        garagem.listarVeiculos();
        System.out.println("Digite modelo para comprar:");
        String modelo = sc.nextLine();

        Veiculo v = garagem.buscarDisponivel(modelo);

        if (v != null) {
            v.setDisponivel(false);
            usuarioLogado.adicionarVeiculos();
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Veículo indisponível.");
        }
    }
}
