package app;

import dao.InformacoesDAO;
import model.Informacoes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarInformacoes") // Esta é a URL para o seu formulário
public class CadastrarInformacoesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        // 1. Recebe os dados do formulário
        String descricao = request.getParameter("descricao");
        String data = request.getParameter("data");
        String horaInicio = request.getParameter("hora-de-inicio");
        String horaTermino = request.getParameter("hora-de-termino");
        
        // 2. Cria uma instância da classe DAO
        InformacoesDAO informacoesDAO = new InformacoesDAO();
        
        // 3. Cria um objeto Informacoes
        Informacoes informacao = new Informacoes(descricao, data, horaInicio, horaTermino); 
        
        // 4. Chama o método de inserção e verifica se foi bem-sucedido
        boolean inserido = informacoesDAO.insert(informacao);
        
        // 5. Envia uma resposta para o navegador do usuário
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        if (inserido) {
            response.getWriter().println("<h1>Agendamento para '" + descricao + "' cadastrado com sucesso!</h1>");
        } else {
            response.getWriter().println("<h1>Erro ao cadastrar o agendamento.</h1>");
        }
    }
}