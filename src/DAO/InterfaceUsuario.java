package DAO;

import models.Usuario;

public interface InterfaceUsuario {
    public void inserir(Usuario usuario);
    public void remover(Usuario usuario);
    public Usuario busca(int id);
    public Usuario busca(String nome);
}
