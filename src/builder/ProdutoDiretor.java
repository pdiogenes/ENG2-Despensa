package builder;

import java.util.Date;
import models.Produto;

public class ProdutoDiretor{
    protected ProdutoBuilder builder;

    public ProdutoDiretor(ProdutoBuilder builder){
        this.builder = builder;
    }

    public void buildProduto(String nome, Date date, double preco, double gasto, double qnt, int nc, int user_id){
        //builder.buildId(id);
        builder.buildNome(nome);
        builder.buildValidade(date);
        builder.buildPreco(preco);
        builder.buildGastoDiario(gasto);
        builder.buildQuantidade(qnt);
        builder.buildNumeroConsumidores(nc);
        builder.buildPrevisaoFalta();
        builder.buildIdUsuario(nc);
    }

    public Produto getProduto(){
        return builder.getProduto();
    }
}
