<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <h1>Curso:${curso.id}</h1>
        <p>..Nome:${curso.nome}</p>
        <p>..Sigla:${curso.sigla}</p>
        <a href="CursoServlet?op=list">[voltar]</a>
    </body>
</html>
