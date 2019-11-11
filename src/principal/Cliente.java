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

        ProdutoDiretor pd = new ProdutoDiretor(new ProdutoPerecivelBuilder());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2017-11-20";
        Date date = sdf.parse(dateString);
        pd.buildProduto("Maça", date, 25, 1, 5, 1);
        InterfaceProduto ip = Factory.criarProdutoDAO();
        ip.inserir(pd.getProduto());

        Produto prodAlterar = ip.busca(1);
        System.out.println(prodAlterar.getNome());


        ip.alterar(prodAlterar, 3);
        ip.remover(prodAlterar);

        //fim teste
    }
}
