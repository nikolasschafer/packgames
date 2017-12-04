<%-- 
    Document   : produto lista em tabela
    Created on : 28/11/2017, 08:21:31
    Author     : Níkolas
--%>

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
                        <a class="nav-link" href="produto_form.jsp"><i class="material-icons">add</i> Inserir</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="ProdutoServlet?op=list"><i class="material-icons">list</i> Listar</a>
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
                <table class="table table-hover tbcategoria">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Preço</th>
                            <th scope="col">Descrição</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="o" items="${produtos}" >
                            <tr class="p-3">
                                <td>${o.id}</td>
                                <td>${o.nome}</td>
                                <td>${o.preco}</td>
                                <td>${o.descricao}</td>
                                <td> 
                                    <a href="ProdutoServlet?op=alt&id=${o.id}" style="padding-right: 20px"><i class="material-icons">mode_edit</i></a>
                                    <a href="CategoriaServlet?op=del&id=${o.id}"><i class="material-icons">delete</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>