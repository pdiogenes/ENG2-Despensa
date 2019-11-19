package builder;

import java.util.Calendar;
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

    @Override
    public void buildPrevisaoFalta(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // dia atual
        int diasParaFalta = (int)(produto.getQuantidade() / produto.getGastoDiario());
        c.add(Calendar.DATE, diasParaFalta);
        produto.setPrevisaoFalta(c.getTime());
    }

    @Override
    public void buildIdUsuario(int user_id) {
        produto.setIdUsuario(user_id);
    }
}
