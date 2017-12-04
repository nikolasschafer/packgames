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
 * @author Matheus
 */
public class CategoriaServlet extends HttpServlet {

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
        Categoria a = null;
        Favorito b = null;
        String op = "list";
        String destino = "categoria_form.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = "categoria_list.jsp";
        } else if (op.equalsIgnoreCase("INC")) {
            a = incluir(request, response);
            destino = "categoria_form.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "categoria_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = "categoria_form.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = "categoria_list.jsp?op=list";
        }
        //
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    protected Categoria carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Categoria a = null;
        String nome = request.getParameter("nome");

        //cria objeto de Categoria
        a = new Categoria();
        a.setId(0);
        a.setNome(nome);

        return a;
    }

    protected Categoria incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Categoria a = carregarDados(request, response);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.incluir(a);
        session.setAttribute("categoria", a);
        request.setAttribute("msg", "Categoria cadastrada com sucesso!");
        return a;
    }

    protected List<Categoria> listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listar();
        request.setAttribute("categorias", categorias);
        return categorias;
    }

    protected Categoria alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Categoria> categorias = new LinkedList<Categoria>();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        if (session.getAttribute("categorias") != null) {
            categorias = (List<Categoria>) session.getAttribute("categorias");
        }
        Categoria a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            categorias.set(pos, a);
            session.setAttribute("categorias", categorias);
            request.setAttribute("categoria", a);
            categoriaDAO.alterar(a);
        }

        return a;
    }

    protected Categoria selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Categoria> categorias = new LinkedList<Categoria>();
        if (session.getAttribute("categorias") != null) {
            categorias = (List<Categoria>) session.getAttribute("categorias");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Categoria a = null;
        for (Categoria e : categorias) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("categoria", a);
        return a;
    }

    protected Categoria remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Categoria> categorias = new LinkedList<Categoria>();

        categorias = listar(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Categoria a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = categorias.get(pos);
            categorias.remove(a);
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.excluir(a.getId());
            session.setAttribute("categorias", categorias);
            request.setAttribute("categoria", a);
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
