<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <h1>Lista de Aluno</h1>
        <c:if test="${alunos==null}">
            <h2>Não existem alunos cadastrados!</h2>
        </c:if>
        <c:if test="${alunos!=null}">
            <table class="table table-striped">
                <thead class="thead-inverse">
                    <tr>
                        <td>#</td>
                        <td>Nome</td>
                        <td>Idade</td>
                        <td>idCurso</td>
                        <td>Curso</td>
                        <td>OPs</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${alunos}" >
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.nome}</td>
                            <td>${o.idade}</td>
                            <td>${o.idCurso}</td>
                            <td>${o.curso.nome}</td>
                            <td> 
                                <a href="MatriculaServlet?op=novo_aluno&idAluno=${o.id}">[D]</a>
                                <a href="AlunoServlet?op=sel&id=${o.id}">[A]</a>
                                <a href="AlunoServlet?op=del&id=${o.id}">[E]</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td><a href="AlunoServlet?op=novo">[novo]</a></td>
                    </tr>
                </tfoot>
            </table>
        </c:if>    
    </body>
</html>
