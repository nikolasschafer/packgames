package br.edu.ifc.riodosul.pweb.pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {
    
    private Connection con = null;

    public AdministradorDAO() {
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
    
    public void incluir(Administrador administrador) {
        String sql = "INSERT INTO usuario "
                + "(nome, login, senha, email, admin) "
                + "VALUES"
                + "('" + administrador.getNome() + "',"
                +"'" + administrador.getLogin() + "',"
                +"'" + administrador.getEmail() + "',"
                +"'" + administrador.getSenha() + "',"
                +" 1 );";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alterar(Administrador administrador) {
        String sql = "UPDATE usuario SET"            
                + "nome='" + administrador.getNome() 
                + "',login='" + administrador.getLogin()
                + "',senha='" + administrador.getSenha()
                + "',email='"+ administrador.getEmail() + 
                "' WHERE usuario.id = "+ administrador.getId() + ";";
        System.out.println("sql update:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Administrador> listar() {
        List<Administrador> saida = new ArrayList<Administrador>();
        String sql = "SELECT * FROM usuario WHERE admin = 1;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Administrador a = new Administrador();
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
    
    public Administrador obter(int id) {
        Administrador saida = null;
        String sql = "SELECT * FROM usuario WHERE id = "+ id + " && admin = 0;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Administrador();
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
    
    public void publicar(Produto produto) {
        String sql = "INSERT INTO produto "
                + "(nome, preco, url, descricao, usuario_id, categoria) "
                + "VALUES"
                + "('" + produto.getNome() + "',"
                + produto.getPreco()+ ","
                +"'" + produto.getUrl()+ "',"
                +"'" + produto.getDescricao()+ "',"
                +"'" + produto.getUsuario_id()+ "',"
                +"'" + produto.getCategoria()+ "');";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluir_produto(int id) {
        String sql = "DELETE FROM produto WHERE id = "+ id + ";";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alterar_produto(Produto produto) {
        String sql = "UPDATE produto SET"            
                + "nome='" + produto.getNome() 
                + "',preco='" + produto.getPreco()
                + "',url='" + produto.getUrl()
                + "',descricao='"+ produto.getDescricao() 
                + "',usuario_id='"+ produto.getUsuario_id() 
                + "',categoria='"+ produto.getCategoria()+ 
                "' WHERE id = "+ produto.getId() + ";";
        System.out.println("sql update:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
