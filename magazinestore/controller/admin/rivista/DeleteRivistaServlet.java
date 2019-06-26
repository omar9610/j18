package magazinestore.controller.admin.rivista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.service.RivistaServices;


@WebServlet("/admin/delete_rivista")
public class DeleteRivistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
	RivistaServices rivistaSercices =new RivistaServices(request,response);
	rivistaSercices.deleteRivista();
	
	}

}
