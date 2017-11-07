/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.riodosul.pweb.pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wesley
 */
public class CursoDAO {

    private Connection con = null;

    public CursoDAO() {
        try {
            //org.hsqldb.jdbcDriver
            Class.forName("com.mysql.jdbc.Driver").
                    newInstance();
            //jdbc:hsqldb:file:C:/programa/hsqldb/data/ex_aluno
            con = DriverManager.
                    getConnection("jdbc:mysql://localhost/"
                            + "ex_aluno",
                            "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void incluir(Curso curso) {
        String sql = "INSERT INTO curso "
                + "(sigla, nome, descricao) "
                + "VALUES"
                + "('" + curso.getSigla() + "',"
                + "'"  + curso.getNome() + "',"
                + "'"  + curso.getDescricao()+ "');";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Curso> listar() {
        List<Curso> saida = new ArrayList<Curso>();
        String sql = "SELECT * FROM curso;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Curso c = new Curso();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setSigla(rs.getString("sigla"));
                c.setDescricao(rs.getString("descricao"));
                saida.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Curso obter(int id) {
        Curso saida = null;
        String sql = "SELECT * FROM curso "
                + "WHERE id=" + id;
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Curso();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
                saida.setSigla(rs.getString("sigla"));
                saida.setDescricao(rs.getString("descricao"));
                //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
}
