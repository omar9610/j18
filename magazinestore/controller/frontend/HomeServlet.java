package magazinestore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.dao.CategoryDAO;
import magazinestore.dao.RivistaDAO;
import magazinestore.entity.Categoria;
import magazinestore.entity.Rivista;

@WebServlet("") //vuota perchè sarà  la nostra home page principale
public class HomeServlet extends HttpServlet {
    public HomeServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	RivistaDAO rivistaDAO = new RivistaDAO();
    	
    	List<Rivista> listNewRiviste = rivistaDAO.listNewRiviste();
    	
    	
    	request.setAttribute("listNewRiviste", listNewRiviste);
    	
    	
    	/*Codice per puntare direttamente alla homepage*/
        String homepage = "frontend/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
        dispatcher.forward(request,response);
        //--


    }
}
