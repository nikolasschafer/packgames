<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div>
            <h1>Formulário de Disciplina</h1>
            <form method="POST" action="DisciplinaServlet">
                <c:if test="${disciplina==null}">
                    <input type="hidden" name="op" value="inc"/>
                </c:if>
                <c:if test="${disciplina!=null}">
                    <input type="hidden" name="op" value="alt"/>
                    <input type="hidden" name="id" value="${disciplina.id}"/>
                </c:if>
                <label for="txtNome">Nome:</label>
                <input id="txtNome" name="nome" 
                       value="${disciplina.nome}"/>
                <br/>                
                <label for="cmbCurso">Curso:</label>
                <select id="cmbCurso" name="idCurso">
                    <c:forEach var="c" items="${cursos}">
                        <option label="${c.sigla} - ${c.nome}" 
                                value="${c.id}"/>
                    </c:forEach>
                </select>
                <br/>
                <label for="txtHoraInicio">Hora inicio:</label>
                <input id="txtHoraInicio" name="horaInicio" 
                       value="${disciplina.horaInicio}"/>
                <br/>
                <label for="txtHoraFim">Hora Fim:</label>
                <input id="txtHoraFim" name="horaFim" 
                       value="${disciplina.horaFim}"/>
                <br/>
                <label for="txtDiaSemana">Dia Semana:</label>
                <input id="txtDiaSemana" name="diaSemana" 
                       value="${disciplina.diaSemana}"/>
                <br/>
                <input type="submit" name="btnOk" value="Ok"/>
                <input type="reset" value="Limpar"/>
            </form>
        </div>
    </body>
</html>
