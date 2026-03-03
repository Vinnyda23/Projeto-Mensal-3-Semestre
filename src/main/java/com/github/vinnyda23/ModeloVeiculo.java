package com.github.vinnyda23;

/**
 * Entity class representing a Vehicle Model.
 * Admins can manage vehicle models in the system.
 * Demonstrates proper encapsulation with private attributes.
 */
public class ModeloVeiculo {
    private String nome;
    private String marca;
    private String categoria;
    private double precoBase;
    private int anoLancamento;

    public ModeloVeiculo(String nome, String marca, String categoria, double precoBase, int anoLancamento) {
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.anoLancamento = anoLancamento;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    @Override
    public String toString() {
        return String.format("Marca: %s | Modelo: %s | Categoria: %s | Preço Base: R$ %.2f | Ano: %d",
                marca, nome, categoria, precoBase, anoLancamento);
    }
}

