package models;

import models.AlertaAbstrato;

/**
 *
 * @author Pedro
 */
public class AlertaFalta extends AlertaAbstrato{
    public AlertaFalta(){
        this.setMensagem("Os seguintes itens estão em falta/estarão em 3 dias: "); 
    }
}
