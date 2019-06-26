package magazinestore.controller.frontend.magazine;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.service.RivistaServices;

@WebServlet("/search")
public class SearchRivistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	public SearchRivistaServlet() {
    }
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			RivistaServices rivistaServices = new RivistaServices(request,response);
			rivistaServices.search();
	}

}
