<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>

    <nav class="navbar fixed-top navbar-dark bg-primary" style="padding-left: 100px; padding-right: 100px;">
        <a class="navbar-brand" href="ProdutoServlet?op=list">
            <img src="img/game_white.png" width="30" height="30" class="d-inline-block align-top" alt="">
            <b>Pack Games</b>
        </a>
        <form class="form-inline mx-auto w-50" action="ProdutoServlet?op=list_b">
            <input class="form-control mr-sm-2" type="search" style="width: 85%" placeholder="Pesquisar" aria-label="Search" >
            <button class="btn btn-primary btn-sm my-2 my-sm-0" type="submit"><i class="material-icons" >search</i></button>
        </form>
        <ul class="nav">
            <c:if test="${usuario==null}">
                <li class="nav-item">
                    <a href="UsuarioServlet?op=LOGOUT"><button type="button" class="btn btn-light text-secondary">Entrar</button></a>
                </li>
            </c:if>
            <c:if test="${usuario!=null}">
                <li class="nav-item dropdown">
                    <button class="nav-link dropdown-toggle btn btn-light" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${usuario.nome}
                    </button>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:if test="${usuario.admin==1}">
                            <a class="dropdown-item" href="ProdutoServlet?op=list_table">Produtos</a>
                            <a class="dropdown-item" href="UsuarioServlet?op=list">Usuarios</a>
                            <a class="dropdown-item" href="categoria_form.jsp">Categorias</a>
                            <div class="dropdown-divider"></div>
                        </c:if>
                        <a class="dropdown-item" href="ProdutoServlet?op=list_f">Favoritos</a>
                        <a class="dropdown-item" href="usuario_view.jsp">Minha Conta</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="UsuarioServlet?op=LOGOUT">Sair</a>
                    </div>
                </li>
            </c:if>
        </ul>
    </nav>

    <div class="w-100 text-primary" style="margin-top: 80px; margin-bottom: 30px;">
        <c:if test="${msg_cadastro!=null}">
            <div class="alert alert-success alert-dismissible fade show mx-auto w-75" role="alert">
                <strong>Bem-vindo!</strong> ${msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <h1 class="mx-auto" style="text-align: center; font-size: 70px;">Pack Games</h1>
    </div>


</div>
