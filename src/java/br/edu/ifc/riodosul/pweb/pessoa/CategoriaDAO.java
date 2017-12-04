package br.edu.ifc.riodosul.pweb.pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection con = null;

    public CategoriaDAO() {
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

    public void incluir(Categoria categoria) {
        String sql = "INSERT INTO categoria "
                + "(nome) "
                + "VALUES"
                + "('" + categoria.getNome() + "');";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Categoria> listar() {
        List<Categoria> saida = new ArrayList<Categoria>();
        String sql = "SELECT * FROM categoria;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Categoria a = new Categoria();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Categoria obter(int id) {
        Categoria saida = null;
        String sql = "SELECT * FROM categoria WHERE id = " + id + ";";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Categoria();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
        public void excluir(int id) {
        String sql = "DELETE FROM categoria WHERE id = "+ id + ";";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void alterar(Categoria categoria) {
        String sql = "UPDATE categoria SET"
                + "nome='" + categoria.getNome()
                + "' WHERE id = " + categoria.getId() + ";";
        System.out.println("sql update:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
