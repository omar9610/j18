package magazinestore.controller.admin.rivista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.service.RivistaServices;

/**
 * Servlet implementation class EditRivistaServlet
 */
@WebServlet("/admin/edit_rivista")
public class EditRivistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditRivistaServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RivistaServices rivistaServices= new RivistaServices( request, response);
		rivistaServices.editRivista();
		
	}

}
