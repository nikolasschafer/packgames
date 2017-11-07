<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <h1>Lista de Disciplina</h1>
        <c:if test="${disciplinas==null}">
            <h2>Não existem disciplinas cadastrados!</h2>
        </c:if>
        <c:if test="${disciplinas!=null}">
            <table class="table table-striped">
                <thead class="thead-inverse">
                    <tr>
                        <td>#</td>
                        <td>Nome</td>
                        <td>Sigla</td>
                        <td>idCurso</td>
                        <td>Curso</td>
                        <td>OPs</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${disciplinas}" >
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.nome}</td>
                            <td>${o.sigla}</td>
                            <td>${o.idCurso}</td>
                            <td>${o.curso.nome}</td>
                            <td> 
                                <a href="DisciplinaServlet?op=sel&id=${o.id}">[A]</a>
                                <a href="DisciplinaServlet?op=del&id=${o.id}">[E]</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td><a href="DisciplinaServlet?op=novo">[novo]</a></td>
                    </tr>
                </tfoot>
            </table>
        </c:if>    
    </body>
</html>
