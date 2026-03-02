package com.github.vinnyda23;

import java.util.ArrayList;

public class Carrinho {
    private int numeroPedido;
    private Usuario cliente;
    private ArrayList<Veiculo> itens;
    private double valorTotal;

    public Carrinho(int numeroPedido, Usuario cliente, ArrayList<Veiculo> itens, double valorTotal) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = new ArrayList<Veiculo>();
        this.valorTotal = 0;
    }
}
