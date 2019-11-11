package prototype;

import java.util.Date;

public class ProdutoLista extends ProdutoPrototype{
    protected ProdutoLista(ProdutoLista pl){
        this.nome = pl.getNome();
        this.dataValidade = pl.getData();
        this.qnt = pl.getQnt();
    }

    public ProdutoLista(){
        this.nome = "";
        this.dataValidade = new Date();
        this.qnt = 0;
    }

    @Override
    public String exibirInfo(){
        //implementar de acordo com necessidade
        return "";
    }

    @Override
    public ProdutoPrototype clonar(){
        return new ProdutoLista(this);
    }
}
