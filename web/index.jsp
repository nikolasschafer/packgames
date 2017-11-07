<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <div id="main">
            <%@include file="topo.jsp" %>
            <div id="content">
                <h1>Bem vindo!</h1> 
            </div>
            <%@include file="rodape.jsp" %>           
        </div>
    </body>
</html>
