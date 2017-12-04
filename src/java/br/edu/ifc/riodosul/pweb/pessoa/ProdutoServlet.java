/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifc.riodosul.pweb.pessoa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
        } else if (op.equalsIgnoreCase("NOVO")) {
            prepararForm(request, response);
            destino = "produto_form.jsp";
        } else if (op.equalsIgnoreCase("inc")) {
            a = incluir_produto(request, response);
            upload_img(request, response);
            destino = "ProdutoServlet?op=list_table";
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

    protected void prepararForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<Categoria> categorias = null;
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categorias = categoriaDAO.listar();
        request.setAttribute("categorias", categorias);
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

        HttpSession session = request.getSession();
        Produto a = null;
        String nome = request.getParameter("nome");
        String url = request.getParameter("url");
        String descricao = request.getParameter("descricao");
        String categoria_idStr = request.getParameter("categoria_id");
        String precoStr = request.getParameter("preco");
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        double preco = -1;
        if ((precoStr != null) && (!precoStr.isEmpty())) {
            preco = Double.parseDouble(precoStr);
        }

        int admin = usuario.getId();

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

    protected void upload_img(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //verifica se o enctype esta correto
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            //
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem i : items) {
                File f = new File("c:/temp/ex_fileupload/" + i.getName());
                FileOutputStream fos = new FileOutputStream(f);
                InputStream is = i.getInputStream();
                byte dados[] = new byte[512];
                while (is.read(dados) >= 0) {
                    fos.write(dados);
                }
                fos.flush();
                fos.close();
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Usuario cliente = (Usuario) session.getAttribute("cliente");
        List<Favorito> favoritos = produtoDAO.listar_favoritos(cliente.getId());
        List<Produto> produtos = new LinkedList<Produto>();
        List<Produto> aux_produtos = produtoDAO.listar();

        for (int i = 0; i <= favoritos.size() - 1; i++) {
            Favorito fav = new Favorito();
            fav = favoritos.get(i);
            for (int j = 0; j <= aux_produtos.size() - 1; j++) {
                Produto produto = new Produto();
                produto = aux_produtos.get(j);
                if (produto.getId() == fav.getProduto_id()) {
                    produtos.add(produto);
                }
            }
        }
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
