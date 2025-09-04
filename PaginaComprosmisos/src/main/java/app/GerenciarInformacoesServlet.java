package app;

import com.google.gson.Gson; // Adicione esta biblioteca ao seu projeto (pesquise "gson maven" para mais info)
import dao.InformacoesDAO;
import model.Informacoes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gerenciarInformacoes")
public class GerenciarInformacoesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("application/json"); // Define o tipo de resposta como JSON
        response.setCharacterEncoding("UTF-8");
        
        String acao = request.getParameter("acao");
        String jsonResponse = "";
        InformacoesDAO informacoesDAO = new InformacoesDAO();
        Gson gson = new Gson(); // Usaremos a biblioteca Gson para converter objetos Java para JSON

        if ("pesquisarPorData".equals(acao)) {
            String data = request.getParameter("data");
            List<Informacoes> resultados = informacoesDAO.getByData(data);
            jsonResponse = gson.toJson(resultados);

        } else if ("removerPorId".equals(acao)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean removido = informacoesDAO.delete(id);
                if (removido) {
                    jsonResponse = "{\"status\":\"sucesso\", \"mensagem\":\"Compromisso removido com sucesso.\"}";
                } else {
                    jsonResponse = "{\"status\":\"erro\", \"mensagem\":\"Erro ao remover o compromisso.\"}";
                }
            } catch (NumberFormatException e) {
                jsonResponse = "{\"status\":\"erro\", \"mensagem\":\"ID inválido.\"}";
            }

        } else if ("mostrarTodos".equals(acao)) {
            List<Informacoes> resultados = informacoesDAO.get();
            jsonResponse = gson.toJson(resultados);
        }

        response.getWriter().write(jsonResponse);
    }
}