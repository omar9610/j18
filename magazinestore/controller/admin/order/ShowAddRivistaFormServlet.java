package magazinestore.controller.admin.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.dao.RivistaDAO;
import magazinestore.entity.Rivista;

@WebServlet("/admin/add_rivista_form")
public class ShowAddRivistaFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAddRivistaFormServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RivistaDAO rivistaDao = new RivistaDAO();
		List<Rivista> listRivista = rivistaDao.listAll();
		request.setAttribute("listRivista", listRivista);
		
		String addFormPage = "add_rivista_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(addFormPage);
		dispatcher.forward(request, response);
		
	}

}
