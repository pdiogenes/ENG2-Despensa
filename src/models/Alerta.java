package models;

import java.util.Date;
import java.util.ArrayList;
import models.Produto;

public class Alerta {
	private String mensagem;
	private ArrayList<Produto> lista;

	public Alerta() {
		
	}

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
		//System.out.println(this.getMensagem());
		for(Produto p : lista){
                    System.out.println(p.getNome());
                }
	}
}
