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
                        <a class="nav-link" href="ProdutoServlet?op=novo"><i class="material-icons">add</i> Inserir</a>
                    </li>
                    <li class="nav-item">
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
                    <h3>Cadastrar Produto</h3>
                    <form method="post" action="ProdutoServlet?op=img" enctype="multipart/form-data">
                        <c:if test="${img_name!=null}">
                            <img src="img_produto/${img_name}" class="w-25">
                        </c:if>
                        <c:if test="${img_name==null}">
                            <img src="img/no-image.jpg" class="w-25"/>
                        </c:if>

                        <div class="form-group">
                            <label for="file">Imagem</label>
                            <input type="file" class="form-control" id="file" aria-describedby="file"  required name="file" >
                        </div>
                        <button type="submit" class="btn btn-success">Carregar imagem</button>
                    </form>
                    <form method="POST" action="ProdutoServlet?op=inc" accept-charset="UTF-8">
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
                        <div class="mb-3">
                            <select class="form-control" id="cateogira_id" name="categoria_id">
                                <option value="" selected disabled hidden>Selecione uma categoria</option>
                                <c:forEach var="c" items="${categorias}">
                                    <option label="${c.nome}" 
                                            value="${c.id}"/>
                                </c:forEach>
                            </select>
                        </div>
                        <c:if test="${url!=null}">
                            <input type="hidden" value="${url}" name="url"/>
                        </c:if>
                        <c:if test="${url==null}">
                            <input type="hidden" value="img/no-image.jpg" name="url"/>
                        </c:if>
                        <div>
                            <button type="submit" class="btn btn-success">Cadastrar</button>
                            <button type="reset" class="btn btn-danger">Limpar</button>
                        </div>
                    </form>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form method="post" action="ProdutoServlet?op=img" enctype="multipart/form-data">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="file">Imagem</label>
                                        <input type="file" class="form-control" id="file" aria-describedby="file"  required name="file" >
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-success">Enviar imagem</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>
