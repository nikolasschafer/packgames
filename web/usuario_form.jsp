<%-- 
    Document   : login
    Created on : 08/11/2017, 11:02:34
    Author     : Níkolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div class="mx-auto w-50 p-3 mt-4" style="border: 1px solid #ccc; border-radius: 10px;">
            <form method="POST" action="UsuarioServlet">
                <h3>Cadastrar Usuario</h3>             
                    <input type="hidden" name="op" value="inc"/>
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" class="form-control" id="nome" aria-describedby="nomeHelp" placeholder="Nome" required name="nome" >
                </div>
                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" id="login" aria-describedby="loginHelp" placeholder="Login" required name="login" >
                </div>
                <div class="form-group">
                    <label for="inputEmail1">E-mail</label>
                    <input type="email" class="form-control" id="inputEmail1" aria-describedby="emailHelp" placeholder="E-mail" required name="email" >
                </div>
                <div class="form-group">
                    <label for="inputPassword1">Senha</label>
                    <input type="password" class="form-control" id="inputPassword1" placeholder="Senha" required name="senha" >
                </div>
                <c:if test="${usuario.admin == 1}">
                <div class="form-check">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input" value=1 name="admin">
                        Administrador
                    </label>
                </div>
                </c:if>
                <button type="submit" class="btn btn-success">Cadastrar</button>
                <button type="reset" class="btn btn-danger">Limpar</button>
            </form>
        </div>
    </body>
</html>
