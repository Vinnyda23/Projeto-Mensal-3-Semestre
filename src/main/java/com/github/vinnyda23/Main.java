package com.github.vinnyda23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SistemaUsuarios sistemaUsuarios = new SistemaUsuarios();

        // Login e senha  ADMIN
        sistemaUsuarios.cadastrarUsuario(new ArrayList<Usuario>(Arrays.asList(new Usuario(
                        "Admin",          // login
                        "123",            // senha
                        "CPD",    // endereco
                        "admin",// email
                        "CPD",       // telefone
                        "ADMIN"           // tipo
                ),
                new Usuario(
                        "Cliente",
                        "cliente",
                        "Empresa Garagem Simas turbo",
                        "Cliente",
                        "99877261",
                        "Cliente"
                )))
        );

        Usuario usuarioLogado = null;
        Garagem garagem = new Garagem();
        Scanner sc = new Scanner(System.in);

        int selection2 = 0;



        while(usuarioLogado == null) {

            System.out.println("==== LOGIN ====");
            System.out.println("Email:");
            String login = sc.nextLine();

            System.out.println("Senha:");
            String senha = sc.nextLine();

            usuarioLogado = sistemaUsuarios.login(login, senha);

            if (usuarioLogado == null) {
                System.out.println("Login nao registrado");
            }
        }
        while (selection2 != 6) {

            //menu de possibilidades - Classe estatica
            System.out.println("==== CONCESSEIONARIA ====");
            System.out.println("1 - Cadastrar Veiculo");
            System.out.println("2 - Listar Veiculos");
            System.out.println("3 - Buscar Veiculos");
            System.out.println("4 - Comprar veiculos");
            System.out.println("5 - Remover Veiculos");
            System.out.println("6 - Sair");
            //menu encerramento para opcao da classe estatica
            selection2 = sc.nextInt();
            sc.nextLine();//serve para pimpar o buffer

            switch (selection2) {
                case 1:
                    if (usuarioLogado.getTipo().equals("ADMIN")) {
                        Opcao1.cadastroVeiculo(garagem);
                    } else {
                        System.out.println("Apenas ADMIN pode cadastrar veículos.");
                    }
                    break;
                case 2:
                    Opcao2.listaVeiculos(garagem);
                    break;
                case 3:
                    Opcao3.buscarVeiculos(garagem);
                    break;
                case 4:
                    if (usuarioLogado.getTipo().equals("CLIENTE")) {
                        Opcao4.ComprarVeiculo(garagem, usuarioLogado);
                    } else {
                        System.out.println("Apenas CLIENTE pode comprar.");
                    }
                    break;

                case 5:
                    if (usuarioLogado.getTipo().equals("ADMIN")) {
                        Opcao5.removerVeiculos(garagem);
                    } else {
                        System.out.println("Apenas ADMIN pode remover.");
                    }
                    break;
                case 6:
                    Opcao6.encerrar();
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
                }
            }
        }
    }


