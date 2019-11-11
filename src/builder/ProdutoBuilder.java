package builder;

import java.util.Date;
import models.Produto;

public abstract class ProdutoBuilder{
    protected Produto produto;
    
    public ProdutoBuilder(){
        produto = new Produto();
    }

    public Produto getProduto(){
        return produto;
    }

    public abstract void buildId(int id);
    public abstract void buildNome(String nome);
    public abstract void buildValidade(Date date);
    public abstract void buildPreco(double preco);
    public abstract void buildGastoDiario(double gasto);
    public abstract void buildQuantidade(double qnt);
    public abstract void buildNumeroConsumidores(int nc);
}
