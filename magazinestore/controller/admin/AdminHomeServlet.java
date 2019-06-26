package magazinestore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/admin/")
public class AdminHomeServlet extends HttpServlet {
    public AdminHomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Codice per puntare direttamente alla admin homepage*/
        String homepage = "index.jsp";  //index.jsp è relativo a quello del servlet definito in @WebServlet in alto
        RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
        dispatcher.forward(request,response);
        //--
    }
}
