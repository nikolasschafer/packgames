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
public class DisciplinaServlet extends HttpServlet {

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
        Disciplina a = null;
        String op = "list";
        String destino = "disciplina_list.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = "disciplina_list.jsp";
        } else if (op.equalsIgnoreCase("INC")) {
            a = incluir(request, response);
            destino = "disciplina_view.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "disciplina_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = "disciplina_list.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = "disciplina_list.jsp";
        } else if (op.equalsIgnoreCase("NOVO")) {
            prepararForm(request, response);
            destino = "disciplina_form.jsp";
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

    protected Disciplina carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //a ser feito no futur
        Disciplina a = null;
        String nome = request.getParameter("nome");
        String sigla = request.getParameter("sigla");
        String horaInicio = request.getParameter("horaInicio");
        String horaFim = request.getParameter("horaFim");
        String diaSemana = request.getParameter("diaSemana");

        String idCursoStr = request.getParameter("idCurso");
        int idCurso = 0;
        if ((idCursoStr != null) && (!idCursoStr.isEmpty())) {
            idCurso = Integer.parseInt(idCursoStr);
        }
        //cria objeto de disciplina
        a = new Disciplina();
        a.setId(0);
        a.setNome(nome);
        a.setIdCurso(idCurso);
        a.setDiaSemana(diaSemana);
        a.setHoraFim(horaFim);
        a.setHoraInicio(horaInicio);
        a.setSigla(sigla);

        return a;
    }

    protected Disciplina incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Disciplina a = carregarDados(request, response);
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.incluir(a);
        request.setAttribute("disciplina", a);
        return a;
    }

    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        List<Disciplina> disciplinas = disciplinaDAO.listar();
        request.setAttribute("disciplinas", disciplinas);
    }

    protected Disciplina alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Disciplina> disciplinas = new LinkedList<Disciplina>();
        if (session.getAttribute("disciplinas") != null) {
            disciplinas = (List<Disciplina>) session.getAttribute("disciplinas");
        }
        Disciplina a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            disciplinas.set(pos, a);
            session.setAttribute("disciplinas", disciplinas);
            request.setAttribute("disciplina", a);
        }

        return a;
    }

    protected Disciplina selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Disciplina> disciplinas = new LinkedList<Disciplina>();
        if (session.getAttribute("disciplinas") != null) {
            disciplinas = (List<Disciplina>) session.getAttribute("disciplinas");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Disciplina a = null;
        for (Disciplina e : disciplinas) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("disciplina", a);
        return a;
    }

    protected Disciplina remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Disciplina> disciplinas = new LinkedList<Disciplina>();
        if (session.getAttribute("disciplinas") != null) {
            disciplinas = (List<Disciplina>) session.getAttribute("disciplinas");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Disciplina a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = disciplinas.get(pos);
            disciplinas.remove(a);
            session.setAttribute("disciplinas", disciplinas);
            request.setAttribute("disciplina", a);
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
