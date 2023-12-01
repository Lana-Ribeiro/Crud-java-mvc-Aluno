package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;
import model.EmpresaDao;

/**
 *
 * @author lana.carolines
 */
@WebServlet(name = "ControleAluno", urlPatterns = {"/ControleAluno"})
public class ControleAluno extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            EmpresaDao dao = new EmpresaDao();
            RequestDispatcher disp;
            String flag, mensagem;
            flag = request.getParameter("flag");
             int matricula;
                String nome;
                String curso;
                String turno;
                int duracao;
                double valorCurso;
         
            if (flag.equals("cad_Aluno")) {            
                
                        matricula = Integer.parseInt(request.getParameter("matricula"));
                        nome =request.getParameter("nome");
                        curso =request.getParameter("curso");
                        turno= request.getParameter("turno");
                        duracao= Integer.parseInt(request.getParameter("duracao"));
                        valorCurso=Double.parseDouble(request.getParameter("valorCurso"));
        
            Aluno aluno = new Aluno();
            
            aluno.setMatricula(Integer.valueOf(matricula));
            aluno.setNome(nome);
            aluno.setCurso(curso);
            aluno.setTurno(turno); 
            aluno.setDuracao(duracao);
            aluno.setValorCurso(valorCurso);
                                
                switch (new EmpresaDao().salvar(aluno)) {
                    case 1:
                        mensagem = "Aluno salvo com sucesso";
                        break;
                    case 2:
                        mensagem = "O aluno " + aluno.getNome() + " já está cadastrado";
                        break;
                    default:
                        mensagem = "Erro ao tentar salvar o aluno";
                        break;
                }
                  request.setAttribute("mensagem", mensagem);
                  request.getRequestDispatcher("mensagens.jsp").forward(request, response);

                
            } else if (flag.equals("list_Aluno")) {
                List<Aluno> alunos = new EmpresaDao().listar("Aluno.findAll", Aluno.class);
                if (alunos == null) {
                    request.setAttribute("mensagem", "Não há alunos cadastrados");
                    request.getRequestDispatcher("mensagens.jsp").forward(request, response);
                } else {
                    request.setAttribute("alunos", alunos);
                    request.getRequestDispatcher("mostrar_alunos.jsp").forward(request, response);
                }
            }
            
            //EXCLUIR ALUNO
            else if (flag.equals("exc_aluno")) {
                matricula = Integer.parseInt(request.getParameter("matricula"));     
                int retorno = new EmpresaDao().excluir(matricula, Aluno.class);
                
                switch (retorno) {
                    case 1:
                        mensagem = "Aluno " + matricula + " Excluido com sucesso!";
                        break;
                    case 2:
                        mensagem = "Aluno " + matricula + " não cadastrado!";
                        break;
                    default:
                        mensagem = "Erro encontrado. Entre em contato com o suporte;" +matricula;
                        break;
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("mensagens.jsp").forward(request, response);
            } 
            
            //ALTERAR ALUNO
            else if (flag.equals("alt_aluno")) {
       
            nome = request.getParameter("nome");
            curso = request.getParameter("curso");
            turno = request.getParameter("turno");
            valorCurso = Double.parseDouble(request.getParameter("valorCurso"));
            duracao = Integer.parseInt(request.getParameter("duracao"));
            matricula = Integer.parseInt(request.getParameter("matricula"));

            try {
                valorCurso = Double.parseDouble(request.getParameter("valorCurso"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            Aluno alu = new Aluno();
            alu.setNome(nome);
            alu.setCurso(curso);
            alu.setTurno(turno);
            alu.setValorCurso(valorCurso);
            alu.setDuracao(duracao);
            alu.setMatricula(matricula);

            int resultado;
            try {
                resultado = new EmpresaDao().alterar(alu);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if (resultado == 1) {
                mensagem = "Alteração no aluno " + nome + " realizada com sucesso!";
            } else {
                mensagem = "Erro ao tentar alterar os dados. Entre em contato com o suporte.";
            }

            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("mensagens.jsp").forward(request, response);
        }
            
            
            
            
            //BUSCAR NA ALTERA
             else if (flag.equals("buscar_alt_aluno")) { //colocando matricula como inteiro. TAMBEM MUDOU NO EMPRESA DAO!!!1
            Aluno aluno = new EmpresaDao().buscar(Aluno.class,Integer.parseInt(request.getParameter("matricula")));
            if (aluno == null) { //se não encontrou o departamento
                request.setAttribute("mensagem", "Aluno não encontrado");
                request.getRequestDispatcher("mensagens.jsp").forward(request, response);
            } else { //se encontrou o departamento a ser alterado
                request.setAttribute("aluno", aluno);
                request.getRequestDispatcher("alterar_aluno.jsp").forward(request, response);
            }
        }
        

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}