package magazinestore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import magazinestore.dao.CategoryDAO;
import magazinestore.dao.RivistaDAO;
import magazinestore.entity.Categoria;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(
			HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		categoryDAO = new CategoryDAO();
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Categoria> listCategory = categoryDAO.listAll();

		request.setAttribute("listCategory", listCategory);
		if (message != null) {
			request.setAttribute("message", message);
		}

		//inoltriamo la richiesta alla pagina category_list(JSP page):
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Categoria existCategory = categoryDAO.findByName(name);

		if (existCategory != null) {
			String message = "La categoria " + name + " esiste già";
			request.setAttribute("message", message);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Categoria newCategory = new Categoria(name);
			categoryDAO.create(newCategory);
			String message = "Nuova categoria creata con successo";
			listCategory(message);
		}
	}

	public void editCategoy() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Categoria category = categoryDAO.get(categoryId);
		request.setAttribute("category", category);

		String editPage = "category_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoriaId"));
		String categoryName = request.getParameter("name");

		Categoria categoryById = categoryDAO.get(categoryId);
		Categoria categoryByName = categoryDAO.findByName(categoryName);

		/*Controllo se categoryByName è diverso da categoryById
		 * visualizzo messaggio d'errore perchè l'admin sta provando di modificare il nome di una 
		 * categoria che esiste già nel database*/
		if (categoryByName != null && categoryById.getCategoriaId() != categoryByName.getCategoriaId()) {
			String message = "Non è possibile modificare la categoria." 
					+ " La categoria col nome " + categoryName + " esiste già";

			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Categoria modificata con successo";
			listCategory(message);
		}

	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		RivistaDAO rivistaDAO = new RivistaDAO();
		long numberOfMagazines = rivistaDAO.countByCategory(categoryId);
		String message;
		
		if(numberOfMagazines > 0) {
			message = "Non è possibile eliminare la categoria ID: %d perchè contiene delle riviste.";
			message = String.format(message, numberOfMagazines);
		} else {
			categoryDAO.delete(categoryId);		
			message = "La categoria con ID " + categoryId + " è stata eliminata con successo.";
		}
		listCategory(message);


//		categoryDAO.delete(categoryId);
//		Categoria category = categoryDAO.get(categoryId);
//		if (category == null) {
//			message = "Non è possibile trovare la categoria con ID " 
//					+ categoryId + ", potrebbe essere stata eliminata.";
//
//			request.setAttribute("message", message);
//			request.getRequestDispatcher("message.jsp").forward(request, response);
//			return;		
//		}

	}


}
