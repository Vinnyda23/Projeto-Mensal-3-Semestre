package com.github.vinnyda23;

/**
 * Entity class representing a vehicle in the system.
 * Contains all vehicle attributes with proper encapsulation.
 */
public class Veiculo {
    private String categoria;
    private String modelo;
    private String marca;
    private String ano;
    private String cor;
    private double valor;

    public Veiculo(String categoria, String modelo, String marca, String ano, String cor, double valor) {
        this.categoria = categoria;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.valor = valor;
    }

    // Getters and Setters
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("Marca: %s | Modelo: %s | Categoria: %s | Ano: %s | Cor: %s | Valor: R$ %.2f",
                marca, modelo, categoria, ano, cor, valor);
    }
}