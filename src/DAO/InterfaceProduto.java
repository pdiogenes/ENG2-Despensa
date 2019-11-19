package DAO;

import java.util.ArrayList;
import models.Produto;

public interface InterfaceProduto {
    public void inserir(Produto produto);
    public void remover(Produto produto);
    public ArrayList<Produto> busca(int id);
    public ArrayList<Produto> busca(String nome);
    public ArrayList<Produto> vencimento();
    public ArrayList<Produto> falta();
    public void alterar(Produto produto, double gastos);
}
