<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div class="mx-auto w-50">

            <div class="form-group ">
                <label for="nome">Nome</label>
                <input type="text" disabled class="form-control" id="nome" aria-describedby="nomeHelp" placeholder="Nome" required name="nome" value="${usuario.nome}" >
            </div>
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" disabled class="form-control" id="login" aria-describedby="loginHelp" placeholder="Login" required name="login" value="${usuario.login}" >
            </div>
            <div class="form-group">
                <label for="inputEmail1">E-mail</label>
                <input type="email " disabled class="form-control" id="inputEmail1" aria-describedby="emailHelp" placeholder="E-mail" required name="email" value="${usuario.email}" >
            </div>
            <div class="form-group">
                <label for="inputPassword1">Senha</label>
                <input type="password" disabled class="form-control" id="inputPassword1" placeholder="Senha" required name="senha" value="${usuario.senha}">
            </div>
            <a class="btn btn-primary" href="ProdutoServlet?op=list">Voltar</a>
        </div>
    </body>
</html>