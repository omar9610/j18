package magazinestore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.dao.RivistaDAO;
import magazinestore.entity.Rivista;

@WebServlet("/add_to_cart")
public class AddRivistaToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddRivistaToCartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	Integer rivistaId = Integer.parseInt(request.getParameter("id"));
		
		Object cartObject = request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart = null;
		
		if (cartObject != null && cartObject instanceof ShoppingCart) {
			shoppingCart =  (ShoppingCart) cartObject;
		} else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		RivistaDAO rivistaDAO = new RivistaDAO();
		Rivista rivista = rivistaDAO.get(rivistaId);
		
		shoppingCart.addItem(rivista);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
