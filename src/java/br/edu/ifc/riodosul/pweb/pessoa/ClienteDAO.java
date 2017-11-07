
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
    
}
