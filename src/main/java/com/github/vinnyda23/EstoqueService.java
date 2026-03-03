package com.github.vinnyda23;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Service class for managing Estoque (Stock) data.
 * Implements CRUD operations and search functionality.
 * All data is stored in memory using ArrayList (no static variables or global storage).
 */
public class EstoqueService {
    private List<Veiculo> veiculos;

    public EstoqueService() {
        this.veiculos = new ArrayList<>();
    }

    /**
     * CREATE: Add a new vehicle to stock.
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        if (veiculo != null) {
            veiculos.add(veiculo);
        }
    }

    /**
     * READ: Get all vehicles in stock.
     */
    public List<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos);
    }

    /**
     * READ: Get a vehicle by index.
     */
    public Veiculo obterPorIndice(int indice) {
        if (indice >= 0 && indice < veiculos.size()) {
            return veiculos.get(indice);
        }
        return null;
    }

    /**
     * UPDATE: Update vehicle at given index.
     */
    public boolean atualizarVeiculo(int indice, Veiculo veiculoAtualizado) {
        if (indice >= 0 && indice < veiculos.size() && veiculoAtualizado != null) {
            veiculos.set(indice, veiculoAtualizado);
            return true;
        }
        return false;
    }

    /**
     * DELETE: Remove vehicle by index.
     */
    public boolean removerVeiculo(int indice) {
        if (indice >= 0 && indice < veiculos.size()) {
            veiculos.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * SEARCH: Generic search method using Predicate for code reuse.
     * Accepts a filter criteria and returns matching vehicles.
     */
    public List<Veiculo> buscar(Predicate<Veiculo> criterio) {
        List<Veiculo> resultados = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (criterio.test(veiculo)) {
                resultados.add(veiculo);
            }
        }
        return resultados;
    }

    /**
     * SEARCH: Find vehicles by brand (marca).
     */
    public List<Veiculo> buscarPorMarca(String marca) {
        return buscar(v -> v.getMarca().equalsIgnoreCase(marca));
    }

    /**
     * SEARCH: Find vehicles by category (categoria).
     */
    public List<Veiculo> buscarPorCategoria(String categoria) {
        return buscar(v -> v.getCategoria().equalsIgnoreCase(categoria));
    }

    /**
     * SEARCH: Find vehicles by model (modelo).
     */
    public List<Veiculo> buscarPorModelo(String modelo) {
        return buscar(v -> v.getModelo().equalsIgnoreCase(modelo));
    }

    /**
     * SEARCH: Find vehicles by year (ano).
     */
    public List<Veiculo> buscarPorAno(String ano) {
        return buscar(v -> v.getAno().equals(ano));
    }

    /**
     * SEARCH: Find vehicles by color (cor).
     */
    public List<Veiculo> buscarPorCor(String cor) {
        return buscar(v -> v.getCor().equalsIgnoreCase(cor));
    }

    /**
     * SEARCH: Find vehicles by price range.
     */
    public List<Veiculo> buscarPorFaixaPreco(double precoMinimo, double precoMaximo) {
        return buscar(v -> v.getValor() >= precoMinimo && v.getValor() <= precoMaximo);
    }

    /**
     * BUSINESS REPORT: Calculate average price of all vehicles.
     */
    public double calcularPrecoMedio() {
        if (veiculos.isEmpty()) {
            return 0;
        }
        double soma = 0;
        for (Veiculo veiculo : veiculos) {
            soma += veiculo.getValor();
        }
        return soma / veiculos.size();
    }

    /**
     * BUSINESS REPORT: Calculate total stock value.
     */
    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Veiculo veiculo : veiculos) {
            total += veiculo.getValor();
        }
        return total;
    }

    /**
     * BUSINESS REPORT: Find the most expensive vehicle.
     */
    public Veiculo obterVeiculoMaisCaro() {
        if (veiculos.isEmpty()) {
            return null;
        }
        Veiculo maisCaro = veiculos.get(0);
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getValor() > maisCaro.getValor()) {
                maisCaro = veiculo;
            }
        }
        return maisCaro;
    }

    /**
     * BUSINESS REPORT: Find the cheapest vehicle.
     */
    public Veiculo obterVeiculoMaisBarato() {
        if (veiculos.isEmpty()) {
            return null;
        }
        Veiculo maisBarato = veiculos.get(0);
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getValor() < maisBarato.getValor()) {
                maisBarato = veiculo;
            }
        }
        return maisBarato;
    }

    /**
     * Get total number of vehicles in stock.
     */
    public int obterTotalVeiculos() {
        return veiculos.size();
    }
}

