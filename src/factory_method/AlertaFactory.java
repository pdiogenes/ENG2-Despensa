package factory_method;

import models.AlertaAbstrato;
import models.AlertaEvento;
import models.AlertaFalta;
import models.AlertaVencimento;
import models.ListaCompras;

/**
 *
 * @author Pedro
 */
public class AlertaFactory {
    public static AlertaAbstrato criarAlerta(String tipoAlerta){
        
        if( tipoAlerta == null ) return null;
        else if( tipoAlerta.equals("falta") ) return new AlertaFalta();
        else if( tipoAlerta.equals("vencimento") ) return new AlertaVencimento();
        else if( tipoAlerta.equals("evento") ) return new AlertaEvento();
        else if( tipoAlerta.equals("lista") ) return new ListaCompras();
        else return null;
        
    }
}
