package DAO;

import connections.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Usuario;

/**
 *
 * @author Pedro
 */
public class UsuarioDAO implements InterfaceUsuario{

    @Override
    public void inserir(Usuario usuario) {
        try(Connection con = new mysql().conecta()) {
            String sql = "insert into usuario " +
                "(id, nome, idade, email, telefone, afiliacao)" +
                " values (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getIdade());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getAfiliacao());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void remover(Usuario usuario) {

    }

    @Override
    public Usuario busca(String email) {
        try(Connection con = new mysql().conecta()) {
            String sql = "select * from usuario " +
                "where email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario =  new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setIdade(rs.getInt("idade"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setAfiliacao(rs.getString("afiliacao"));

                return usuario;
            } else {
                return null;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Usuario busca(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
