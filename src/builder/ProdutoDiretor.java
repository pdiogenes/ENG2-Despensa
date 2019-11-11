package builder;

import java.util.Date;
import models.Produto;

public class ProdutoDiretor{
    protected ProdutoBuilder builder;

    public ProdutoDiretor(ProdutoBuilder builder){
        this.builder = builder;
    }

    public void buildProduto(String nome, Date date, double preco, double gasto, double qnt, int nc){
        //builder.buildId(id);
        builder.buildNome(nome);
        builder.buildValidade(date);
        builder.buildPreco(preco);
        builder.buildGastoDiario(gasto);
        builder.buildQuantidade(qnt);
        builder.buildNumeroConsumidores(nc);
    }

    public Produto getProduto(){
        return builder.getProduto();
    }
}
