package com.github.vinnyda23;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing Carrinho (Orders/Shopping Carts).
 * Implements CRUD operations for orders.
 * All data is stored in memory using ArrayList.
 */
public class CarrinhoService {
    private List<Carrinho> carrinhos;
    private int proximoNumeroPedido;

    public CarrinhoService() {
        this.carrinhos = new ArrayList<>();
        this.proximoNumeroPedido = 1001; // Start order numbers from 1001
    }

    /**
     * CREATE: Create a new order/cart for a customer.
     */
    public Carrinho criarCarrinho(Usuario cliente) {
        if (cliente != null) {
            Carrinho carrinho = new Carrinho(proximoNumeroPedido++, cliente);
            carrinhos.add(carrinho);
            return carrinho;
        }
        return null;
    }

    /**
     * READ: Get all orders.
     */
    public List<Carrinho> listarTodos() {
        return new ArrayList<>(carrinhos);
    }

    /**
     * READ: Get order by index.
     */
    public Carrinho obterPorIndice(int indice) {
        if (indice >= 0 && indice < carrinhos.size()) {
            return carrinhos.get(indice);
        }
        return null;
    }

    /**
     * READ: Get order by order number.
     */
    public Carrinho obterPorNumeroPedido(int numeroPedido) {
        for (Carrinho carrinho : carrinhos) {
            if (carrinho.getNumeroPedido() == numeroPedido) {
                return carrinho;
            }
        }
        return null;
    }

    /**
     * UPDATE: Update an order.
     */
    public boolean atualizarCarrinho(int indice, Carrinho carrinhoAtualizado) {
        if (indice >= 0 && indice < carrinhos.size() && carrinhoAtualizado != null) {
            carrinhos.set(indice, carrinhoAtualizado);
            return true;
        }
        return false;
    }

    /**
     * DELETE: Remove order by index.
     */
    public boolean removerCarrinho(int indice) {
        if (indice >= 0 && indice < carrinhos.size()) {
            carrinhos.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * SEARCH: Find orders by customer name.
     */
    public List<Carrinho> buscarPorCliente(String nomeCliente) {
        List<Carrinho> resultados = new ArrayList<>();
        for (Carrinho carrinho : carrinhos) {
            if (carrinho.getCliente().getNome().toLowerCase().contains(nomeCliente.toLowerCase())) {
                resultados.add(carrinho);
            }
        }
        return resultados;
    }

    /**
     * SEARCH: Find orders within a price range.
     */
    public List<Carrinho> buscarPorFaixaPreco(double precoMinimo, double precoMaximo) {
        List<Carrinho> resultados = new ArrayList<>();
        for (Carrinho carrinho : carrinhos) {
            double valor = carrinho.getValorTotal();
            if (valor >= precoMinimo && valor <= precoMaximo) {
                resultados.add(carrinho);
            }
        }
        return resultados;
    }

    /**
     * BUSINESS REPORT: Calculate total revenue from all orders.
     */
    public double calcularReceitaTotal() {
        double total = 0;
        for (Carrinho carrinho : carrinhos) {
            total += carrinho.getValorTotal();
        }
        return total;
    }

    /**
     * BUSINESS REPORT: Calculate average order value.
     */
    public double calcularValorMedioCarrinho() {
        if (carrinhos.isEmpty()) {
            return 0;
        }
        return calcularReceitaTotal() / carrinhos.size();
    }

    /**
     * BUSINESS REPORT: Get the most expensive order.
     */
    public Carrinho obterCarrinhoMaisCaro() {
        if (carrinhos.isEmpty()) {
            return null;
        }
        Carrinho maisCaro = carrinhos.get(0);
        for (Carrinho carrinho : carrinhos) {
            if (carrinho.getValorTotal() > maisCaro.getValorTotal()) {
                maisCaro = carrinho;
            }
        }
        return maisCaro;
    }

    /**
     * BUSINESS REPORT: Get customer with most orders.
     */
    public String obterClienteComMaisCompras() {
        if (carrinhos.isEmpty()) {
            return "Nenhum pedido encontrado";
        }

        // Count orders per customer
        java.util.Map<String, Integer> contagemClientes = new java.util.HashMap<>();
        for (Carrinho carrinho : carrinhos) {
            String nomeCliente = carrinho.getCliente().getNome();
            contagemClientes.put(nomeCliente, contagemClientes.getOrDefault(nomeCliente, 0) + 1);
        }

        // Find customer with most orders
        String clienteMais = "";
        int maiorContagemm = 0;
        for (String cliente : contagemClientes.keySet()) {
            int contagem = contagemClientes.get(cliente);
            if (contagem > maiorContagemm) {
                maiorContagemm = contagem;
                clienteMais = cliente;
            }
        }

        return clienteMais + " (" + maiorContagemm + " pedidos)";
    }

    /**
     * Get total number of orders.
     */
    public int obterTotalCarrinhos() {
        return carrinhos.size();
    }
}

