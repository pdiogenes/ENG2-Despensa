package DAO;

import java.util.ArrayList;
import models.Evento;
import models.Produto;

public interface InterfaceProduto {
    public void inserir(Produto produto);
    public void remover(Produto produto);
    public Produto busca(int id);
    public ArrayList<Produto> busca(String nome);
    public ArrayList<Produto> vencimento();
    public ArrayList<Produto> falta();
    public ArrayList<Produto> buscarItensEvento(Evento evento);
    public void alterar(Produto produto, double gastos);
}
