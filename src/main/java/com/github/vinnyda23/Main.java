package com.github.vinnyda23;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class - CLI interface for Vehicle Management System.
 * IMPORTANT: All Scanner operations and user interaction happen ONLY in this class.
 * Service classes contain NO Scanner or user input logic.
 * Features input validation with retry logic (no immediate return to main menu on invalid input).
 *
 * This system allows:
 * - Complete CRUD operations on vehicles (Estoque)
 * - Complete CRUD operations on vehicle models (only for Admins)
 * - Search filters for vehicles and models
 * - Business reports with aggregated data
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EstoqueService estoqueService;
    private static UsuarioService usuarioService;
    private static ModeloVeiculoService modeloVeiculoService;
    private static Usuario usuarioLogado;

    public static void main(String[] args) {
        // Initialize services
        estoqueService = new EstoqueService();
        usuarioService = new UsuarioService();
        modeloVeiculoService = new ModeloVeiculoService();

        // Load sample data
        carregarDadosAmostra();

        // Main application loop
        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            String opcao = obterEntrada("Escolha uma opção: ");

            switch (opcao) {
                case "1" -> autenticar();
                case "2" -> gerenciarEstoque();
                case "3" -> gerenciarModelos();
                case "4" -> exibirRelatorios();
                case "5" -> {
                    System.out.println("\n========== ENCERRANDO APLICAÇÃO ==========\n");
                    executando = false;
                }
                default -> System.out.println("\n❌ Opção inválida! Tente novamente.\n");
            }
        }

        scanner.close();
    }

    // ============ MENU METHODS ============

    private static void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE GERENCIAMENTO DE VEÍCULOS           ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        if (usuarioLogado != null) {
            System.out.println("📌 Usuário: " + usuarioLogado.getNome() + " (" + usuarioLogado.obterTipo() + ")");
        }
        System.out.println("\n1. Login");
        System.out.println("2. Gerenciar Estoque de Veículos");
        System.out.println("3. Gerenciar Modelos de Veículos (Admin)");
        System.out.println("4. Ver Relatórios");
        System.out.println("5. Sair");
    }

    private static void gerenciarEstoque() {
        if (!verificarLogin()) return;

        // Check if user is Admin
        if (!(usuarioLogado instanceof Admin)) {
            // For regular customers, show only view options
            exibirEstoqueCliente();
            return;
        }

        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║         GERENCIAR ESTOQUE DE VEÍCULOS              ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println("1. Adicionar Veículo");
            System.out.println("2. Listar Todos os Veículos");
            System.out.println("3. Buscar Veículo");
            System.out.println("4. Atualizar Veículo");
            System.out.println("5. Remover Veículo");
            System.out.println("6. Voltar ao Menu Principal");

            String opcao = obterEntrada("Escolha uma opção: ");

            switch (opcao) {
                case "1" -> adicionarVeiculo();
                case "2" -> listarVeiculos();
                case "3" -> buscarVeiculo();
                case "4" -> atualizarVeiculo();
                case "5" -> removerVeiculo();
                case "6" -> voltarMenu = true;
                default -> System.out.println("\n❌ Opção inválida!\n");
            }
        }
    }

    private static void exibirEstoqueCliente() {
        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║         CONSULTAR ESTOQUE DE VEÍCULOS              ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println("1. Listar Todos os Veículos Disponíveis");
            System.out.println("2. Buscar Veículo");
            System.out.println("3. Voltar ao Menu Principal");

            String opcao = obterEntrada("Escolha uma opção: ");

            switch (opcao) {
                case "1" -> listarVeiculos();
                case "2" -> buscarVeiculo();
                case "3" -> voltarMenu = true;
                default -> System.out.println("\n❌ Opção inválida!\n");
            }
        }
    }

    private static void gerenciarModelos() {
        if (!verificarLogin()) return;

        // Check if user is Admin
        if (!(usuarioLogado instanceof Admin)) {
            System.out.println("\n❌ Apenas administradores podem gerenciar modelos de veículos!");
            return;
        }

        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║       GERENCIAR MODELOS DE VEÍCULOS (ADMIN)        ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println("1. Adicionar Novo Modelo");
            System.out.println("2. Listar Todos os Modelos");
            System.out.println("3. Buscar Modelo");
            System.out.println("4. Atualizar Modelo");
            System.out.println("5. Remover Modelo");
            System.out.println("6. Voltar ao Menu Principal");

            String opcao = obterEntrada("Escolha uma opção: ");

            switch (opcao) {
                case "1" -> adicionarModelo();
                case "2" -> listarModelos();
                case "3" -> buscarModelo();
                case "4" -> atualizarModelo();
                case "5" -> removerModelo();
                case "6" -> voltarMenu = true;
                default -> System.out.println("\n❌ Opção inválida!\n");
            }
        }
    }

    // ============ AUTHENTICATION METHODS ============

    private static void autenticar() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║                    LOGIN                           ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        String email = obterEntradaComRetentativa("Email: ", input -> Validador.ehEmailValido(input), "Email inválido! Use o formato correto (ex: user@email.com)");
        String senha = obterEntrada("Senha: ");

        usuarioLogado = usuarioService.autenticar(email, senha);

        if (usuarioLogado != null) {
            System.out.println("\n✅ Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");
        } else {
            System.out.println("\n❌ Email ou senha inválidos!");
        }
    }

    private static void registrarNovoUsuario() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║          REGISTRAR NOVO USUÁRIO                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        String nome = obterEntradaComRetentativa("Nome: ", Validador::naoEstaVazio, "Nome não pode estar vazio!");
        String email = obterEntradaComRetentativa("Email: ", Validador::ehEmailValido, "Email inválido! Use o formato correto (ex: user@email.com)");

        String senha = obterEntradaComRetentativa("Senha: ", Validador::naoEstaVazio, "Senha não pode estar vazia!");
        String endereco = obterEntradaComRetentativa("Endereço: ", Validador::naoEstaVazio, "Endereço não pode estar vazio!");
        String telefone = obterEntradaComRetentativa("Telefone: ", Validador::naoEstaVazio, "Telefone não pode estar vazio!");

        System.out.println("\nTipo de Usuário:");
        System.out.println("1. Admin");
        System.out.println("2. Cliente");
        String tipoOpcao = obterEntradaComRetentativa(
            "Escolha o tipo (1 ou 2): ",
            input -> input.equals("1") || input.equals("2"),
            "Opção inválida! Digite 1 para Admin ou 2 para Cliente"
        );

        if (tipoOpcao.equals("1")) {
            String departamento = obterEntradaComRetentativa("Departamento: ", Validador::naoEstaVazio, "Departamento não pode estar vazio!");
            Admin admin = new Admin(nome, senha, endereco, email, telefone, departamento);
            usuarioService.adicionarUsuario(admin);
            System.out.println("\n✅ Admin registrado com sucesso!");
        } else {
            String cpf = obterEntradaComRetentativa("CPF: ", Validador::ehCpfValido, "CPF inválido! Digite 11 dígitos (ex: 12345678901)");
            Cliente cliente = new Cliente(nome, senha, endereco, email, telefone, cpf);
            usuarioService.adicionarUsuario(cliente);
            System.out.println("\n✅ Cliente registrado com sucesso!");
        }
    }

    // ============ ESTOQUE METHODS ============

    private static void adicionarVeiculo() {
        System.out.println("\n─── Adicionar Novo Veículo ───");

        String marca = obterEntradaComRetentativa("Marca: ", Validador::naoEstaVazio, "Marca não pode estar vazia!");
        String modelo = obterEntradaComRetentativa("Modelo: ", Validador::naoEstaVazio, "Modelo não pode estar vazio!");
        String categoria = obterEntradaComRetentativa("Categoria (Sedan/SUV/Pickup/etc): ", Validador::naoEstaVazio, "Categoria não pode estar vazia!");
        String ano = obterEntradaComRetentativa("Ano: ", Validador::ehAnoValido, "Ano inválido! Digite um ano entre 1900 e 2100");
        String cor = obterEntradaComRetentativa("Cor: ", Validador::naoEstaVazio, "Cor não pode estar vazia!");

        Double valor = obterDoubleComRetentativa("Valor (R$): ", "Valor inválido! Digite um número positivo (ex: 95000.00)");

        Veiculo veiculo = new Veiculo(categoria, modelo, marca, ano, cor, valor);
        estoqueService.adicionarVeiculo(veiculo);
        System.out.println("\n✅ Veículo adicionado com sucesso!");
    }

    private static void listarVeiculos() {
        System.out.println("\n─── Listagem de Veículos ───");
        List<Veiculo> veiculos = estoqueService.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, veiculos.get(i));
        }
    }

    private static void buscarVeiculo() {
        System.out.println("\n─── Buscar Veículo ───");
        System.out.println("1. Por Marca");
        System.out.println("2. Por Modelo");
        System.out.println("3. Por Categoria");
        System.out.println("4. Por Ano");
        System.out.println("5. Por Cor");
        System.out.println("6. Por Faixa de Preço");

        String opcao = obterEntrada("Escolha: ");
        List<Veiculo> resultados = List.of();

        switch (opcao) {
            case "1" -> {
                String marca = obterEntradaComRetentativa("Marca: ", Validador::naoEstaVazio, "Marca não pode estar vazia!");
                resultados = estoqueService.buscarPorMarca(marca);
            }
            case "2" -> {
                String modelo = obterEntradaComRetentativa("Modelo: ", Validador::naoEstaVazio, "Modelo não pode estar vazio!");
                resultados = estoqueService.buscarPorModelo(modelo);
            }
            case "3" -> {
                String categoria = obterEntradaComRetentativa("Categoria: ", Validador::naoEstaVazio, "Categoria não pode estar vazia!");
                resultados = estoqueService.buscarPorCategoria(categoria);
            }
            case "4" -> {
                String ano = obterEntradaComRetentativa("Ano: ", Validador::ehAnoValido, "Ano inválido! Digite um ano entre 1900 e 2100");
                resultados = estoqueService.buscarPorAno(ano);
            }
            case "5" -> {
                String cor = obterEntradaComRetentativa("Cor: ", Validador::naoEstaVazio, "Cor não pode estar vazia!");
                resultados = estoqueService.buscarPorCor(cor);
            }
            case "6" -> {
                Double precoMin = obterDoubleComRetentativa("Preço Mínimo (R$): ", "Valor inválido! Digite um número positivo");
                Double precoMax = obterDoubleComRetentativa("Preço Máximo (R$): ", "Valor inválido! Digite um número positivo");
                resultados = estoqueService.buscarPorFaixaPreco(precoMin, precoMax);
            }
            default -> System.out.println("❌ Opção inválida!");
        }

        if (resultados.isEmpty()) {
            System.out.println("\nNenhum veículo encontrado.");
        } else {
            System.out.println("\n─── Resultados da Busca ───");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, resultados.get(i));
            }
        }
    }

    private static void atualizarVeiculo() {
        listarVeiculos();

        if (estoqueService.obterTotalVeiculos() == 0) {
            return;
        }

        Integer indice = obterIntegerComRetentativa("Número do veículo a atualizar: ", 1, estoqueService.obterTotalVeiculos(), "Número inválido! Digite um número válido da lista");

        System.out.println("\n─── Novos Dados ───");
        String marca = obterEntradaComRetentativa("Marca: ", Validador::naoEstaVazio, "Marca não pode estar vazia!");
        String modelo = obterEntradaComRetentativa("Modelo: ", Validador::naoEstaVazio, "Modelo não pode estar vazio!");
        String categoria = obterEntradaComRetentativa("Categoria: ", Validador::naoEstaVazio, "Categoria não pode estar vazia!");
        String ano = obterEntradaComRetentativa("Ano: ", Validador::ehAnoValido, "Ano inválido! Digite um ano entre 1900 e 2100");
        String cor = obterEntradaComRetentativa("Cor: ", Validador::naoEstaVazio, "Cor não pode estar vazia!");

        Double valor = obterDoubleComRetentativa("Valor (R$): ", "Valor inválido! Digite um número positivo");

        Veiculo veiculoAtualizado = new Veiculo(categoria, modelo, marca, ano, cor, valor);
        if (estoqueService.atualizarVeiculo(indice - 1, veiculoAtualizado)) {
            System.out.println("\n✅ Veículo atualizado com sucesso!");
        }
    }

    private static void removerVeiculo() {
        listarVeiculos();

        if (estoqueService.obterTotalVeiculos() == 0) {
            return;
        }

        Integer indice = obterIntegerComRetentativa("Número do veículo a remover: ", 1, estoqueService.obterTotalVeiculos(), "Número inválido! Digite um número válido da lista");

        if (estoqueService.removerVeiculo(indice - 1)) {
            System.out.println("\n✅ Veículo removido com sucesso!");
        }
    }

    // ============ MODELO VEICULO METHODS ============

    private static void adicionarModelo() {
        System.out.println("\n─── Adicionar Novo Modelo de Veículo ───");

        String nome = obterEntradaComRetentativa("Nome do Modelo: ", Validador::naoEstaVazio, "Nome não pode estar vazio!");
        String marca = obterEntradaComRetentativa("Marca: ", Validador::naoEstaVazio, "Marca não pode estar vazia!");
        String categoria = obterEntradaComRetentativa("Categoria (Sedan/SUV/Pickup/etc): ", Validador::naoEstaVazio, "Categoria não pode estar vazia!");

        Double precoBase = obterDoubleComRetentativa("Preço Base (R$): ", "Valor inválido! Digite um número positivo (ex: 95000.00)");
        Integer ano = obterIntegerComRetentativa("Ano de Lançamento: ", 1900, 2100, "Ano inválido! Digite um ano entre 1900 e 2100");

        ModeloVeiculo modelo = new ModeloVeiculo(nome, marca, categoria, precoBase, ano);
        modeloVeiculoService.adicionarModelo(modelo);
        System.out.println("\n✅ Modelo de veículo adicionado com sucesso!");
    }

    private static void listarModelos() {
        System.out.println("\n─── Listagem de Modelos de Veículos ───");
        List<ModeloVeiculo> modelos = modeloVeiculoService.listarTodos();

        if (modelos.isEmpty()) {
            System.out.println("Nenhum modelo cadastrado.");
            return;
        }

        for (int i = 0; i < modelos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, modelos.get(i));
        }
    }

    private static void buscarModelo() {
        System.out.println("\n─── Buscar Modelo de Veículo ───");
        System.out.println("1. Por Marca");
        System.out.println("2. Por Nome");
        System.out.println("3. Por Categoria");
        System.out.println("4. Por Faixa de Preço");

        String opcao = obterEntrada("Escolha: ");
        List<ModeloVeiculo> resultados = List.of();

        switch (opcao) {
            case "1" -> {
                String marca = obterEntradaComRetentativa("Marca: ", Validador::naoEstaVazio, "Marca não pode estar vazia!");
                resultados = modeloVeiculoService.buscarPorMarca(marca);
            }
            case "2" -> {
                String nome = obterEntradaComRetentativa("Nome do Modelo: ", Validador::naoEstaVazio, "Nome não pode estar vazio!");
                resultados = modeloVeiculoService.buscarPorNome(nome);
            }
            case "3" -> {
                String categoria = obterEntradaComRetentativa("Categoria: ", Validador::naoEstaVazio, "Categoria não pode estar vazia!");
                resultados = modeloVeiculoService.buscarPorCategoria(categoria);
            }
            case "4" -> {
                Double precoMin = obterDoubleComRetentativa("Preço Mínimo (R$): ", "Valor inválido! Digite um número positivo");
                Double precoMax = obterDoubleComRetentativa("Preço Máximo (R$): ", "Valor inválido! Digite um número positivo");
                resultados = modeloVeiculoService.buscarPorFaixaPreco(precoMin, precoMax);
            }
            default -> System.out.println("❌ Opção inválida!");
        }

        if (resultados.isEmpty()) {
            System.out.println("\nNenhum modelo encontrado.");
        } else {
            System.out.println("\n─── Resultados da Busca ───");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, resultados.get(i));
            }
        }
    }

    private static void atualizarModelo() {
        listarModelos();

        if (modeloVeiculoService.obterTotalModelos() == 0) {
            return;
        }

        Integer indice = obterIntegerComRetentativa("Número do modelo a atualizar: ", 1, modeloVeiculoService.obterTotalModelos(), "Número inválido! Digite um número válido da lista");

        System.out.println("\n─── Novos Dados ───");
        String nome = obterEntradaComRetentativa("Nome do Modelo: ", Validador::naoEstaVazio, "Nome não pode estar vazio!");
        String marca = obterEntradaComRetentativa("Marca: ", Validador::naoEstaVazio, "Marca não pode estar vazia!");
        String categoria = obterEntradaComRetentativa("Categoria: ", Validador::naoEstaVazio, "Categoria não pode estar vazia!");

        Double precoBase = obterDoubleComRetentativa("Preço Base (R$): ", "Valor inválido! Digite um número positivo");
        Integer ano = obterIntegerComRetentativa("Ano de Lançamento: ", 1900, 2100, "Ano inválido! Digite um ano entre 1900 e 2100");

        ModeloVeiculo modeloAtualizado = new ModeloVeiculo(nome, marca, categoria, precoBase, ano);
        if (modeloVeiculoService.atualizarModelo(indice - 1, modeloAtualizado)) {
            System.out.println("\n✅ Modelo atualizado com sucesso!");
        }
    }

    private static void removerModelo() {
        listarModelos();

        if (modeloVeiculoService.obterTotalModelos() == 0) {
            return;
        }

        Integer indice = obterIntegerComRetentativa("Número do modelo a remover: ", 1, modeloVeiculoService.obterTotalModelos(), "Número inválido! Digite um número válido da lista");

        if (modeloVeiculoService.removerModelo(indice - 1)) {
            System.out.println("\n✅ Modelo removido com sucesso!");
        }
    }

    // ============ RELATÓRIOS ============

    private static void exibirRelatorios() {
        if (!verificarLogin()) return;

        boolean voltarMenu = false;
        while (!voltarMenu) {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║                   RELATÓRIOS                       ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.println("1. Relatório de Estoque de Veículos");
            System.out.println("2. Relatório de Modelos de Veículos");
            System.out.println("3. Relatório de Usuários");
            System.out.println("4. Voltar ao Menu Principal");

            String opcao = obterEntrada("Escolha: ");

            switch (opcao) {
                case "1" -> relatorioEstoque();
                case "2" -> relatorioModelos();
                case "3" -> relatorioUsuarios();
                case "4" -> voltarMenu = true;
                default -> System.out.println("❌ Opção inválida!");
            }
        }
    }

    private static void relatorioEstoque() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║         RELATÓRIO DE ESTOQUE DE VEÍCULOS           ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        int total = estoqueService.obterTotalVeiculos();
        double valorTotal = estoqueService.calcularValorTotalEstoque();
        double precoMedio = estoqueService.calcularPrecoMedio();
        Veiculo maisCaro = estoqueService.obterVeiculoMaisCaro();
        Veiculo maisBarato = estoqueService.obterVeiculoMaisBarato();

        System.out.println("\n📊 Estatísticas do Estoque:");
        System.out.println("├─ Total de Veículos: " + total);
        System.out.printf("├─ Valor Total do Estoque: R$ %.2f%n", valorTotal);
        System.out.printf("├─ Preço Médio: R$ %.2f%n", precoMedio);

        if (maisCaro != null) {
            System.out.println("├─ Veículo Mais Caro:");
            System.out.println("│  └─ " + maisCaro);
        }

        if (maisBarato != null) {
            System.out.println("└─ Veículo Mais Barato:");
            System.out.println("   └─ " + maisBarato);
        }
    }

    private static void relatorioModelos() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║      RELATÓRIO DE MODELOS DE VEÍCULOS              ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        int total = modeloVeiculoService.obterTotalModelos();
        double valorTotal = modeloVeiculoService.calcularValorTotalModelos();
        double precoMedio = modeloVeiculoService.calcularPrecoMedio();
        ModeloVeiculo maisCaro = modeloVeiculoService.obterModeloMaisCaro();
        ModeloVeiculo maisBarato = modeloVeiculoService.obterModeloMaisBarato();

        System.out.println("\n📊 Estatísticas de Modelos:");
        System.out.println("├─ Total de Modelos: " + total);
        System.out.printf("├─ Valor Total (Preços Base): R$ %.2f%n", valorTotal);
        System.out.printf("├─ Preço Médio: R$ %.2f%n", precoMedio);

        if (maisCaro != null) {
            System.out.println("├─ Modelo Mais Caro:");
            System.out.println("│  └─ " + maisCaro);
        }

        if (maisBarato != null) {
            System.out.println("└─ Modelo Mais Barato:");
            System.out.println("   └─ " + maisBarato);
        }
    }

    private static void relatorioUsuarios() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║           RELATÓRIO DE USUÁRIOS                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        int totalUsuarios = usuarioService.obterTotalUsuarios();
        int totalAdmins = usuarioService.contarPorTipo("ADMIN");
        int totalClientes = usuarioService.contarPorTipo("CLIENTE");

        System.out.println("\n📊 Estatísticas de Usuários:");
        System.out.println("├─ Total de Usuários: " + totalUsuarios);
        System.out.println("├─ Administradores: " + totalAdmins);
        System.out.println("└─ Clientes: " + totalClientes);

        System.out.println("\n─── Listagem de Usuários ───");
        List<Usuario> usuarios = usuarioService.listarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, usuarios.get(i));
        }
    }

    // ============ UTILITY METHODS - INPUT WITH VALIDATION & RETRY ============

    /**
     * Get input with validation that retries until valid input is received.
     * Does NOT return to main menu on invalid input - user can try again immediately.
     */
    private static String obterEntradaComRetentativa(String prompt, Validador.ValidadorFunction validador, String mensagemErro) {
        boolean entradaValida = false;
        String entrada = "";

        while (!entradaValida) {
            System.out.print(prompt);
            entrada = scanner.nextLine().trim();

            if (validador.validar(entrada)) {
                entradaValida = true;
            } else {
                System.out.println("❌ " + mensagemErro + " Tente novamente.\n");
            }
        }

        return entrada;
    }

    /**
     * Get Integer input with validation and retry logic.
     */
    private static Integer obterIntegerComRetentativa(String prompt, int minValue, int maxValue, String mensagemErro) {
        boolean entradaValida = false;
        Integer valor = null;

        while (!entradaValida) {
            System.out.print(prompt);
            String entrada = scanner.nextLine().trim();
            valor = Validador.parseIntegerSafe(entrada);

            if (valor != null && valor >= minValue && valor <= maxValue) {
                entradaValida = true;
            } else {
                System.out.println("❌ " + mensagemErro + " Tente novamente.\n");
            }
        }

        return valor;
    }

    /**
     * Get Double input with validation and retry logic.
     */
    private static Double obterDoubleComRetentativa(String prompt, String mensagemErro) {
        boolean entradaValida = false;
        Double valor = null;

        while (!entradaValida) {
            System.out.print(prompt);
            String entrada = scanner.nextLine().trim();
            valor = Validador.parseDoubleSafe(entrada);

            if (valor != null && Validador.ehValorValido(valor)) {
                entradaValida = true;
            } else {
                System.out.println("❌ " + mensagemErro + " Tente novamente.\n");
            }
        }

        return valor;
    }

    private static boolean verificarLogin() {
        if (usuarioLogado == null) {
            System.out.println("\n❌ Você precisa estar logado para acessar essa funcionalidade!");
            return false;
        }
        return true;
    }

    private static String obterEntrada(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // ============ SAMPLE DATA ============

    private static void carregarDadosAmostra() {
        // Add sample users
        Admin admin1 = new Admin("João Silva", "admin123", "Rua A, 123", "joao@email.com", "11999999999", "Gerente");
        Cliente cliente1 = new Cliente("Maria Santos", "cliente123", "Rua B, 456", "maria@email.com", "11988888888", "12345678901");
        Cliente cliente2 = new Cliente("Pedro Costa", "cliente456", "Rua C, 789", "pedro@email.com", "11987654321", "98765432109");

        usuarioService.adicionarUsuario(admin1);
        usuarioService.adicionarUsuario(cliente1);
        usuarioService.adicionarUsuario(cliente2);

        // Add sample vehicle models
        ModeloVeiculo modelo1 = new ModeloVeiculo("Civic", "Honda", "Sedan", 95000.00, 2023);
        ModeloVeiculo modelo2 = new ModeloVeiculo("CR-V", "Honda", "SUV", 145000.00, 2023);
        ModeloVeiculo modelo3 = new ModeloVeiculo("Corolla", "Toyota", "Sedan", 105000.00, 2023);
        ModeloVeiculo modelo4 = new ModeloVeiculo("Hilux", "Toyota", "Pickup", 165000.00, 2023);
        ModeloVeiculo modelo5 = new ModeloVeiculo("Sportage", "Kia", "SUV", 135000.00, 2023);

        modeloVeiculoService.adicionarModelo(modelo1);
        modeloVeiculoService.adicionarModelo(modelo2);
        modeloVeiculoService.adicionarModelo(modelo3);
        modeloVeiculoService.adicionarModelo(modelo4);
        modeloVeiculoService.adicionarModelo(modelo5);

        // Add sample vehicles (in stock)
        Veiculo veiculo1 = new Veiculo("Sedan", "Civic", "Honda", "2022", "Preto", 95000.00);
        Veiculo veiculo2 = new Veiculo("SUV", "CR-V", "Honda", "2023", "Branco", 145000.00);
        Veiculo veiculo3 = new Veiculo("Sedan", "Corolla", "Toyota", "2022", "Prata", 105000.00);
        Veiculo veiculo4 = new Veiculo("Pickup", "Hilux", "Toyota", "2021", "Branco", 165000.00);
        Veiculo veiculo5 = new Veiculo("SUV", "Sportage", "Kia", "2023", "Azul", 135000.00);

        estoqueService.adicionarVeiculo(veiculo1);
        estoqueService.adicionarVeiculo(veiculo2);
        estoqueService.adicionarVeiculo(veiculo3);
        estoqueService.adicionarVeiculo(veiculo4);
        estoqueService.adicionarVeiculo(veiculo5);

        System.out.println("✅ Dados de amostra carregados com sucesso!\n");
        System.out.println("Dados de teste disponíveis:");
        System.out.println("  Admin: joao@email.com / admin123");
        System.out.println("  Cliente: maria@email.com / cliente123\n");
    }
}
