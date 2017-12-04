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
public class ProdutoServlet extends HttpServlet {

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
        Produto a = null;
        String op = "list";
        String destino = "produto_list.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = "produto_list.jsp";
        } else if (op.equalsIgnoreCase("list_table")) {
            listar(request, response);
            destino = "produto_table.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "aluno_form.jsp";
        } else if (op.equalsIgnoreCase("list_f")) {
            listar_favorito(request, response);
            destino = "produto_list.jsp";
        } else if (op.equalsIgnoreCase("inc_f")) {
            incluir_favorito(request, response);
            destino = "produto_list.jsp";
        } else if (op.equalsIgnoreCase("alt")) {
            alterar_produto(request, response);
            destino = "produto_form.jsp";
        } else if (op.equalsIgnoreCase("list_c")) {
            listar_por_categoria(request, response);
            destino = "produto_list.jsp";
        } else if (op.equalsIgnoreCase("list_b")) {
            listar_por_busca(request, response);
            destino = "produto_list.jsp";
        }
        //
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listar();
        request.setAttribute("produtos", produtos);
    }

    protected Produto selecionar(HttpServletRequest request,
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
        Produto a = null;
        for (Produto e : produtos) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("produto", a);
        return a;
    }

    protected Produto dados_produto(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Produto a = null;
        String nome = request.getParameter("nome");
        String url = request.getParameter("url");
        String descricao = request.getParameter("descricao");
        String categoria_idStr = request.getParameter("categoria_id");
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
        int categoria_id = -1;
        if ((categoria_idStr != null) && (!categoria_idStr.isEmpty())) {
            categoria_id = Integer.parseInt(categoria_idStr);
        }
        //cria objeto de Administrador
        a = new Produto();
        a.setId(0);
        a.setNome(nome);
        a.setPreco(preco);
        a.setUrl(url);
        a.setUsuario_id(admin);
        a.setDescricao(descricao);
        a.setCategoria_id(categoria_id);

        return a;
    }

    protected Produto incluir_produto(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Produto a = dados_produto(request, response);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.publicar(a);
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

    protected Favorito dados_favoritos(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String produtoStr = request.getParameter("produto_id");
        int produto = -1;
        if ((produtoStr != null) && (!produtoStr.isEmpty())) {
            produto = Integer.parseInt(produtoStr);
        }

        //cria objeto de Favorito
        Favorito a = new Favorito();
        a.setUsuario_id(usuario.getId());
        a.setProduto_id(produto);

        return a;
    }

    /*
        Método responsável por incluir produto na lista de favoritos
     */
    protected Favorito incluir_favorito(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Favorito a = dados_favoritos(request, response);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.preferir(a);
        request.setAttribute("favorito", a);
        return a;
    }

    /*
        Método responsável por listar favoritos
     */
    protected void listar_favorito(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Usuario cliente = (Usuario) session.getAttribute("usuario");
        List<Favorito> favoritos = produtoDAO.listar_favoritos(cliente.getId());
        List<Produto> produtos = produtoDAO.produtos_favoritos(favoritos);

        request.setAttribute("produtos", produtos);
    }

    /**
     * Método responsável por remover produto da lista de favoritos
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected Favorito remover_favorito(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Usuario cliente = (Usuario) session.getAttribute("cliente");
        List<Favorito> favoritos = produtoDAO.listar_favoritos(cliente.getId());

        String produtoStr = request.getParameter("produto_id");
        int produto = -1;
        if ((produtoStr != null) && (!produtoStr.isEmpty())) {
            produto = Integer.parseInt(produtoStr);
        }
        int pos = -1;
        for (int i = 0; i < favoritos.size(); i++) {
            Favorito fav = favoritos.get(i);
            if (fav.getProduto_id() == produto) {
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
            produtoDAO.excluir_favorito(a);
            session.setAttribute("favoritos", favoritos);
            request.setAttribute("favorito", a);
        }
        return a;
    }
    
    protected Produto alterar_produto(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Produto> produtos = new LinkedList<Produto>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (session.getAttribute("produtos") != null) {
            produtos = (List<Produto>) session.getAttribute("produtos");
        }
        Produto a = dados_produto(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            produtos.set(pos, a);
            session.setAttribute("produtos", produtos);
            request.setAttribute("produto", a);
            produtoDAO.alterar(a);
        }

        return a;
    }

    protected void listar_por_categoria(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String categoriaStr = request.getParameter("categoria");
        int categoria = -1;
        if ((categoriaStr != null) && (!categoriaStr.isEmpty())) {
            categoria = Integer.parseInt(categoriaStr);
        }
        List<Produto> produtos = produtoDAO.listar_por_categoria(categoria);
        request.setAttribute("produtos", produtos);
    }
    
    protected void listar_por_busca(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String busca = request.getParameter("search");
        
        List<Produto> produtos = produtoDAO.listar_por_busca(busca);
        request.setAttribute("produtos", produtos);
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
