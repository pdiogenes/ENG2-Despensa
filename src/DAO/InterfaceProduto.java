package DAO;

import models.Produto;

public interface InterfaceProduto {
    public void inserir(Produto produto);
    public void remover(Produto produto);
    public Produto busca(int id);
    public Produto busca(String nome);
    public void alterar(Produto produto, double gastos);
}
