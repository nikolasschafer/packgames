/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.riodosul.pweb.pessoa;

import java.io.IOException;
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
public class AlunoServlet extends HttpServlet {

    private static int MAX_ID = 0;

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
        Aluno a = null;
        String op = "list";
        String destino = "aluno_list.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = "aluno_list.jsp";
        } else if (op.equalsIgnoreCase("INC")) {
            a = incluir(request, response);
            destino = "aluno_view.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "aluno_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = "aluno_list.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = "aluno_list.jsp";
        }else if (op.equalsIgnoreCase("NOVO")) {
            prepararForm(request, response);
            destino = "aluno_form.jsp";
        }
        //
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }
    
    protected void prepararForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<Curso> cursos = null;
        CursoDAO cursoDAO = new CursoDAO();
        cursos = cursoDAO.listar();
        request.setAttribute("cursos", cursos);
    }

    protected Aluno carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //a ser feito no futur
        Aluno a = null;
        String nome = request.getParameter("nome");
        String idadeStr = request.getParameter("idade");
        int idade = 0;
        if ((idadeStr != null) && (!idadeStr.isEmpty())) {
            idade = Integer.parseInt(idadeStr);
        }
        //
        String idCursoStr = request.getParameter("idCurso");
        int idCurso = 0;
        if ((idCursoStr != null) && (!idCursoStr.isEmpty())) {
            idCurso = Integer.parseInt(idCursoStr);
        }
        //cria objeto de aluno
        a = new Aluno();
        a.setId(0);
        a.setIdade(idade);
        a.setNome(nome);
        a.setIdCurso(idCurso);

        return a;
    }

    protected Aluno incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Aluno a = carregarDados(request, response);
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.incluir(a);
        request.setAttribute("aluno", a);
        return a;
    }

    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        AlunoDAO alunoDAO = new AlunoDAO();
        List<Aluno> alunos = alunoDAO.listar();
        request.setAttribute("alunos", alunos);
    }

    protected Aluno alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Aluno> alunos = new LinkedList<Aluno>();
        if (session.getAttribute("alunos") != null) {
            alunos = (List<Aluno>) session.getAttribute("alunos");
        }
        Aluno a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            alunos.set(pos, a);
            session.setAttribute("alunos", alunos);
            request.setAttribute("aluno", a);
        }

        return a;
    }

    protected Aluno selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Aluno> alunos = new LinkedList<Aluno>();
        if (session.getAttribute("alunos") != null) {
            alunos = (List<Aluno>) session.getAttribute("alunos");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Aluno a = null;
        for (Aluno e : alunos) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("aluno", a);
        return a;
    }

    protected Aluno remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Aluno> alunos = new LinkedList<Aluno>();
        if (session.getAttribute("alunos") != null) {
            alunos = (List<Aluno>) session.getAttribute("alunos");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Aluno a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = alunos.get(pos);
            alunos.remove(a);
            session.setAttribute("alunos", alunos);
            request.setAttribute("aluno", a);
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
