<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:import url="cabecalho.jsp" />
    <body>
        <%@include file="topo.jsp" %>
        <div class="d-flex flex-row">

            <div style="width: 300px; ">        
                <ul class="nav flex-column menu-vertical">
                    <li class="nav-item active">
                        <a class="nav-link" href="produto_form.jsp"><i class="material-icons">add</i> Inserir</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="produto_table.jsp"><i class="material-icons">list</i> Listar</a>
                    </li>
                </ul>
            </div>


            <div class="d-flex flex-wrap w-75 mx-auto">
                <c:if test="${msg!=null}">
                    <div class="alert alert-success alert-dismissible fade show  w-100" role="alert">
                        <strong>Blz!</strong> ${msg}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
                <div class="mx-auto w-100 p-3 " style="border: 1px solid #ccc; border-radius: 10px;">
                    <form method="POST" action="CategoriaServlet">
                        <h3>Cadastrar produto</h3>
                        <input type="hidden" name="op" value="inc"/>
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" id="nome" aria-describedby="nomeHelp" placeholder="Nome" required name="nome" >
                            <label for="preco">Preço</label>
                            <input type="double" class="form-control" id="preco" aria-describedby="precoHelp" placeholder="Preço" required name="preco" >
                            <label for="descricao">Descrição</label>
                            <input type="text" class="form-control" id="descricao" aria-describedby="descricaoHelp" placeholder="Descrição" required name="descricao" >
                        </div>
                        <button type="submit" class="btn btn-success">Cadastrar</button>
                        <button type="reset" class="btn btn-danger">Limpar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
