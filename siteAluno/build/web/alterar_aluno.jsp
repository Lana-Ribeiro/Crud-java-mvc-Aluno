<%@page import="model.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alteração de departamento</title>
    </head>
    <body>
        <%
           Aluno alu = (Aluno) request.getAttribute("aluno");
        %>
       <form method="post" action="ControleAluno">
            <input type="hidden" name="flag" value="alt_aluno">

            <p>
                <label for="matricula">Matricula</label>
                <input id="matricula" type="number" name="matricula" placeholder="Coloque sua Matricula" size="80" maxlength="80" required value="<%= alu.getMatricula() %>" hidden>
            </p>

            <p>
                <label for="nome">Nome</label>
                <input id="nome" type="text" name="nome" placeholder="Coloque o seu Nome" size="80" maxlength="80"  required value="<%= alu.getNome() %>">
            </p>

            <p>
                <label for="curso">Curso*</label>
                <input id="curso" type="text" name="curso" placeholder="Coloque o seu Curso" size="80" maxlength="80" required value="<%= alu.getCurso() %>">
            </p>

            <p>
                <label for="turno">Turno</label>
                <input id="turno" type="text" name="turno" placeholder="Coloque o seu Turno" size="20" maxlength="20" value="<%= alu.getTurno() %>">
            </p>

            <p>
                <label for="duracao">Duração</label>
                <input id="duracao" type="number" name="duracao" placeholder="Coloque a Duração" size="40" maxlength="40" value="<%= alu.getDuracao() %>">
            </p>

            <p>
                <label for="valorCurso">Valor do Curso</label>
                <input id="valorCurso" type="number" name="valorCurso" placeholder="Coloque o valor do curso" step="0.1" value="<%= alu.getValorCurso() %>">
            </p>

            <input type="submit" value="Salvar Alteração">
        </form>
    </body>
</html>
