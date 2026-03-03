package com.github.vinnyda23;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Service class for managing ModeloVeiculo (Vehicle Models).
 * Implements CRUD operations for vehicle models.
 * Only Admins can access this functionality.
 */
public class ModeloVeiculoService {
    private List<ModeloVeiculo> modelos;

    public ModeloVeiculoService() {
        this.modelos = new ArrayList<>();
    }

    /**
     * CREATE: Add a new vehicle model.
     */
    public void adicionarModelo(ModeloVeiculo modelo) {
        if (modelo != null) {
            modelos.add(modelo);
        }
    }

    /**
     * READ: Get all vehicle models.
     */
    public List<ModeloVeiculo> listarTodos() {
        return new ArrayList<>(modelos);
    }

    /**
     * READ: Get model by index.
     */
    public ModeloVeiculo obterPorIndice(int indice) {
        if (indice >= 0 && indice < modelos.size()) {
            return modelos.get(indice);
        }
        return null;
    }

    /**
     * UPDATE: Update model at given index.
     */
    public boolean atualizarModelo(int indice, ModeloVeiculo modeloAtualizado) {
        if (indice >= 0 && indice < modelos.size() && modeloAtualizado != null) {
            modelos.set(indice, modeloAtualizado);
            return true;
        }
        return false;
    }

    /**
     * DELETE: Remove model by index.
     */
    public boolean removerModelo(int indice) {
        if (indice >= 0 && indice < modelos.size()) {
            modelos.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * SEARCH: Generic search method using Predicate for code reuse.
     */
    public List<ModeloVeiculo> buscar(Predicate<ModeloVeiculo> criterio) {
        List<ModeloVeiculo> resultados = new ArrayList<>();
        for (ModeloVeiculo modelo : modelos) {
            if (criterio.test(modelo)) {
                resultados.add(modelo);
            }
        }
        return resultados;
    }

    /**
     * SEARCH: Find models by brand (marca).
     */
    public List<ModeloVeiculo> buscarPorMarca(String marca) {
        return buscar(m -> m.getMarca().equalsIgnoreCase(marca));
    }

    /**
     * SEARCH: Find models by category (categoria).
     */
    public List<ModeloVeiculo> buscarPorCategoria(String categoria) {
        return buscar(m -> m.getCategoria().equalsIgnoreCase(categoria));
    }

    /**
     * SEARCH: Find models by name (nome).
     */
    public List<ModeloVeiculo> buscarPorNome(String nome) {
        return buscar(m -> m.getNome().equalsIgnoreCase(nome));
    }

    /**
     * SEARCH: Find models by price range.
     */
    public List<ModeloVeiculo> buscarPorFaixaPreco(double precoMinimo, double precoMaximo) {
        return buscar(m -> m.getPrecoBase() >= precoMinimo && m.getPrecoBase() <= precoMaximo);
    }

    /**
     * BUSINESS REPORT: Calculate average price of all models.
     */
    public double calcularPrecoMedio() {
        if (modelos.isEmpty()) {
            return 0;
        }
        double soma = 0;
        for (ModeloVeiculo modelo : modelos) {
            soma += modelo.getPrecoBase();
        }
        return soma / modelos.size();
    }

    /**
     * BUSINESS REPORT: Calculate total value of all models.
     */
    public double calcularValorTotalModelos() {
        double total = 0;
        for (ModeloVeiculo modelo : modelos) {
            total += modelo.getPrecoBase();
        }
        return total;
    }

    /**
     * BUSINESS REPORT: Get the most expensive model.
     */
    public ModeloVeiculo obterModeloMaisCaro() {
        if (modelos.isEmpty()) {
            return null;
        }
        ModeloVeiculo maisCaro = modelos.get(0);
        for (ModeloVeiculo modelo : modelos) {
            if (modelo.getPrecoBase() > maisCaro.getPrecoBase()) {
                maisCaro = modelo;
            }
        }
        return maisCaro;
    }

    /**
     * BUSINESS REPORT: Get the cheapest model.
     */
    public ModeloVeiculo obterModeloMaisBarato() {
        if (modelos.isEmpty()) {
            return null;
        }
        ModeloVeiculo maisBarato = modelos.get(0);
        for (ModeloVeiculo modelo : modelos) {
            if (modelo.getPrecoBase() < maisBarato.getPrecoBase()) {
                maisBarato = modelo;
            }
        }
        return maisBarato;
    }

    /**
     * Get total number of models.
     */
    public int obterTotalModelos() {
        return modelos.size();
    }

    /**
     * Count models by brand.
     */
    public int contarPorMarca(String marca) {
        return (int) buscarPorMarca(marca).size();
    }
}

