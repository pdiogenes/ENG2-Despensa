package principal;

import DAO.Factory;
import DAO.InterfaceEvento;
import DAO.InterfaceProduto;
import DAO.InterfaceUsuario;
import builder.ProdutoDiretor;
import builder.ProdutoPerecivelBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import models.Alerta;
import models.Evento;
import models.Produto;
import models.Usuario;

public class Cliente {
    static Usuario logado;
    
    public static void main(String[] args) throws ParseException {
        InterfaceUsuario iu = Factory.criarUsuarioDAO();
        InterfaceProduto ip = Factory.criarProdutoDAO();
        InterfaceEvento ie = Factory.criarEventoDAO();
        
        logado = iu.busca("teste@teste.com");
        
        // teste BD Usuario
        Usuario user = new Usuario();
        user.setNome("Teste");
        user.setIdade(12);
        user.setEmail("teste@teste.com");
        user.setTelefone("(31) 132456");
        user.setAfiliacao("NULL");

        
        //iu.inserir(user);
        
        
        ProdutoDiretor pd = new ProdutoDiretor(new ProdutoPerecivelBuilder());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2019-11-20";
        Date date = sdf.parse(dateString);
        pd.buildProduto("Maça", date, 25, 1, 5, 1, logado.getId());
        //ip.inserir(pd.getProduto());
        pd.buildProduto("Banana", date, 25, 1, 5, 1, logado.getId());
        ip.inserir(pd.getProduto());
        
        Produto p1 = ip.busca(2);
        Produto p2 = ip.busca(3);
        Evento evento = new Evento("Festa", new Date());
        //ie.inserir(evento);
        
        evento = ie.buscarEvento(1);
        
        ie.inserirItem(evento, p1);
        ie.inserirItem(evento, p2);
        
        ArrayList<Produto> produtosEvento = ip.buscarItensEvento(evento);
        
        for(Produto p : produtosEvento){
            System.out.println(p.getNome());
        }
        

        /*ArrayList<Produto> vencimentos = ip.vencimento();
        Alerta alertaVencimento = new Alerta();
        alertaVencimento.setLista(vencimentos);
        alertaVencimento.exibir();*/

        //fim teste
    }
}
