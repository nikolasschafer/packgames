package br.edu.ifc.riodosul.pweb.pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    private Connection con = null;

    public ProdutoDAO() {
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
    
    public List<Produto> listar() {
        List<Produto> saida = new ArrayList<Produto>();
        String sql = "SELECT * FROM produto;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Produto a = new Produto();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setPreco(rs.getDouble("preco"));
                a.setUrl(rs.getString("url"));
                a.setUsuario_id(rs.getInt("usuario_id"));
                a.setDescricao(rs.getString("descricao"));
                a.setCategoria(rs.getString("categoria"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
    public Produto obter(int id) {
        Produto saida = null;
        String sql = "SELECT * FROM produto WHERE id = "+ id + ";";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Produto();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
                saida.setPreco(rs.getDouble("preco"));
                saida.setUrl(rs.getString("url"));
                saida.setDescricao(rs.getString("descricao"));
                saida.setUsuario_id(rs.getInt("usuario_id"));
                saida.setCategoria(rs.getString("categoria"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
}
