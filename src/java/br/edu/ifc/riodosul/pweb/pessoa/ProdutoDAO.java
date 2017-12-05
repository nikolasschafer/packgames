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
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            while (rs.next()) {
                Produto a = new Produto();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setPreco(rs.getDouble("preco"));
                a.setUrl(rs.getString("url"));
                a.setUsuario_id(rs.getInt("usuario_id"));
                a.setDescricao(rs.getString("descricao"));
                a.setCategoria_id(rs.getInt("categoria_id"));
                                //referencia             
                a.setCategoria(
                        categoriaDAO.obter(
                                a.getCategoria_id()));
                //
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Produto obter(int id) {
        Produto saida = null;
        String sql = "SELECT * FROM produto WHERE id = " + id + ";";
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
                saida.setCategoria_id(rs.getInt("categoria_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public void publicar(Produto produto) {
        String sql = "INSERT INTO produto "
                + "(nome, preco, url, descricao, usuario_id, categoria_id) "
                + "VALUES"
                + "('" + produto.getNome() + "',"
                + produto.getPreco() + ","
                + "'" + produto.getUrl() + "',"
                + "'" + produto.getDescricao() + "',"
                + "'" + produto.getUsuario_id() + "',"
                + "'" + produto.getCategoria_id() + "');";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = " + id + ";";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar(Produto produto) {
        String sql = "UPDATE produto SET"
                + "nome='" + produto.getNome()
                + "',preco='" + produto.getPreco()
                + "',url='" + produto.getUrl()
                + "',descricao='" + produto.getDescricao()
                + "',usuario_id='" + produto.getUsuario_id()
                + "',categoria_id='" + produto.getCategoria_id()
                + "' WHERE id = " + produto.getId() + ";";
        System.out.println("sql update:" + sql);
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
                + favorito.getProduto_id() + ");";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir_favorito(Favorito favorito) {
        String sql = "DELETE FROM preferir_produto WHERE usuario_id = " + favorito.getUsuario_id() + " && produto_id = " + favorito.getProduto_id() + ";";
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
        String sql = "SELECT * FROM preferir_produto WHERE usuario_id = " + usuario_id + ";";
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

    public List<Produto> produtos_favoritos(List<Favorito> favoritos) {
        List<Produto> saida = new ArrayList<Produto>();
        String sql = "select * from produto where id = ";
        for (int i = 0; i < favoritos.size(); i++) {
            Favorito fav = new Favorito();
            fav = favoritos.get(i);
            if (i == favoritos.size()-1) {
                sql = sql + fav.getProduto_id() + " ;";
            } else {
                sql = sql + fav.getProduto_id() + " || id = ";
            }
        }
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
                a.setCategoria_id(rs.getInt("categoria_id"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public List<Produto> listar_por_categoria(int categoria) {
        List<Produto> saida = new ArrayList<Produto>();
        String sql = "SELECT * FROM produto WHERE categoria_id ="+categoria+";";
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
                a.setCategoria_id(rs.getInt("categoria_id"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
    public List<Produto> listar_por_busca(String busca) {
        List<Produto> saida = new ArrayList<Produto>();
        String sql = "SELECT * FROM produto WHERE produto.nome LIKE '%"+busca+"%';";
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
                a.setCategoria_id(rs.getInt("categoria_id"));
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
}
