package app;

import dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/autenticarUsuario")
public class AutenticarUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean autenticado = usuarioDAO.autenticar(login, senha);

     // Código CORRIGIDO:
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (autenticado) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUsuario", login);
            response.getWriter().write("{\"status\":\"ok\", \"redirect\":\"painel.jsp\"}");
            
        } else {
            response.getWriter().write("{\"status\":\"erro\", \"mensagem\":\"Login ou senha inválidos.\"}");
        }
    }
}