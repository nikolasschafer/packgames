/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.riodosul.pweb.pessoa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wesley
 */
public class MatriculaServlet extends HttpServlet {

    private static int MAX_ID = 0;
    private final static String TABELA = "matricula";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Matricula a = null;
        String op = "list";
        String destino = TABELA + "_list.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = TABELA + "_list.jsp";
        } else if (op.equalsIgnoreCase("INC")) {
            a = incluir(request, response);
            destino = TABELA + "_view.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = TABELA + "_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = TABELA + "_list.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = TABELA + "_list.jsp";
        } else if (op.equalsIgnoreCase("NOVO")) {
            prepararForm(request, response);
            destino = TABELA + "_form.jsp";
        
        //para fazer a manutencao da matricula na pagina de aluno
        } else if (op.equalsIgnoreCase("NOVO_ALUNO")) {
            prepararForm(request, response);
            destino = "aluno_view.jsp";
        } else if (op.equalsIgnoreCase("INC_ALUNO")) {
            incluir(request, response);
            prepararForm(request, response);
            destino = "aluno_view.jsp";
        } else if (op.equalsIgnoreCase("DEL_ALUNO")) {
            remover(request, response);
            prepararForm(request, response);
            destino = "aluno_view.jsp";
        }
        
        //
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    protected void prepararForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        AlunoDAO alunoDAO = new AlunoDAO();
        int idAluno = getParam(request, "idAluno");
        Aluno aluno = alunoDAO.obter(idAluno);
        request.setAttribute("aluno", aluno);
        
        List<Disciplina> disciplinas = null;
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinas = disciplinaDAO.listar();
        request.setAttribute("disciplinas", disciplinas);
        
        List<Aluno> alunos = null;
        alunos = alunoDAO.listar();
        request.setAttribute("alunos", alunos);
        
        List<Matricula> matriculas = null;
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        matriculas = matriculaDAO.listarPorAluno(aluno);
        request.setAttribute("matriculas", matriculas);
       
        int idDisciplina = getParam(request, "idDisciplina");
        Disciplina disciplina = disciplinaDAO.obter(idDisciplina);
        request.setAttribute("disciplina", disciplina);
    }

    private int getParam(HttpServletRequest request, String nomeParam) {
        String str = request.getParameter(nomeParam);
        int saida = 0;
        if ((str != null) && (!str.isEmpty())) {
            saida = Integer.parseInt(str);
        }
        return saida;
    }

    protected Matricula carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Matricula a = new Matricula();
        a.setId(0);
        a.setIdAluno(getParam(request, "idAluno"));
        a.setIdDisciplina(getParam(request, "idDisciplina"));
        a.setAno(getParam(request, "ano"));
        a.setSemestre(getParam(request, "semestre"));
        return a;
    }

    protected Matricula incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Matricula a = carregarDados(request, response);
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        matriculaDAO.incluir(a);
        request.setAttribute("matricula", a);
        return a;
    }

    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        List<Matricula> matriculas = matriculaDAO.listar();
        request.setAttribute("matriculas", matriculas);
    }

    protected Matricula alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Matricula> matriculas = new LinkedList<Matricula>();
        if (session.getAttribute("matriculas") != null) {
            matriculas = (List<Matricula>) session.getAttribute("matriculas");
        }
        Matricula a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            matriculas.set(pos, a);
            session.setAttribute("matriculas", matriculas);
            request.setAttribute("matricula", a);
        }

        return a;
    }

    protected Matricula selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Matricula> matriculas = new LinkedList<Matricula>();
        if (session.getAttribute("matriculas") != null) {
            matriculas = (List<Matricula>) session.getAttribute("matriculas");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Matricula a = null;
        for (Matricula e : matriculas) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("matricula", a);
        return a;
    }

    protected Matricula remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Matricula> matriculas = new LinkedList<Matricula>();
        if (session.getAttribute("matriculas") != null) {
            matriculas = (List<Matricula>) session.getAttribute("matriculas");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Matricula a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = matriculas.get(pos);
            matriculas.remove(a);
            session.setAttribute("matriculas", matriculas);
            request.setAttribute("matricula", a);
        }
        return a;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
