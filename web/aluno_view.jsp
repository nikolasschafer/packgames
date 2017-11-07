<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <h1>Aluno:${aluno.id}</h1>
        <p>..Nome:${aluno.nome}</p>
        <p>..Idade:${aluno.idade}</p>
        <br/>

        <form method="post" action="MatriculaServlet">
            <!-- campos escondidos para manter o aluno -->
            <input type="hidden" name="idAluno" value="${aluno.id}"/>
            <input type="hidden" name="op" value="inc_aluno"/>
            <table class="table table-striped">
                <thead class="thead-inverse">
                    <tr>
                        <td>#</td>
                        <td>Ano</td>
                        <td>Semestre</td>
                        <td>idDisciplina</td>
                        <td>OPs</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${matriculas}" >
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.ano}</td>
                            <td>${o.semestre}</td>                            
                            <td>${o.idDisciplina}</td>
                            <td> 
                                <a href="MatriculaServlet?op=del_aluno&id=${o.id}&idAluno=${aluno.id}">[E]</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td></td>
                        <td><input type="text" name="ano" size="10" /></td>
                        <td><input type="text" name="semestre" size="10" /></td>
                        <td><select name="idDisciplina">
                                <c:forEach var="d" items="${disciplinas}">
                                    <option value="${d.id}">${d.nome}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><input name="btnIncluir" type="submit" value="INC"/></td>
                    </tr>
                </tfoot>
            </table>
        </form>

        <a href="AlunoServlet?op=list">[voltar]</a>
    </body>
</html>
