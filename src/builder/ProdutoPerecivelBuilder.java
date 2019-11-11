package builder;

import java.util.Date;

public class ProdutoPerecivelBuilder extends ProdutoBuilder{
    @Override
    public void buildId(int id){
        produto.setId(id);
    }

    @Override
    public void buildNome(String nome){
        produto.setNome(nome);
    }

    @Override
    public void buildValidade(Date date){
        produto.setValidade(date);
    }

    @Override
    public void buildPreco(double preco){
        produto.setPreco(preco);
    }

    @Override
    public void buildGastoDiario(double gasto){
        produto.setGastoDiario(gasto);
    }

    @Override
    public void buildQuantidade(double qnt){
        produto.setQuantidade(qnt);
    }

    @Override
    public void buildNumeroConsumidores(int nc){
        produto.setNumeroConsumidores(nc);
    }
}
