package com.github.vinnyda23;

/**
 * Client user class implementing Usuario abstract class.
 * Client users get discounts based on purchase amount.
 * Demonstrates polymorphism through overriding abstract methods.
 */
public class Cliente extends Usuario {
    private String cpf;
    private int numeroCompras;

    public Cliente(String nome, String senha, String endereco, String email, String telefone, String cpf) {
        super(nome, senha, endereco, email, telefone);
        this.cpf = cpf;
        this.numeroCompras = 0;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumeroCompras() {
        return numeroCompras;
    }

    public void setNumeroCompras(int numeroCompras) {
        this.numeroCompras = numeroCompras;
    }

    /**
     * Client users get discount based on purchase amount:
     * - Purchases up to R$ 10,000: 0% discount
     * - Purchases R$ 10,001 to R$ 50,000: 5% discount
     * - Purchases above R$ 50,000: 8% discount
     * Polymorphic implementation of abstract method.
     */
    @Override
    public double calcularDesconto(double valor) {
        if (valor > 50000) {
            return valor * 0.08; // 8% discount
        } else if (valor > 10000) {
            return valor * 0.05; // 5% discount
        }
        return 0; // No discount for small purchases
    }

    /**
     * Returns the user type as Client.
     */
    @Override
    public String obterTipo() {
        return "CLIENTE";
    }

    @Override
    public String toString() {
        return String.format("%s | Tipo: %s | CPF: %s | Compras: %d",
                super.toString(), obterTipo(), cpf, numeroCompras);
    }
}

