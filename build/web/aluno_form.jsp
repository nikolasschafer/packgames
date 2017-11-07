<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div>
            <h1>Formulário de Aluno</h1>
            <form method="POST" action="AlunoServlet">
                <c:if test="${aluno==null}">
                    <input type="hidden" name="op" value="inc"/>
                </c:if>
                <c:if test="${aluno!=null}">
                    <input type="hidden" name="op" value="alt"/>
                    <input type="hidden" name="id" value="${aluno.id}"/>
                </c:if>
                <label for="txtNome">Nome:</label>
                <input id="txtNome" name="nome" 
                       value="${aluno.nome}"/>
                <br/>
                <label for="txtIdade">Idade:</label>
                <input id="txtIdade" name="idade" 
                       value="${aluno.idade}"/>
                <br/>
                <label for="cmbCurso">Curso:</label>
                <select id="cmbCurso" name="idCurso">
                    <c:forEach var="c" items="${cursos}">
                        <option label="${c.sigla} - ${c.nome}" 
                                value="${c.id}"/>
                    </c:forEach>
                </select>
                <br/>
                <input type="submit" name="btnOk" value="Ok"/>
                <input type="reset" value="Limpar"/>
            </form>
        </div>
    </body>
</html>
