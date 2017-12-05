<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <h1>Usuario:${usuario.id}</h1>
        <p>..Nome:${usuario.nome}</p>
        <p>..Login:${usuario.login}</p>
        <p>..Senha:${usuario.senha}</p>
        <p>..Email:${usuario.email}</p>
        <a href="ProdutoServlet?op=list">[voltar]</a>
    </body>
</html>