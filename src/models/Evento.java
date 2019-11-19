package models;

import java.util.Date;

/**
 *
 * @author Pedro
 */
public class Evento {
    private int id;
    private String nome;
    private Date dataEvento;

    public Evento(String nome, Date dataEvento){
        this.setNome(nome);
        this.setDataEvento(dataEvento);
    }

    public Evento() {
        
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

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }
    
}
