package magazinestore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.dao.UserDAO;
import magazinestore.entity.Utenti;

public class UserServices {
    private UserDAO userDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public UserServices( HttpServletRequest request, HttpServletResponse response) {
    	this.request = request;
    	this.response = response;
        
        userDAO = new UserDAO();
    }

    public void listUser() throws ServletException, IOException {
    	listUser(null);
    }

    
    public void listUser(String message) throws ServletException, IOException {
        List<Utenti> listUsers = userDAO.listAll();

        request.setAttribute("listUsers", listUsers);
        if (message != null) {
            request.setAttribute("message", message);
		}
        //Inoltrimo la richiesta alla pagina user_list (pagina JSP)
		String listPage = "user_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);
    }
    
    public void createUser() throws ServletException, IOException {
    	
    	String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
				
		Utenti existUser = userDAO.findByEmail(email);
		if (existUser != null) {
			String message = "Errore, l'email: " + email + " esiste già";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Utenti newUser = new Utenti(email, fullname, password);
			userDAO.create(newUser);
			listUser("Nuovo utente creato");
		}
    	
    }

	public void editUser() throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("id"));
		Utenti user = userDAO.get(userId);
		
		String editPage = "user_form.jsp";
		
		if (user == null) {
			editPage = "message.jsp";
			String errorMessage = "Non è possibile trovare l'utente con l'ID: " + userId;
			request.setAttribute("message", errorMessage);
		} else {
			request.setAttribute("user", user);			
		}
		
		request.setAttribute("user", user);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Utenti userById = userDAO.get(userId);
		Utenti userByEmail = userDAO.findByEmail(email);
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			
			String message ="Non è stato possibile modificare l'utente: l'email " + email + " esiste già";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			
		} else {
			Utenti user = new Utenti(userId, email, fullname, password);
			userDAO.update(user);
		
			String message = "Utente modificato con successo";
			listUser(message);
		}

		
		
	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Utenti user = userDAO.get(userId);
	
		String message = "Utente Eliminato con successo";
		
		/*Per evitare che l'admin venga eliminato*/
		if (userId == 1) {
			message = "L'account dell'admin non può essere eliminato";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			return;
		}
		
		/*Nel caso che ci sono due admin accesi, da diverse postazioni, che effetuano l'eliminazione
		 * in contemporanea*/
		if (user == null) {
			message = "Non è possibile trovare utente con ID " + userId
					+ ", potrebbe essere stato eliminato da altro admin";
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);			
		} else {
			userDAO.delete(userId);
			listUser(message);
		}	
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean loginResult = userDAO.checkLogin(email, password);
		
		if (loginResult) {
			request.getSession().setAttribute("useremail", email);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		} else {
			String message = "Login fallito";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	
}
