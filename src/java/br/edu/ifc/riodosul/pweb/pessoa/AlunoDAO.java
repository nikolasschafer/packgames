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
public class AlunoDAO {

    private Connection con = null;

    public AlunoDAO() {
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

    public void incluir(Aluno aluno) {
        String sql = "INSERT INTO aluno "
                + "(nome, idade, curso_id) "
                + "VALUES"
                + "('" + aluno.getNome() + "',"
                + aluno.getIdade() + ","
                + aluno.getIdCurso() + ");";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void alterar(Aluno aluno) {
        String sql = "UPDATE aluno SET"            
                + "nome='" + aluno.getNome() 
                + "',idade=" + aluno.getIdade() 
                + ",curso_id="+ aluno.getIdCurso() + ");";
        System.out.println("sql update:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listar() {
        List<Aluno> saida = new ArrayList<Aluno>();
        String sql = "SELECT * FROM aluno;";
        CursoDAO cursoDAO = new CursoDAO();
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setIdCurso(rs.getInt("curso_id"));
                //referencia             
                a.setCurso(
                        cursoDAO.obter(
                                a.getIdCurso()));
                //
                saida.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Aluno obter(int id) {
        Aluno saida = null;
        String sql = "SELECT * FROM aluno WHERE id=" + id;
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Aluno();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
                saida.setIdade(rs.getInt("idade"));
                saida.setIdCurso(rs.getInt("curso_id"));
                //
                CursoDAO cursoDAO = new CursoDAO();
                saida.setCurso(
                        cursoDAO.obter(
                                saida.getIdCurso()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
}
