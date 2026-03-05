package com.github.vinnyda23;

import java.io.*;//serve para puxar todos os imports .io
import java.util.ArrayList;

public class SistemaUsuarios {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void cadastrarUsuario(ArrayList<Usuario> usuario) {
        for (Usuario u : usuario) {
            usuarios.add(u);
        }
        System.out.println("Usuario cadastrado com sucesso!!!");


    }

    public Usuario buscarporemail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario login(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)
                    && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean emailExiste(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

}