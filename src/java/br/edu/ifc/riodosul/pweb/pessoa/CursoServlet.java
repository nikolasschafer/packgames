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
public class CursoServlet extends HttpServlet {

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
        Curso a = null;
        String op = "list";
        String destino = "curso_list.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = "curso_list.jsp";
        } else if (op.equalsIgnoreCase("INC")) {
            a = incluir(request, response);
            destino = "curso_view.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "curso_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = "curso_list.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = "curso_list.jsp";
        } else if (op.equalsIgnoreCase("NOVO")) {
            prepararForm(request, response);
            destino = "curso_form.jsp";
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

    protected Curso carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //a ser feito no futur
        Curso a = null;
        String nome = request.getParameter("nome");
        String sigla = request.getParameter("sigla");
        String descricao = request.getParameter("descricao");

        //cria objeto de curso
        a = new Curso();
        a.setId(0);
        a.setNome(nome);
        a.setSigla(sigla);
        a.setDescricao(descricao);

        return a;
    }

    protected Curso incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Curso a = carregarDados(request, response);
        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.incluir(a);
        request.setAttribute("curso", a);
        return a;
    }

    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        CursoDAO cursoDAO = new CursoDAO();
        List<Curso> cursos = cursoDAO.listar();
        request.setAttribute("cursos", cursos);
    }

    protected Curso alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Curso> cursos = new LinkedList<Curso>();
        if (session.getAttribute("cursos") != null) {
            cursos = (List<Curso>) session.getAttribute("cursos");
        }
        Curso a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            cursos.set(pos, a);
            session.setAttribute("cursos", cursos);
            request.setAttribute("curso", a);
        }

        return a;
    }

    protected Curso selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Curso> cursos = new LinkedList<Curso>();
        if (session.getAttribute("cursos") != null) {
            cursos = (List<Curso>) session.getAttribute("cursos");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Curso a = null;
        for (Curso e : cursos) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("curso", a);
        return a;
    }

    protected Curso remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Curso> cursos = new LinkedList<Curso>();
        if (session.getAttribute("cursos") != null) {
            cursos = (List<Curso>) session.getAttribute("cursos");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Curso a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = cursos.get(pos);
            cursos.remove(a);
            session.setAttribute("cursos", cursos);
            request.setAttribute("curso", a);
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
