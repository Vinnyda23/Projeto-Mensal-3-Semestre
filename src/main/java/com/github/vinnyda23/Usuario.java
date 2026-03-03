package com.github.vinnyda23;

/**
 * Abstract class representing a generic user in the system.
 * Implements different user types through inheritance (Admin, Client).
 */
public abstract class Usuario {
    private String nome;
    private String senha;
    private String endereco;
    private String email;
    private String telefone;

    public Usuario(String nome, String senha, String endereco, String email, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Abstract method for calculating discount based on user type.
     * Different user types (Admin, Client) will implement differently.
     */
    public abstract double calcularDesconto(double valor);

    /**
     * Abstract method to get user role.
     */
    public abstract String obterTipo();

    @Override
    public String toString() {
        return String.format("Nome: %s | Email: %s | Telefone: %s | Endereço: %s",
                nome, email, telefone, endereco);
    }
}

