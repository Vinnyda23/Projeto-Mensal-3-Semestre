package com.github.vinnyda23;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a shopping cart/order (deprecated).
 * This class is kept for backward compatibility but is not used in the current application.
 * The application now focuses on vehicle model and stock management.
 */
public class Carrinho {
    private int numeroPedido;
    private Usuario cliente;
    private List<Veiculo> itens;
    private double valorTotal;

    public Carrinho(int numeroPedido, Usuario cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0;
    }

    // Getters and Setters
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public List<Veiculo> getItens() {
        return new ArrayList<>(itens);
    }

    public double getValorTotal() {
        return valorTotal;
    }


    @Override
    public String toString() {
        return String.format("Pedido #%d | Cliente: %s | Itens: %d | Total: R$ %.2f",
                numeroPedido, cliente.getNome(), itens.size(), valorTotal);
    }
}
