package com.github.vinnyda23;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Service class for managing Usuario data.
 * Implements CRUD operations for both Admin and Client users.
 * All data is stored in memory using ArrayList.
 */
public class UsuarioService {
    private List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * CREATE: Add a new user to the system.
     */
    public void adicionarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    /**
     * READ: Get all users.
     */
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    /**
     * READ: Get user by index.
     */
    public Usuario obterPorIndice(int indice) {
        if (indice >= 0 && indice < usuarios.size()) {
            return usuarios.get(indice);
        }
        return null;
    }

    /**
     * UPDATE: Update user at given index.
     */
    public boolean atualizarUsuario(int indice, Usuario usuarioAtualizado) {
        if (indice >= 0 && indice < usuarios.size() && usuarioAtualizado != null) {
            usuarios.set(indice, usuarioAtualizado);
            return true;
        }
        return false;
    }

    /**
     * DELETE: Remove user by index.
     */
    public boolean removerUsuario(int indice) {
        if (indice >= 0 && indice < usuarios.size()) {
            usuarios.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * SEARCH: Generic search method using Predicate for code reuse.
     */
    public List<Usuario> buscar(Predicate<Usuario> criterio) {
        List<Usuario> resultados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (criterio.test(usuario)) {
                resultados.add(usuario);
            }
        }
        return resultados;
    }

    /**
     * SEARCH: Find users by name.
     */
    public List<Usuario> buscarPorNome(String nome) {
        return buscar(u -> u.getNome().toLowerCase().contains(nome.toLowerCase()));
    }

    /**
     * SEARCH: Find users by email.
     */
    public List<Usuario> buscarPorEmail(String email) {
        return buscar(u -> u.getEmail().equalsIgnoreCase(email));
    }

    /**
     * SEARCH: Find users by type (ADMIN or CLIENTE).
     */
    public List<Usuario> buscarPorTipo(String tipo) {
        return buscar(u -> u.obterTipo().equals(tipo.toUpperCase()));
    }

    /**
     * SEARCH: Find users by phone.
     */
    public List<Usuario> buscarPorTelefone(String telefone) {
        return buscar(u -> u.getTelefone().contains(telefone));
    }

    /**
     * AUTHENTICATION: Validate user login.
     */
    public Usuario autenticar(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Get total number of users.
     */
    public int obterTotalUsuarios() {
        return usuarios.size();
    }

    /**
     * Get count of users by type.
     */
    public int contarPorTipo(String tipo) {
        return (int) buscarPorTipo(tipo).size();
    }
}

