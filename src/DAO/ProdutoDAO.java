package DAO;

import connections.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Produto;

/**
 *
 * @author Pedro
 */
public class ProdutoDAO implements InterfaceProduto{

    @Override
    public void inserir(Produto produto) {
        try(Connection con = new mysql().conecta()) {
            String sql = "insert into produto " +
                "(id, nome, validade, preco, gastoDiario, quantidade, numeroConsumidores)" +
                " values (NULL, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDate(2, new java.sql.Date(produto.getValidade().getTime()));
            stmt.setDouble(3, produto.getPreco());
            stmt.setDouble(4, produto.getGastoDiario());
            stmt.setDouble(5, produto.getQuantidade());
            stmt.setInt(6, produto.getNumeroConsumidores());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void remover(Produto produto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produto busca(int id) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto " +
                "where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produto produto =  new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));

                java.sql.Date dbSqlDate = rs.getDate("validade");
                java.util.Date dataValidade = new java.util.Date(dbSqlDate.getTime());
                produto.setValidade(dataValidade);

                produto.setGastoDiario(rs.getDouble("gastoDiario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numeroConsumidores"));

                return produto;
            } else {
                return null;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Produto busca(String nome) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto " +
                "where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produto produto =  new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));

                java.sql.Date dbSqlDate = rs.getDate("validade");
                java.util.Date dataValidade = new java.util.Date(dbSqlDate.getTime());
                produto.setValidade(dataValidade);

                produto.setGastoDiario(rs.getDouble("gastoDiario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numeroConsumidores"));

                return produto;
            } else {
                return null;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

}
