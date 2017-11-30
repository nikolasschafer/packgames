package br.edu.ifc.riodosul.pweb.pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection con = null;

    public UsuarioDAO() {
        try {
            //org.hsqldb.jdbcDriver
            Class.forName("com.mysql.jdbc.Driver").
                    newInstance();
            //jdbc:hsqldb:file:C:/programa/hsqldb/data/ex_aluno
            con = DriverManager.
                    getConnection("jdbc:mysql://localhost/"
                            + "packgames",
                            "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incluir(Usuario usuario) {
        String sql = "INSERT INTO usuario "
                + "(nome, login, senha, email, admin) "
                + "VALUES"
                + "('" + usuario.getNome() + "',"
                + "'" + usuario.getLogin() + "',"
                + "'" + usuario.getEmail() + "',"
                + "'" + usuario.getSenha() + "',"
                + "'" + usuario.getAdmin() + "');";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET"
                + "nome='" + usuario.getNome()
                + "',login='" + usuario.getLogin()
                + "',senha='" + usuario.getSenha()
                + "',email='" + usuario.getEmail()
                + "',admin='" + usuario.getAdmin()
                + "' WHERE usuario.id = " + usuario.getId() + ";";
        System.out.println("sql update:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> saida = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Usuario a = new Usuario();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setLogin(rs.getString("login"));
                a.setSenha(rs.getString("senha"));
                a.setEmail(rs.getString("email"));
                a.setAdmin(rs.getInt("admin"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Usuario obter(int id) {
        Usuario saida = null;
        String sql = "SELECT * FROM usuario WHERE id = " + id + " ;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Usuario();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
                saida.setLogin(rs.getString("login"));
                saida.setSenha(rs.getString("senha"));
                saida.setEmail(rs.getString("email"));
                saida.setAdmin(rs.getInt("admin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Usuario login(String login, String senha) {
        Usuario saida = null;
        String sql = "SELECT * FROM usuario WHERE login = '" + login + "' && senha = '" + senha + "';";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Usuario();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
                saida.setLogin(rs.getString("login"));
                saida.setSenha(rs.getString("senha"));
                saida.setEmail(rs.getString("email"));
                saida.setAdmin(rs.getInt("admin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = " + id + ";";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
