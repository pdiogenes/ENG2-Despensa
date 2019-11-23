package models;

import java.util.Date;
import java.util.ArrayList;
import models.Produto;

public abstract class AlertaAbstrato {
	private String mensagem;
	private ArrayList<Produto> lista;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ArrayList getLista() {
		return lista;
	}

	public void setLista(ArrayList<Produto> lista) {
		this.lista = lista;
	}

	public void exibir() {
            if(this.getLista().size() > 0){
		System.out.println(this.getMensagem());
		for(Produto p : lista){
                    System.out.println(p.getNome());
                }
            } else {
                //System.out.println("");
            }
	}
}
