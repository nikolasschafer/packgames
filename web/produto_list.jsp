<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div class="d-flex flex-row">

            <%@include file="menu_vertical.jsp" %>
            <div class="d-flex flex-wrap w-75 mx-auto">
                <c:if test="${produtos==null}">
                    <h2>Não existem produtos cadastrados!</h2>
                </c:if>
                <c:forEach var="o" items="${produtos}" >
                    <div class="produto">
                        <c:if test="${o.url==null}">
                            <img src="img/no-image.jpg" class="w-100"/>
                        </c:if>
                        <c:if test="${o.url!=null}">
                            <img src="${o.url}" class="w-100"/>
                        </c:if>
                        <p>Nome: ${o.nome}</p>
                        <p>Preço: ${o.preco}</p>
                        <p>Categoria: ${o.categoria_id}</p>
                        <a class="favorito" href="ProdutoServlet?op=inc_f&produto_id=${o.id}"><button type="button" class="btn btn-danger favorito"><i class="material-icons">favorite_border</i> </button></a>
                        <a class="favorito" href="ProdutoServlet?op=del_f&produto_id=${o.id}"><button type="button" class="btn favorito"><i class="material-icons">favorite_border</i> </button></a>
                    </div>   
                </c:forEach>
            </div>
        </div>
        <script>
            $(".favorito").click(function () {
                $(this).toggleClass("btn-danger");
            });
        </script>
    </body>
</html>
