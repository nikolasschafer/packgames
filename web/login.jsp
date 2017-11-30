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
        <c:if test="${msg!=null}">
            <div class="alert alert-warning alert-dismissible fade show mx-auto w-50" role="alert">
                <strong>Ops!</strong> ${msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="mx-auto w-50 p-3" style="border: 1px solid #ccc; border-radius: 10px;">
            <form method="POST" action="UsuarioServlet">
                <input type="hidden" name="op" value="LOGIN"/>
                <div class="form-group">
                    <label for="inputEmail1">Login</label>
                    <input name="login" type="text" class="form-control" id="inputEmail1" aria-describedby="emailHelp" placeholder="Login" required>
                </div>
                <div class="form-group">
                    <label for="inputPassword1">Senha</label>
                    <input name="senha" type="password" class="form-control" id="inputPassword1" placeholder="Senha" required>
                </div>
                <button type="submit" class="btn btn-primary">Entrar</button>
                <a href="usuario_form.jsp"><button type="button" class="btn btn-success">Cadastrar</button></a>
            </form>
        </div>
    </body>
</html>
