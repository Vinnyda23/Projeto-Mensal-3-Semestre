package com.github.vinnyda23;

/**
 * Admin user class implementing Usuario abstract class.
 * Admin users get special discounts (10% off).
 * Demonstrates polymorphism through overriding abstract methods.
 */
public class Admin extends Usuario {
    private String departamento;

    public Admin(String nome, String senha, String endereco, String email, String telefone, String departamento) {
        super(nome, senha, endereco, email, telefone);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Admin users get 10% discount on all purchases.
     * Polymorphic implementation of abstract method.
     */
    @Override
    public double calcularDesconto(double valor) {
        return valor * 0.10; // 10% discount
    }

    /**
     * Returns the user type as Admin.
     */
    @Override
    public String obterTipo() {
        return "ADMIN";
    }

    @Override
    public String toString() {
        return String.format("%s | Tipo: %s | Departamento: %s",
                super.toString(), obterTipo(), departamento);
    }
}

