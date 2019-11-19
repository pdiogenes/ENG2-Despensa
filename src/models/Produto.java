package models;

import java.util.Date;

public class Produto {
    private int id;
    private String nome;
    private Date validade;
    private Date previsaoFalta;
    private double preco;
    private double gastoDiario;
    private double quantidade;
    private int numeroConsumidores;
    private int idUsuario;

    

    public Produto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getGastoDiario() {
        return gastoDiario;
    }

    public void setGastoDiario(double gastoDiario) {
        this.gastoDiario = gastoDiario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public int getNumeroConsumidores() {
        return numeroConsumidores;
    }

    public void setNumeroConsumidores(int numeroConsumidores) {
        this.numeroConsumidores = numeroConsumidores;
    }
    
    public Date getPrevisaoFalta() {
        return previsaoFalta;
    }

    public void setPrevisaoFalta(Date previsaoFalta) {
        this.previsaoFalta = previsaoFalta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


}
