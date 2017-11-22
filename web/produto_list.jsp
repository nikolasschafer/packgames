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
                <div class="produto">
                    <img src="img/no-image.jpg" class="w-100"/>
                    <p>Nome: PRODUTO TESTE</p>
                    <p>Preço: R$ 49,90</p>
                    <a class="favorito" href="#"><button type="button" class="btn favorito btn-danger"><i class="material-icons">favorite_border</i> </button></a>
                </div>          
                <div class="produto">
                    <img src="img/no-image.jpg" class="w-100"/>
                    <p>Nome: PRODUTO TESTE</p>
                    <p>Preço: R$ 49,90</p>
                    <a class="favorito" href="#"><button type="button" class="btn favorito btn-danger"><i class="material-icons">favorite_border</i> </button></a>
                </div>          
            </div>
        </div>

        <script>
            $(".favorito").click(function () {
                $(this).toggleClass( "btn-danger");
            });
        </script>
    </body>
</html>
