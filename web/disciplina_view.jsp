<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <h1>Disciplina:${disciplina.id}</h1>
        <p>..Nome:${disciplina.nome}</p>
        <p>..Sigla:${disciplina.sigla}</p>
        <a href="DisciplinaServlet?op=list">[voltar]</a>
    </body>
</html>
