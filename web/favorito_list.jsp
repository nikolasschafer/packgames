<%-- 
    Document   : favorito_list
    Created on : 21/11/2017, 10:02:22
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div class="d-flex flex-row">

            <%@include file="menu_vertical.jsp" %>
            <div class="d-flex flex-wrap w-75 mx-auto" >
                <c:if test="${produtos_f==null}">
                    <h2>Não existem favoritos cadastrados!</h2>
                </c:if>
                <c:forEach var="o" items="${produtos_f}" >
                    <div class="produto">
                        <img src="img/no-image.jpg" class="w-100"/>
                        <p>Nome: ${o.nome}</p>
                        <p>Preço: ${o.preco}</p>
                        <p>Categoria: ${o.categoria}</p>
                    </div>   
                </c:forEach>
            </div>
        </div>
    </body>
</html>
