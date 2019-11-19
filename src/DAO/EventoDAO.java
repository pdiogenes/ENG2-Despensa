package DAO;

import connections.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Evento;
import models.Produto;

/**
 *
 * @author Pedro
 */
public class EventoDAO implements InterfaceEvento{

    @Override
    public void inserir(Evento evento) {
        try(Connection con = new mysql().conecta()) {
            String sql = "insert into evento " +
                "(id, nome, data)" +
                " values (NULL, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, evento.getNome());
            stmt.setDate(2, new java.sql.Date(evento.getDataEvento().getTime()));
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void cancelar(Evento evento) {
        try(Connection con = new mysql().conecta()) {

            int id = evento.getId();
            String sql = "delete from produto_evento " +
                "where evento_id = ?";
            PreparedStatement stmt1 = con.prepareStatement(sql);
            stmt1.setInt(1, id);
            stmt1.execute();
            stmt1.close();
            sql = "delete from evento " +
                "where id = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql);
            stmt2.setInt(1, id);
            stmt2.execute();
            stmt2.close();

        } catch(SQLException e) {
            System.out.println(e);
        }
    }



    @Override
    public void inserirItem(Evento evento, Produto produto) {
        try(Connection con = new mysql().conecta()) {
            String sql = "insert into produto_evento " +
                "(id_produto, id_evento)" +
                " values (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.setInt(2, evento.getId());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Evento buscarEvento(int id) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from evento " +
                "where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getInt("id"));
                evento.setNome(rs.getString("nome"));
                
                java.sql.Date dbSqlDate = rs.getDate("data");
                java.util.Date dataEvento = new java.util.Date(dbSqlDate.getTime());
                evento.setDataEvento(dataEvento);

                return evento;
            } else {
                return null;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

}
