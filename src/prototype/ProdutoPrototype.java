package prototype;
import java.util.Date;

public abstract class ProdutoPrototype{
    protected String nome;
    protected Date dataValidade;
    protected double qnt;

    public abstract String exibirInfo();
    public abstract ProdutoPrototype clonar();

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setData(Date data){
        this.dataValidade = data;
    }

    public void setQnt(double qnt){
        this.qnt = qnt;
    }

    public String getNome(){
        return this.nome;
    }

    public Date getData(){
        return this.dataValidade;
    }

    public double getQnt(){
        return this.qnt;
    }
}
