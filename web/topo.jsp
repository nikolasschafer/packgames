<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>

    <nav class="navbar fixed-top navbar-dark bg-primary" style="padding-left: 100px; padding-right: 100px;">
        <a class="navbar-brand" href="index.jsp">
            <img src="img/game_white.png" width="30" height="30" class="d-inline-block align-top" alt="">
            <b>Pack Games</b>
        </a>
        <form class="form-inline mx-auto w-50">
            <input class="form-control mr-sm-2" type="search" style="width: 85%" placeholder="Pesquisar" aria-label="Search">
            <button class="btn btn-primary btn-sm my-2 my-sm-0" type="submit"><i class="material-icons">search</i></button>
        </form>
        <ul class="nav">
            <li class="nav-item">
                <!--<a class="nav-link text-light active" href="#">Cadastrar</a>-->
                <a href="login.jsp"><button type="button" class="btn btn-light text-secondary">Entrar</button></a>
            </li>
        </ul>
    </nav>

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">

            <b>Pack Games</b>
        </a>
        <button class="navbar-toggler" type="button" 
                data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Início <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="CursoServlet?op=list" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Curso
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="CursoServlet?op=novo">Novo</a>
                        <a class="dropdown-item" href="CursoServlet?op=list">Listar</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="DisciplinaServlet?op=list" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Disciplina
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="DisciplinaServlet?op=novo">Novo</a>
                        <a class="dropdown-item" href="DisciplinaServlet?op=list">Listar</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="AlunoServlet?op=list" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Aluno
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="AlunoServlet?op=novo">Novo</a>
                        <a class="dropdown-item" href="AlunoServlet?op=list">Listar</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
    <div class="w-100 text-primary">
        <h1 class="mx-auto mt-3" style="text-align: center; font-size: 70px;">Pack Games</h1>
    </div>

</div>
