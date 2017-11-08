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
        <div class="mx-auto w-50 p-3" style="border: 1px solid #ccc; border-radius: 10px;">
            <form>
                <div class="form-group">
                    <label for="inputEmail1">E-mail</label>
                    <input type="email" class="form-control" id="inputEmail1" aria-describedby="emailHelp" placeholder="E-mail" required>
                </div>
                <div class="form-group">
                    <label for="inputPassword1">Senha</label>
                    <input type="password" class="form-control" id="inputPassword1" placeholder="Senha" required>
                </div>
                <button type="submit" class="btn btn-primary">Entrar</button>
                <a href="login.jsp"><button type="button" class="btn btn-success">Cadastrar</button></a>
            </form>
        </div>
    </body>
</html>
