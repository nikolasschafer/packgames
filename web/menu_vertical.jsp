<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="width: 300px; ">        
    <ul class="nav flex-column menu-vertical">
        <c:if test="${categorias==null}">
            <h2>Listando categorias...</h2>
        </c:if>
        <c:forEach var="o" items="${categorias}" >
            <li class="categoria">
                <a class="nav-link" href="ProdutoServlet?op=list_c&categoria=${o.id}"><i class="material-icons">settings_input_composite</i>${o.nome}</a>
            </li>   
        </c:forEach>
    </ul>
</div>

