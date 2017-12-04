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
public class UsuarioServlet extends HttpServlet {

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
        Usuario a = null;
        Favorito b = null;
        String op = "list";
        String destino = "usuario_list.jsp";
        if ((request.getParameter("op") != null)
                && (!request.getParameter("op").isEmpty())) {
            op = request.getParameter("op");
        }
        //
        if (op.equalsIgnoreCase("LIST")) {
            listar(request, response);
            destino = "usuario_list.jsp";
        } else if (op.equalsIgnoreCase("INC")) {
            a = incluir(request, response);
            destino = "produto_list.jsp";
        } else if (op.equalsIgnoreCase("SEL")) {
            a = selecionar(request, response);
            destino = "usuario_form.jsp";
        } else if (op.equalsIgnoreCase("ALT")) {
            a = alterar(request, response);
            destino = "usuario_list.jsp";
        } else if (op.equalsIgnoreCase("DEL")) {
            a = remover(request, response);
            destino = "usuario_list.jsp";
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

    protected Usuario carregarDados(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Usuario a = null;
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        String adminStr = request.getParameter("admin");
        int admin = -1;
        if ((adminStr != null) && (!adminStr.isEmpty())) {
            admin = Integer.parseInt(adminStr);
        }

        //cria objeto de Usuario
        a = new Usuario();
        a.setId(0);
        a.setNome(nome);
        a.setLogin(login);
        a.setSenha(senha);
        a.setEmail(email);
        a.setAdmin(admin);
        return a;
    }

    protected Usuario incluir(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario a = carregarDados(request, response);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.incluir(a);
        session.setAttribute("usuario", a);
        request.setAttribute("msg_cadastro", "Cadastro efetuado com sucesso, acompanhe a lista de nossos produtos!");
        return a;
    }

    protected void listar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listar();
        request.setAttribute("usuarios", usuarios);
    }

    protected Usuario alterar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Usuario> usuarios = new LinkedList<Usuario>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (session.getAttribute("usuarios") != null) {
            usuarios = (List<Usuario>) session.getAttribute("usuarios");
        }
        Usuario a = carregarDados(request, response);
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        a.setId(id);
        int pos = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            usuarios.set(pos, a);
            session.setAttribute("usuarios", usuarios);
            request.setAttribute("usuario", a);
            usuarioDAO.alterar(a);
        }

        return a;
    }

    protected Usuario selecionar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Usuario> usuarios = new LinkedList<Usuario>();
        if (session.getAttribute("usuarios") != null) {
            usuarios = (List<Usuario>) session.getAttribute("usuarios");
        }
        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        Usuario a = null;
        for (Usuario e : usuarios) {
            if (e.getId() == id) {
                a = e;
            }
        }
        request.setAttribute("usuario", a);
        return a;
    }

    protected String logar(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.login(request.getParameter("login"), request.getParameter("senha"));
        if (usuario == null) {
            request.setAttribute("msg",
                    "Login ou senha incorretos!");
            return "login.jsp";
        } else {
            session.setAttribute("usuario", usuario);
            return "ProdutoServlet?op=list";

        }
    }

    protected void logout(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    protected Usuario remover(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Usuario> usuarios = new LinkedList<Usuario>();
        if (session.getAttribute("usuarios") != null) {
            usuarios = (List<Usuario>) session.getAttribute("usuarios");
        }

        String idStr = request.getParameter("id");
        int id = -1;
        if ((idStr != null) && (!idStr.isEmpty())) {
            id = Integer.parseInt(idStr);
        }
        int pos = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                pos = i;
            }
        }
        //
        Usuario a = null;
        //
        if (pos == -1) {
            request.setAttribute("msg",
                    "Não existe o id!");
        } else {
            a = usuarios.get(pos);
            usuarios.remove(a);
            session.setAttribute("usuarios", usuarios);
            request.setAttribute("usuario", a);
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
