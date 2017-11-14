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
public class ClienteServlet extends HttpServlet {

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
        Cliente a = null;
        Favorito b = null;
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
        }else if (op.equalsIgnoreCase("INC_F")) {
            b = incluir_favorito(request, response);
            destino = "aluno_list.jsp";
        }else if (op.equalsIgnoreCase("LIST_F")) {
            listar_favorito(request, response);
            destino = "aluno_list.jsp";
        }else if (op.equalsIgnoreCase("DEL_F")) {
            b = remover_favorito(request, response);
            destino = "aluno_list.jsp";
        }
        //
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }
    
    protected Cliente carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        Cliente a = null;
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        
        //cria objeto de Cliente
        a = new Cliente();
        a.setId(0);
        a.setNome(nome);
        a.setLogin(login);
        a.setSenha(senha);
        a.setEmail(email);
    
        return a;
    }
    
    protected Cliente incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Cliente a = carregarDados(request, response);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.incluir(a);
        request.setAttribute("cliente", a);
        return a;
    }
    
    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listar();
        request.setAttribute("clientes", clientes);
    }
    
    protected Cliente alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Cliente> clientes = new LinkedList<Cliente>();
        if (session.getAttribute("clientes") != null) {
            clientes = (List<Cliente>) session.getAttribute("clientes");
        }
        Cliente a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            clientes.set(pos, a);
            session.setAttribute("clientes", clientes);
            request.setAttribute("cliente", a);
        }

        return a;
    }
    
    protected Cliente selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Cliente> clientes = new LinkedList<Cliente>();
        if (session.getAttribute("clientes") != null) {
            clientes = (List<Cliente>) session.getAttribute("clientes");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Cliente a = null;
        for (Cliente e : clientes) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("cliente", a);
        return a;
    }
    
    protected Cliente remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Cliente> clientes = new LinkedList<Cliente>();
        if (session.getAttribute("clientes") != null) {
            clientes = (List<Cliente>) session.getAttribute("clientes");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Cliente a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = clientes.get(pos);
            clientes.remove(a);
            session.setAttribute("clientes", clientes);
            request.setAttribute("cliente", a);
        }
        return a;
    }
    
    protected Favorito dados_favoritos(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        Favorito a = null;
        String clienteStr = request.getParameter("usuario_id");
        String produtoStr = request.getParameter("produto_id");
        int cliente = -1, produto = -1;
        if ((clienteStr != null) && (!clienteStr.isEmpty())) {
            cliente = Integer.parseInt(clienteStr);
        }
        if ((produtoStr != null) && (!produtoStr.isEmpty())) {
            produto = Integer.parseInt(produtoStr);
        }
        
        //cria objeto de Favorito
        a = new Favorito();
        a.setUsuario_id(cliente);
        a.setProduto_id(produto);
        
        return a;
    }
    
    protected Favorito incluir_favorito(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Favorito a = dados_favoritos(request, response);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.preferir(a);
        request.setAttribute("favorito", a);
        return a;
    }
    
    protected void listar_favorito(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        String clienteStr = request.getParameter("usuario_id");
        int cliente = -1;
        if ((clienteStr != null) && (!clienteStr.isEmpty())) {
            cliente = Integer.parseInt(clienteStr);
        }
        List<Favorito> favoritos = clienteDAO.listar_favoritos(cliente);
        request.setAttribute("favoritos", favoritos);
    }
    
    protected Favorito remover_favorito(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        List<Favorito> favoritos = new LinkedList<Favorito>();
        if (session.getAttribute("favoritos") != null) {
            favoritos = (List<Favorito>) session.getAttribute("favoritos");
        }

        String produtoStr = request.getParameter("produto_id");
        int produto = -1;
        if ((produtoStr != null) && (!produtoStr.isEmpty())) {
            produto = Integer.parseInt(produtoStr);
        }
        int pos = -1;
        for (int i = 0; i < favoritos.size(); i++) {
            if (favoritos.get(i).getProduto_id()== produto) {
                pos = i;
            }
        }
        //
        Favorito a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = favoritos.get(pos);
            favoritos.remove(a);
            session.setAttribute("favoritos", favoritos);
            request.setAttribute("favorito", a);
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
