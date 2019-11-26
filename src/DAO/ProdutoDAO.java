package DAO;

import connections.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import models.Produto;
import java.util.*;
import models.Evento;

/**
 *
 * @author Pedro
 */
public class ProdutoDAO implements InterfaceProduto{

    @Override
    public void inserir(Produto produto) {
        try(Connection con = new mysql().conecta()) {
            Produto p = this.busca(produto.getNome());
            if(p == null){
                String sql = "insert into produto " +
                "(id, nome, validade, preco, gasto_diario, quantidade, numero_consumidores, previsao_falta, id_usuario)" +
                " values (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, produto.getNome());
                stmt.setDate(2, new java.sql.Date(produto.getValidade().getTime()));
                stmt.setDouble(3, produto.getPreco());
                stmt.setDouble(4, produto.getGastoDiario());
                stmt.setDouble(5, produto.getQuantidade());
                stmt.setInt(6, produto.getNumeroConsumidores());
                stmt.setDate(7, new java.sql.Date(produto.getPrevisaoFalta().getTime()));
                stmt.setInt(8, produto.getIdUsuario());
                stmt.execute();
                stmt.close();
            } else{
                this.alterar(p, produto);
            }
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
    public void alterar(Produto produto, Produto produtoAlterado){
         try(Connection con = new mysql().conecta()) {
            String sql = "update produto set nome = ?, validade = ?, previsao_falta = ?, " +
                " preco = ?, gasto_diario = ?, quantidade = ?, numero_consumidores = ? " +
                "where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produtoAlterado.getNome());
            stmt.setDate(2, new java.sql.Date(produtoAlterado.getValidade().getTime()));
            stmt.setDate(3, new java.sql.Date(produtoAlterado.getPrevisaoFalta().getTime()));
            stmt.setDouble(4, produtoAlterado.getPreco());
            stmt.setDouble(5, produtoAlterado.getGastoDiario());
            stmt.setDouble(6, produtoAlterado.getQuantidade());
            stmt.setInt(7, produtoAlterado.getNumeroConsumidores());
            stmt.setInt(8, produto.getId());
            stmt.execute();
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
    public Produto busca(int id) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto " +
                "where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Produto produto =  new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));

                java.sql.Date dbSqlDate = rs.getDate("validade");
                java.util.Date dataValidade = new java.util.Date(dbSqlDate.getTime());
                produto.setValidade(dataValidade);

                produto.setGastoDiario(rs.getDouble("gasto_diario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numero_consumidores"));
                
                java.sql.Date dbSqlDate2 = rs.getDate("previsao_falta");
                java.util.Date dataFalta = new java.util.Date(dbSqlDate2.getTime());
                produto.setPrevisaoFalta(dataFalta);
                
                produto.setIdUsuario(rs.getInt("id_usuario"));

                return produto;

            } else return null;
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

            if(rs.next()){
                Produto produto =  new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));

                java.sql.Date dbSqlDate = rs.getDate("validade");
                java.util.Date dataValidade = new java.util.Date(dbSqlDate.getTime());
                produto.setValidade(dataValidade);

                produto.setGastoDiario(rs.getDouble("gasto_diario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numero_consumidores"));
                
                java.sql.Date dbSqlDate2 = rs.getDate("previsao_falta");
                java.util.Date dataFalta = new java.util.Date(dbSqlDate2.getTime());
                produto.setPrevisaoFalta(dataFalta);
                
                produto.setIdUsuario(rs.getInt("id_usuario"));

                return produto;

            } else return null;
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    /*@Override
    public ArrayList<Produto> busca(String nome) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto " +
                "where id = ?";
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

                produto.setGastoDiario(rs.getDouble("gasto_diario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numero_consumidores"));
                
                java.sql.Date dbSqlDate2 = rs.getDate("previsao_falta");
                java.util.Date dataFalta = new java.util.Date(dbSqlDate2.getTime());
                produto.setValidade(dataFalta);
                
                produto.setIdUsuario(rs.getInt("id_usuario"));

                resultado.add(produto);
            }
            if(resultado.size() > 0) return resultado;
            else return null;
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }*/

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

                produto.setGastoDiario(rs.getDouble("gasto_diario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numero_consumidores"));
                
                java.sql.Date dbSqlDate2 = rs.getDate("previsao_falta");
                java.util.Date dataFalta = new java.util.Date(dbSqlDate2.getTime());
                produto.setPrevisaoFalta(dataFalta);
                
                produto.setIdUsuario(rs.getInt("id_usuario"));

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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date()); //hoje
            String hoje = sdf.format(c.getTime());

            c.add(Calendar.DATE, 3); // daqui a 3 dias
            String depois = sdf.format(c.getTime());

            String sql = "select * from produto " +
                "where previsao_falta between ? and ?";
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

                produto.setGastoDiario(rs.getDouble("gasto_diario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numero_consumidores"));
                
                java.sql.Date dbSqlDate2 = rs.getDate("previsao_falta");
                java.util.Date dataFalta = new java.util.Date(dbSqlDate2.getTime());
                produto.setPrevisaoFalta(dataFalta);
                
                produto.setIdUsuario(rs.getInt("id_usuario"));

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
    public ArrayList<Produto> buscarItensEvento(Evento evento) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from produto_evento " +
                "where id_evento = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, evento.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produto> resultado = new ArrayList<>();

            while(rs.next()){
                resultado.add(this.busca(rs.getInt("id_produto")));
            }

            if(resultado.size() > 0) return resultado;
            else return null;
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public ArrayList<Produto> buscarProdutos() {
         try(Connection con = new mysql().conecta()) {
            ArrayList<Produto> retorno = new ArrayList<>(0);
            String sql = "select * from produto";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                java.sql.Date dbSqlDate = rs.getDate("validade");
                java.util.Date dataValidade = new java.util.Date(dbSqlDate.getTime());
                produto.setValidade(dataValidade);
                produto.setGastoDiario(rs.getDouble("gasto_diario"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setNumeroConsumidores(rs.getInt("numero_consumidores"));
                java.sql.Date dbSqlDate2 = rs.getDate("previsao_falta");
                java.util.Date dataFalta = new java.util.Date(dbSqlDate2.getTime());
                produto.setPrevisaoFalta(dataFalta);   
                produto.setIdUsuario(rs.getInt("id_usuario"));
                retorno.add(produto);
            }
            return retorno;
        }catch(SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
