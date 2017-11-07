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
public class DisciplinaDAO {

    private Connection con = null;

    public DisciplinaDAO() {
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

    public void incluir(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina "
                + "(nome, curso_id, sigla, hora_inicio, hora_fim, dia_semana) "
                + "VALUES"
                + "('" + disciplina.getNome() + "',"
                + "'" + disciplina.getIdCurso()+ "',"
                + "'" + disciplina.getSigla() + "',"
                + "'" + disciplina.getHoraInicio() + "',"
                + "'" + disciplina.getHoraFim() + "',"
                + "'" + disciplina.getDiaSemana() + "');";
        System.out.println("sql insert:" + sql);
        try {
            Statement stm = con.createStatement();
            stm.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Disciplina> listar() {
        List<Disciplina> saida = new ArrayList<Disciplina>();
        String sql = "SELECT * FROM disciplina;";
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            while (rs.next()) {
                Disciplina c = new Disciplina();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setSigla(rs.getString("sigla"));
                c.setHoraInicio(rs.getString("hora_inicio"));
                c.setHoraFim(rs.getString("hora_fim"));
                c.setDiaSemana(rs.getString("dia_semana"));
                saida.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }

    public Disciplina obter(int id) {
        Disciplina saida = null;
        String sql = "SELECT * FROM disciplina "
                + "WHERE id=" + id;
        try {
            ResultSet rs = con.createStatement().
                    executeQuery(sql);
            if (rs.next()) {
                saida = new Disciplina();
                saida.setId(rs.getInt("id"));
                saida.setNome(rs.getString("nome"));
                saida.setSigla(rs.getString("sigla"));
                saida.setHoraInicio(rs.getString("hora_inicio"));
                saida.setHoraFim(rs.getString("hora_fim"));
                saida.setDiaSemana(rs.getString("dia_semana"));
                //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saida;
    }
}
