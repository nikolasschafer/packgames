/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.riodosul.pweb.pessoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wesley
 */
public class MatriculaDAO {

    private Connection con = null;

    public MatriculaDAO() {
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

    public void incluir(Matricula matricula) {
        String sql = "INSERT INTO matricula "
                + "(disciplina_id, aluno_id, ano, semestre) "
                + "VALUES"
                + "(" + matricula.getIdDisciplina() + ","
                + " " + matricula.getIdAluno() + ","
                + " " + matricula.getAno() + ","
                + " " + matricula.getSemestre() + ");";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Matricula sql2object(ResultSet rs, boolean completo) throws SQLException {
        Matricula saida = new Matricula();
        saida.setId(rs.getInt("id"));
        saida.setIdAluno(rs.getInt("aluno_id"));
        saida.setIdDisciplina(rs.getInt("disciplina_id"));
        saida.setAno(rs.getInt("ano"));
        saida.setSemestre(rs.getInt("semestre"));
        if(completo){
            //carregar aluno
            //carregar disciplina
        }
        return saida;
    }

    public List<Matricula> listar() {
        List<Matricula> saida = new ArrayList<Matricula>();
        String sql = "SELECT * FROM matricula;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Matricula c = sql2object(rs, false);
                saida.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
    public List<Matricula> listarPorAluno(Aluno aluno) {
        List<Matricula> saida = new ArrayList<Matricula>();
        String sql = "SELECT * FROM matricula WHERE aluno_id="+aluno.getId();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Matricula c = sql2object(rs, false);
                saida.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
    
    public List<Matricula> listarPorDisciplina(Disciplina disciplina) {
        List<Matricula> saida = new ArrayList<Matricula>();
        String sql = "SELECT * FROM matricula WHERE disciplina_id="+disciplina.getId();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Matricula c = sql2object(rs, false);
                saida.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Matricula obter(int id) {
        Matricula saida = null;
        String sql = "SELECT * FROM matricula "
                + "WHERE id=" + id;
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = sql2object(rs, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
}
