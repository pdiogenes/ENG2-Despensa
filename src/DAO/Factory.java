package DAO;

/**
 *
 * @author Pedro
 */
public class Factory {
    public static InterfaceProduto criarProdutoDAO(){
        return new ProdutoDAO();
    }
    
    public static InterfaceUsuario criarUsuarioDAO(){
        return new UsuarioDAO();
    }
}
