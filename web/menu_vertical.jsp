<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div style="width: 300px; ">        
    <ul class="nav flex-column menu-vertical">
        <li class="categoria active">
            <a class="nav-link" href="ProdutoServlet?op=list"><i class="material-icons">fiber_manual_record</i>Todos os produtos</a>
        </li>
        <c:forEach var="o" items="${categorias}" >
            <li class="categoria">
                <a class="nav-link" href="ProdutoServlet?op=list_c&categoria=${o.id}"><i class="material-icons">fiber_manual_record</i>${o.nome}</a>
            </li>   
        </c:forEach>
    </ul>
</div>

