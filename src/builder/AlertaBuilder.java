package builder;

import models.Alerta;
import java.util.ArrayList;

public abstract class AlertaBuilder{
    protected Alerta alerta;
    
    public AlertaBuilder(){
        alerta = new Alerta();
    }

    public Alerta getAlerta(){
        return alerta;
    }

    public abstract void buildMensagem(string mensagem);
    public abstract void buildLista(Arraylist<Produto> lista);

}
