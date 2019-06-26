package magazinestore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.entity.Rivista;

@WebServlet("/remove_from_cart")
public class RemoveCartFromServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public RemoveCartFromServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer rivistaId = Integer.parseInt(request.getParameter("id"));
		
		Object cartObject = request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart =  (ShoppingCart) cartObject;
		
		shoppingCart.removeItem(new Rivista(rivistaId));
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
