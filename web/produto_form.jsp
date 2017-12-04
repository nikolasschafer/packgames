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
                    <li class="nav-item">
                        <a class="nav-link" href="ProdutoServlet?op=novo"><i class="material-icons">add</i> Inserir</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="ProdutoServlet?op=list_table"><i class="material-icons">list</i> Listar</a>
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
                    <form method="POST" action="ProdutoServlet">
                        <h3>Cadastrar Produto</h3>
                        <input type="hidden" name="op" value="inc"/>
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" id="nome" aria-describedby="nomeHelp" placeholder="Nome" required name="nome" >
                        </div>
                        <div class="form-group">
                            <label for="preco">Preço</label>
                            <input type="number" min="0" step="0.01" class="form-control" id="preco" aria-describedby="nomeHelp" placeholder="Preço" required name="preco" >
                        </div>
                        <div class="form-group">
                            <label for="descricao">Descrição</label>
                            <input type="text" class="form-control" id="descricao" aria-describedby="nomeHelp" placeholder="Descrição" required name="descricao" >
                        </div>
                        <div class="form-group">
                            <label for="imagem">Imagem</label>
                            <input type="file" class="form-control" id="imagem" aria-describedby="imagem"  required name="imagem" >
                        </div>
                        <div class="mb-3">
                            <select class="form-control" id="cateogira_id" name="categoria_id">
                                <option value="" selected disabled hidden>Selecione uma categoria</option>
                                <c:forEach var="c" items="${categorias}">
                                    <option label="${c.nome}" 
                                            value="${c.id}"/>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-success">Cadastrar</button>
                            <button type="reset" class="btn btn-danger">Limpar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
