package principal;

import DAO.Factory;
import DAO.InterfaceProduto;
import DAO.InterfaceUsuario;
import builder.ProdutoDiretor;
import builder.ProdutoPerecivelBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import models.Alerta;
import models.Produto;
import models.Usuario;

public class Cliente {
    public static void main(String[] args) throws ParseException {
        // teste BD Usuario
        Usuario user = new Usuario();
        user.setNome("Teste");
        user.setIdade(12);
        user.setEmail("teste@teste.com");
        user.setTelefone("(31) 132456");
        user.setAfiliacao("NULL");

        InterfaceUsuario iu = Factory.criarUsuarioDAO();
        iu.inserir(user);
        
        InterfaceProduto ip = Factory.criarProdutoDAO();
        ProdutoDiretor pd = new ProdutoDiretor(new ProdutoPerecivelBuilder());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2019-11-20";
        Date date = sdf.parse(dateString);
        pd.buildProduto("Maça", date, 25, 1, 5, 1);
        //ip.inserir(pd.getProduto());
        pd.buildProduto("Banana", date, 25, 1, 5, 1);
        //ip.inserir(pd.getProduto());

        ArrayList<Produto> vencimentos = ip.vencimento();
        Alerta alertaVencimento = new Alerta();
        alertaVencimento.setLista(vencimentos);
        alertaVencimento.exibir();

        //fim teste
    }
}
