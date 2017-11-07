<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div>
            <h1>Formulário de Curso</h1>
            <form method="POST" action="CursoServlet">
                <c:if test="${curso==null}">
                    <input type="hidden" name="op" value="inc"/>
                </c:if>
                <c:if test="${curso!=null}">
                    <input type="hidden" name="op" value="alt"/>
                    <input type="hidden" name="id" value="${curso.id}"/>
                </c:if>
                <label for="txtNome">Nome:</label>
                <input id="txtNome" name="nome" 
                       value="${curso.nome}"/>
                <br/>
                <label for="txtSigla">Sigla:</label>
                <input id="txtSigla" name="sigla" 
                       value="${curso.sigla}"/>
                <br/>
                <label for="txtDescricao">Descricao:</label>
                <textarea id="txtDescricao" name="descricao" >
                    ${curso.descricao}
                </textarea>
                <br/>
                <br/>
                <input type="submit" name="btnOk" value="Ok"/>
                <input type="reset" value="Limpar"/>
            </form>
        </div>
    </body>
</html>
