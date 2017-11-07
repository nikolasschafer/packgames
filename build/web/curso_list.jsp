<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <h1>Lista de Curso</h1>
        <c:if test="${cursos==null}">
            <h2>Não existem cursos cadastrados!</h2>
        </c:if>
        <c:if test="${cursos!=null}">
            <table class="table table-striped">
                <thead class="thead-inverse">
                    <tr>
                        <td>#</td>
                        <td>Nome</td>
                        <td>Sigla</td>                        
                        <td>OPs</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${cursos}" >
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.nome}</td>
                            <td>${o.sigla}</td>
                            <td> 
                                <a href="CursoServlet?op=sel&id=${o.id}">[A]</a>
                                <a href="CursoServlet?op=del&id=${o.id}">[E]</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td><a href="CursoServlet?op=novo">[novo]</a></td>
                    </tr>
                </tfoot>
            </table>
        </c:if>    
    </body>
</html>
