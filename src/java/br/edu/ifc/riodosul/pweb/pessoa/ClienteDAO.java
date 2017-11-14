
package br.edu.ifc.riodosul.pweb.pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    private Connection con = null;

    public ClienteDAO() {
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
    
    public void incluir(Cliente cliente) {
        String sql = "INSERT INTO usuario "
                + "(nome, login, senha, email) "
                + "VALUES"
                + "('" + cliente.getNome() + "',"
                +"'" + cliente.getLogin() + "',"
                +"'" + cliente.getEmail() + "',"
                +"'" + cliente.getSenha() + "');";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alterar(Cliente cliente) {
        String sql = "UPDATE usuario SET"            
                + "nome='" + cliente.getNome() 
                + "',login='" + cliente.getLogin()
                + "',senha='" + cliente.getSenha()
                + "',email='"+ cliente.getEmail() + 
                "' WHERE usuario.id = "+ cliente.getId() + ";";
        System.out.println("sql update:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Cliente> listar() {
        List<Cliente> saida = new ArrayList<Cliente>();
        String sql = "SELECT * FROM usuario WHERE admin = 0;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Cliente a = new Cliente();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setLogin(rs.getString("login"));
                a.setSenha(rs.getString("senha"));
                a.setEmail(rs.getString("email"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
    public Cliente obter(int id) {
        Cliente saida = null;
        String sql = "SELECT * FROM usuario WHERE id = "+ id + " && admin = 0;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Cliente();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
                saida.setLogin(rs.getString("login"));
                saida.setSenha(rs.getString("senha"));
                saida.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
    public void excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = "+ id + ";";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void preferir(Favorito favorito) {
        String sql = "INSERT INTO preferir_produto "
                + "(usuario_id, produto_id) "
                + "VALUES"
                + "(" + favorito.getUsuario_id() + ","
                + favorito.getProduto_id()+ ");";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluir_favorito(Favorito favorito) {
        String sql = "DELETE FROM preferir_produto WHERE usuario_id = "+ favorito.getUsuario_id() + " && produto_id = "+ favorito.getProduto_id() +";";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Favorito> listar_favoritos(int usuario_id) {
        List<Favorito> saida = new ArrayList<Favorito>();
        String sql = "SELECT * FROM prefeir_produto WHERE usuario_id = "+ usuario_id + ";";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Favorito a = new Favorito();
                a.setUsuario_id(rs.getInt("usuario_id"));
                a.setProduto_id(rs.getInt("produto_id"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
}
