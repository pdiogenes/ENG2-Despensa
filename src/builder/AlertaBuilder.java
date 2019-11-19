package builder;

import models.Alerta;
import java.util.ArrayList;
import models.Produto;

public abstract class AlertaBuilder{
    protected Alerta alerta;
    
    public AlertaBuilder(){
        alerta = new Alerta();
    }

    public Alerta getAlerta(){
        return alerta;
    }

    public abstract void buildMensagem(String mensagem);
    public abstract void buildLista(ArrayList<Produto> lista);

}
