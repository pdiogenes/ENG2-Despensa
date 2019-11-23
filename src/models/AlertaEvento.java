package models;

import models.AlertaAbstrato;

/**
 *
 * @author Pedro
 */
public class AlertaEvento extends AlertaAbstrato{
    public AlertaEvento(){
        this.setMensagem("Os seguintes itens fazem parte de um evento que irá ocorrer nessa semana: ");
    }
}
