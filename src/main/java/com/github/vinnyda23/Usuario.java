package com.github.vinnyda23;

import java.util.ArrayList;

public class Usuario {
    private String login;
    private String senha;
    private String endereco;
    private String email;
    private String telefone;
    private String tipo;

    private ArrayList<Veiculo> veiculosComprados = new ArrayList<>();

    //constructor
    public Usuario(String login, String senha, String endereco, String email, String telefone, String tipo) {
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void adicionarVeiculos(){
        for(Veiculo v : veiculosComprados){
            System.out.println(v.getModelo() + " - R$ " + v.getValor());
        }
    }

}
