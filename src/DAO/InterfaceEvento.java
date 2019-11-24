package DAO;

import java.util.ArrayList;
import java.util.Date;
import models.Evento;
import models.Produto;

/**
 *
 * @author Pedro
 */
public interface InterfaceEvento {
    public void inserir(Evento evento);
    public void cancelar(Evento evento);
    public void inserirItem(Evento evento, Produto produto);
    public Evento buscarEvento(int id);
    public Evento buscarEvento(Date data);
}
