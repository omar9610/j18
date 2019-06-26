package magazinestore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import magazinestore.dao.CategoryDAO;
import magazinestore.dao.OrderDAO;
import magazinestore.dao.RivistaDAO;
import magazinestore.entity.Categoria;
import magazinestore.entity.Rivista;
import static magazinestore.service.CommonUtitlity.*;

public class RivistaServices {

	private RivistaDAO rivistaDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public RivistaServices( HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		rivistaDAO= new RivistaDAO();
		categoryDAO = new CategoryDAO();

	}

	public void listRiviste() throws ServletException, IOException {
		listRiviste(null);
	}
	public void listRiviste(String message) throws ServletException, IOException {

		List<Rivista> listRivista= rivistaDAO.listAll();
		request.setAttribute("listRivista",listRivista);

		if (message != null) {
			request.setAttribute("message", message);
		}
		forwardToPage("rivista_list.jsp", message, request, response);
	}
	public void showRivistaNewForm() throws ServletException, IOException {

		List<Categoria> listCategory= categoryDAO.listAll();
		request.setAttribute("listCategory",listCategory);
		forwardToPage("rivista_form.jsp",request, response);

	}

	public void createRivista() throws ServletException, IOException {


		String title = request.getParameter("title");


		Rivista existRivista = rivistaDAO.findByTitle(title);
		if (existRivista != null) {
			String message = "Non è possibile creare un nuova rivista perchè il titolo '" 
					+ title + "' esiste giï¿½.";
			listRiviste(message);
			return;
		}
		Rivista newRivista= new Rivista();
		readRivistaFields(newRivista);

		Rivista createdRivista = rivistaDAO.create(newRivista);


		if (createdRivista.getRivistaId() > 0) {
			String message = "Un nuovo libro è stato creato con successo";
			request.setAttribute("message", message);
			listRiviste();

		}

	}
	public void readRivistaFields(Rivista rivista) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
	   float price = Float.parseFloat(request.getParameter("price"));

		DateFormat dateFormat = new SimpleDateFormat("dd/mm-yyyy");
		Date publishDate = null;

		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("Errore nel formato della data(Deve essere dd/MM/yyyy)");
		}




		rivista.setTitle(title);
		rivista.setDescription(description);
		rivista.setPrice(price);
		rivista.setPublishDate(publishDate);

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Categoria category = categoryDAO.get(categoryId);
		rivista.setCategoria(category);

		Part part = request.getPart("RivistaImage");
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];

			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();

			rivista.setImmagine(imageBytes);
		}

	}
	public void editRivista() throws ServletException, IOException {
		Integer rivistaId= Integer.parseInt(request.getParameter("id"));
		Rivista rivista=rivistaDAO.get(rivistaId);
		
		if(rivista != null) {
		List<Categoria> listCategory=categoryDAO.listAll();
		request.setAttribute("rivista",rivista);
		request.setAttribute("listCategory",listCategory);
		forwardToPage("rivista_form.jsp", request, response);
		
		}
		else {
			String message = "Impossibilie trovare la rivista con  ID " + rivistaId;
			showMessageBackend(message, request, response);
		}

	}

	public void updateRivista() throws ServletException, IOException {
		Integer rivistaId= Integer.parseInt(request.getParameter("rivistaId"));
		String title = request.getParameter("title");


		Rivista existRivista = rivistaDAO.get(rivistaId);
		Rivista rivistaByTitle =rivistaDAO.findByTitle(title);

		if(rivistaByTitle!=null &&!existRivista.equals(rivistaByTitle)) {
			String message="non è possibile cambiare a questo titolo perche' gia'  usato da un altra rivista.";
			listRiviste(message);
			return;
		}
		readRivistaFields(existRivista);
		rivistaDAO.update(existRivista);
		String message= "La rivista e' stata aggiornata con successo.";
		listRiviste(message);

	}

	public void deleteRivista() throws ServletException, IOException {
		Integer rivistaId= Integer.parseInt(request.getParameter("id"));
		Rivista rivista= rivistaDAO.get(rivistaId);
		
		rivistaDAO.delete(rivistaId);
		
		if (rivista == null) {
			String message = "Impossibile trovare la rivista con ID " + rivistaId 
					+ ", oppure e' stata gia' eliminata";
			showMessageBackend(message, request, response);
			
		} else {			
				OrderDAO orderDAO = new OrderDAO();
				long countByOrder = orderDAO.countOrderDetailByRivista(rivistaId);
				
				if (countByOrder > 0) {
					String message = "Non si puo' eliminare la rivista con ID " + rivistaId
							+ " perche' e' asociata a dei ordini.";
					showMessageBackend(message, request, response);
				} else {
					String message = "La rivista e' stata eliminata.";
					rivistaDAO.delete(rivistaId);			
					listRiviste(message);
				}
			}
		}

	

	public void listRivistaByCatagory() throws ServletException, IOException {
		int categoryId =Integer.parseInt(request.getParameter("id"));
		
		List<Rivista> listRiviste =rivistaDAO.listByCategory(categoryId);
		Categoria category= categoryDAO.get(categoryId);

		if (category == null) {
			String message = "Sorry, the category ID " + categoryId + " is not available.";
			showMessageFrontend(message, request, response);			
			return;
		}
		
		request.setAttribute("listRiviste",listRiviste);
		request.setAttribute("category",category);


		forwardToPage("frontend/riviste_list_by_category.jsp", request, response);

	}

	public void viewRivistaDetail() throws ServletException, IOException {
		Integer rivistaId= Integer.parseInt(request.getParameter("id"));
		Rivista rivista= rivistaDAO.get(rivistaId);
		
		if(rivista != null) {
		request.setAttribute("rivista",rivista);
		forwardToPage("frontend/rivista_detail.jsp", request, response);
		
		}
		else {			
			String message = "Ci scusiamo, ma la rivista con ID  " + rivistaId + " non e' disponibile";
			showMessageFrontend(message, request, response);			
		}
		
	}

	public void search() throws ServletException, IOException{
		String keyword= request.getParameter("keyword");
		List<Rivista> result = null;
		if(keyword.equals("")) {
			result= rivistaDAO.listAll();
		}else {
			result=rivistaDAO.search(keyword);
		}
		request.setAttribute("keyword",keyword);
		request.setAttribute("result",result);
		
		forwardToPage("frontend/search_result.jsp", request, response);

	}
}
