<%-- 
    Document   : cliente_list
    Created on : 21/11/2017, 09:00:37
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

            <c:if test="${clientes==null}">
                <h2>Não existem Clientes cadastrados!</h2>
            </c:if>
            <c:if test="${clientes!=null}">
                <table class="table table-striped">
                    <thead class="thead-inverse">
                        <tr>
                            <td>#</td>
                            <td>Nome</td>
                            <td>Login</td>
                            <td>Senha</td>
                            <td>Email</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="o" items="${clientes}" >
                            <tr>
                                <td>${o.id}</td>
                                <td>${o.nome}</td>
                                <td>${o.login}</td>
                                <td>${o.senha}</td>
                                <td>${o.email}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
    </body>
</html>
