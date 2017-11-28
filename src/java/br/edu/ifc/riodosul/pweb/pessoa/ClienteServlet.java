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
        String destino = "cliente_list.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = "cliente_list.jsp";
        } else if (op.equalsIgnoreCase("INC")) {
            a = incluir(request, response);
            destino = "prodito_list.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "cliente_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = "cliente_list.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = "cliente_list.jsp";
        } else if (op.equalsIgnoreCase("INC_F")) {
            b = incluir_favorito(request, response);
            destino = "produto_list.jsp";
        } else if (op.equalsIgnoreCase("LIST_F")) {
            listar_favorito(request, response);
            destino = "favorito_list.jsp";
        } else if (op.equalsIgnoreCase("DEL_F")) {
            b = remover_favorito(request, response);
            destino = "produto_list.jsp";
        } else if (op.equalsIgnoreCase("LOGIN")) {
            destino = logar(request, response);
        } else if (op.equalsIgnoreCase("LOGOUT")) {
            logout(request, response);
            destino = "login.jsp";
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
        HttpSession session = request.getSession();
        Cliente a = carregarDados(request, response);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.incluir(a);
        session.setAttribute("cliente", a);
        request.setAttribute("msg_cadastro", "Cadastro efetuado com sucesso, acompanhe a lista de nossos produtos!");
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


    protected String logar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.login(request.getParameter("login"), request.getParameter("senha"));
        if (cliente == null) {
            request.setAttribute("msg",
                    "Login ou senha incorretos!");
            return "login.jsp";
        } else {
            session.setAttribute("cliente", cliente);
            return "ProdutoServlet?op=list";

        }
    }

    protected void logout(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
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
        List<Produto> produtos_f = null;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listar();
        for (int i = 1; i <= favoritos.size(); i++) {
            Favorito fav = new Favorito();
            fav = favoritos.get(i);
            for (int j = 1; j <= produtos.size(); j++) {
                Produto produto = new Produto();
                produto = produtos.get(i);
                if(produto.getId() == fav.getProduto_id()){
                    produtos_f.add(produto);
                }
            }
        }
        request.setAttribute("produtos_f", produtos_f);
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
            if (favoritos.get(i).getProduto_id() == produto) {
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
