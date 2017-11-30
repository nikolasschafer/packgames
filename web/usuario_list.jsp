<%-- 
    Document   : usuario_list
    Created on : 21/11/2017, 09:00:37
    Author     : Matheus
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
                        <a class="nav-link" href="categoria_form.jsp"><i class="material-icons">add</i> Inserir</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="CategoriaServlet?op=list"><i class="material-icons">list</i> Listar</a>
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
                            <th scope="col">Login</th>
                            <th scope="col">Email</th>
                            <th scope="col">Admin</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="o" items="${usuarios}" >
                            <tr class="p-3">
                                <td>${o.id}</td>
                                <td>${o.nome}</td>
                                <td>${o.login}</td>
                                <td>${o.email}</td>
                                <td>${o.admin}</td>
                                <td> 
                                    <a href="UsuarioServlet?op=sel&id=${o.id}" style="padding-right: 20px"><i class="material-icons">mode_edit</i></a>
                                    <a href="UsuarioServlet?op=del&id=${o.id}"><i class="material-icons">delete</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>