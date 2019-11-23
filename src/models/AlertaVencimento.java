package models;

import models.AlertaAbstrato;

/**
 *
 * @author Pedro
 */
public class AlertaVencimento extends AlertaAbstrato{
    public AlertaVencimento(){
        this.setMensagem("Os seguintes itens venceram/irão vencer em 3 dias: "); 
    }
}
