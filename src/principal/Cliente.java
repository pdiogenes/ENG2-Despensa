package principal;

import DAO.Factory;
import DAO.InterfaceProduto;
import DAO.InterfaceUsuario;
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
        user.setNome("Pedro");
        user.setIdade(12);
        user.setEmail("teste@teste.com");
        user.setTelefone("(31) 9 9795-8935");
        user.setAfiliacao("NULL");

        InterfaceUsuario iu = Factory.criarUsuarioDAO();
        iu.inserir(user);

        // teste BD Produto
        Produto item = new Produto();
        item.setNome("Banana");
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2017-11-20";
        Date date = sdf.parse(dateString);
        item.setValidade(date);
        item.setPreco(25);
        item.setGastoDiario(1);
        item.setQuantidade(5);
        item.setNumeroConsumidores(1);

        InterfaceProduto ip = Factory.criarProdutoDAO();
        ip.inserir(item);

        //fim teste
    }
}
