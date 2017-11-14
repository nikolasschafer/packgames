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

;

/**
 *
 * @author Matheus
 */
public class AdministradorServlet extends HttpServlet {

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
        Administrador a = null;
        Produto b = null;
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
            destino = "cliente_form.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "aluno_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = "aluno_list.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = "aluno_list.jsp";
        }else if (op.equalsIgnoreCase("INC_P")) {
            b = incluir_produto(request, response);
            destino = "aluno_list.jsp";
        }else if (op.equalsIgnoreCase("DEL")) {
            b = remover_produto(request, response);
            destino = "aluno_list.jsp";
        }
        //
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }
    
    protected Administrador carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        Administrador a = null;
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        
        //cria objeto de Administrador
        a = new Administrador();
        a.setId(0);
        a.setNome(nome);
        a.setLogin(login);
        a.setSenha(senha);
        a.setEmail(email);
    
        return a;
    }
    
    protected Administrador incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Administrador a = carregarDados(request, response);
        AdministradorDAO administradorDAO = new AdministradorDAO();
        administradorDAO.incluir(a);
        request.setAttribute("administrador", a);
        return a;
    }
    
    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        AdministradorDAO administradorDAO = new AdministradorDAO();
        List<Administrador> administradores = administradorDAO.listar();
        request.setAttribute("administradores", administradores);
    }
    
    protected Administrador alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Administrador> administradores = new LinkedList<Administrador>();
        if (session.getAttribute("administradores") != null) {
            administradores = (List<Administrador>) session.getAttribute("administradores");
        }
        Administrador a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < administradores.size(); i++) {
            if (administradores.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            administradores.set(pos, a);
            session.setAttribute("administradores", administradores);
            request.setAttribute("administrador", a);
        }

        return a;
    }
    
    protected Administrador selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Administrador> administradores = new LinkedList<Administrador>();
        if (session.getAttribute("administradores") != null) {
            administradores = (List<Administrador>) session.getAttribute("administradores");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Administrador a = null;
        for (Administrador e : administradores) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("administradores", a);
        return a;
    }
    
    protected Administrador remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Administrador> administradores = new LinkedList<Administrador>();
        if (session.getAttribute("administradores") != null) {
            administradores = (List<Administrador>) session.getAttribute("administradores");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < administradores.size(); i++) {
            if (administradores.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Administrador a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = administradores.get(pos);
            administradores.remove(a);
            session.setAttribute("administradores", administradores);
            request.setAttribute("administrador", a);
        }
        return a;
    }
    
    protected Produto dados_produto(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        Produto a = null;
        String nome = request.getParameter("nome");
        String url = request.getParameter("url");
        String descricao = request.getParameter("descricao");
        String categoria = request.getParameter("categoria");
        String precoStr = request.getParameter("preco");
        String adminStr = request.getParameter("usuario_id");
        double preco = -1;
        if ((precoStr != null) && (!precoStr.isEmpty())) {
            preco = Double.parseDouble(precoStr);
        }
        int admin = -1;
        if ((adminStr != null) && (!adminStr.isEmpty())) {
            admin = Integer.parseInt(adminStr);
        }
        //cria objeto de Administrador
        a = new Produto();
        a.setId(0);
        a.setNome(nome);
        a.setPreco(preco);
        a.setUrl(url);
        a.setUsuario_id(admin);
        a.setDescricao(descricao);
        a.setCategoria(categoria);
    
        return a;
    }
    
    protected Produto incluir_produto(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Produto a = dados_produto(request, response);
        AdministradorDAO administradorDAO = new AdministradorDAO();
        administradorDAO.publicar(a);
        request.setAttribute("produto", a);
        return a;
    }
    
    protected Produto remover_produto(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Produto> produtos = new LinkedList<Produto>();
        if (session.getAttribute("produtos") != null) {
            produtos = (List<Produto>) session.getAttribute("produtos");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Produto a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = produtos.get(pos);
            produtos.remove(a);
            session.setAttribute("produtos", produtos);
            request.setAttribute("produto", a);
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
