package app;

import dao.UsuarioDAO;
import model.Usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarUsuario") // Esta é a URL para o seu formulário
public class CadastrarUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        // 1. Recebe os dados do formulário
    	String codigoString = request.getParameter("codigo");
    	int codigo = Integer.parseInt(codigoString);
    	String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String sexoStr = request.getParameter("sexo");
        char sexo = sexoStr.charAt(0);

        // 2. Cria uma instância da classe DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // 3. Cria um objeto Usuario. O código 0 pode ser um placeholder
        Usuario usuario = new Usuario(codigo, login, senha, sexo); 
        
        // 4. Chama o método de inserção e verifica se foi bem-sucedido
        boolean inserido = usuarioDAO.insert(usuario);
        
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("login", "senha"));
		
		System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
		List<Usuario> usuarios = usuarioDAO.getSexoMasculino();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}

        // 5. Envia uma resposta para o navegador do usuário
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        if (inserido) {
            response.getWriter().println("<h1>Usuário " + login + " cadastrado com sucesso!</h1>");
        } else {
            response.getWriter().println("<h1>Erro ao cadastrar o usuário.</h1>");
        }
    }
}