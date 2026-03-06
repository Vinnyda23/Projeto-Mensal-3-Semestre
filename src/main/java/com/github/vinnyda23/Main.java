package com.github.vinnyda23;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        SistemaUsuarios sistemaUsuarios = new SistemaUsuarios();

        // Login e senha  ADMIN
        sistemaUsuarios.cadastrarUsuario(new ArrayList<Usuario>(Arrays.asList(new Usuario("Admin", "123", "CPD", "admin", "CPD", "ADMIN"),
                new Usuario("Cliente", "cliente", "Empresa Garagem Simas turbo", "Cliente", "99877261", "CLIENTE")))
        );


        Usuario usuarioLogado = null;
        Garagem garagem = new Garagem();
        garagem.adicionarVeiculos(new Veiculo("Popular Plus", "Corolla", "Toyota", "2020","preto",75000.99));
        garagem.adicionarVeiculos(new Veiculo("Popular PLus", "Civic","Honda", "2019","branco",523412.312));
        garagem.adicionarVeiculos(new Veiculo("Popular Plus", "Focus","Ford", "2018","azul escuro",178000.00));
        garagem.adicionarVeiculos(new Veiculo("Luxo","Cayenne","Porsche","2026","vermelha",1740000.00));
        Scanner sc = new Scanner(System.in);

        int selection2 = 0;



        while(usuarioLogado == null) {
            try {

                System.out.println("==== LOGIN ====");
                System.out.println("Email:");
                String login = sc.nextLine();

                System.out.println("Senha:");
                String senha = sc.nextLine();

                usuarioLogado = sistemaUsuarios.login(login, senha);

                if (usuarioLogado == null) {
                    System.out.println("Login nao registrado");
                }
            }catch (Exception e) {
                System.out.println("Numero/Caracter Invalido");
                sc.nextLine();
            }
        }
        while (selection2 != 6) {
            try {
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
            }catch(InputMismatchException e) {
                System.out.println("|----------------------------------|");
                System.out.println("|-- ⚠ Erro , Caracter invalido ⚠ --|");
                sc.nextLine();
            }

            switch (selection2) {
                case 1:
                    if (usuarioLogado.getTipo().equals("ADMIN")) {
                        Opcao1.cadastroVeiculo(garagem);
                    } else {
                        System.out.println("|-------------------------------------------|");
                        System.out.println("|-- Apenas ADMIN pode cadastrar veículos. --|");
                        System.out.println("|-------------------------------------------|");
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
                    System.out.println("|--------------------------------|");
                    System.out.println("|--      Numero Invalido⚠      --|");
                    System.out.println("|-- ⚠ Use os numero de 1 a 6 ⚠ --|");
                    System.out.println("|--------------------------------|");
                    break;
                }
            }
        }
    }


