package DAO;

import connections.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import models.Produto;
import java.util.*;

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
    public void alterar(Produto produto, double gastos){
        try(Connection con = new mysql().conecta()) {
            int id = produto.getId();
            String sql = "update produto set gastoDiario = ? " +
                "where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, gastos);
            stmt.setInt(2, id);
            if(stmt.execute()){
                //System.out.println("Item de id " + id + " alterado.");
            } //else System.out.println("Falha ao alterar.");
            stmt.close();

        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void remover(Produto produto) {
        try(Connection con = new mysql().conecta()) {
            int id = produto.getId();
            String sql = "delete from produto " +
                "where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            if(stmt.execute()){
                //System.out.println("Item de id " + id + " removido.");
            } //else System.out.println("Falha ao remover.");
            stmt.close();

        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<Produto> busca(int id) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto " +
                "where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produto> resultado = new ArrayList<Produto>();

            while(rs.next()){
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

                resultado.add(produto);

            }

            if(resultado.size() > 0) return resultado;
            else return null;
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ArrayList<Produto> busca(String nome) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto " +
                "where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produto> resultado = new ArrayList<Produto>();
            while(rs.next()){
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

                resultado.add(produto);
            }
            if(resultado.size() > 0) return resultado;
            else return null;
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ArrayList<Produto> vencimento() {
        try(Connection con = new mysql().conecta()) {


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date()); //hoje
            String hoje = sdf.format(c.getTime());

            c.add(Calendar.DATE, 3); // daqui a 3 dias
            String depois = sdf.format(c.getTime());

            String sql = "select * from produto " +
                "where validade between ? and ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hoje);
            stmt.setString(2, depois);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produto> resultado = new ArrayList<Produto>();
            while(rs.next()){
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

                resultado.add(produto);
            }
            if(resultado.size() > 0) return resultado;
            else return null;
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;

    }

    @Override
    public ArrayList<Produto> falta() {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto " +
                "where quantidade = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, 0);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produto> resultado = new ArrayList<Produto>();
            
            while(rs.next()){
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

                resultado.add(produto);
            }
            if(resultado.size() > 0) return resultado;
            else return null;
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;

    }
}
