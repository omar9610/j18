package magazinestore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.event.spi.ClearEvent;
import org.omg.CORBA.Request;
import org.w3c.dom.ls.LSInput;

import magazinestore.dao.CustomerDAO;
import magazinestore.dao.OrderDAO;
import magazinestore.entity.Cliente;
import static magazinestore.service.CommonUtitlity.*;

public class CustomerServices {
	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDAO = new CustomerDAO();
	}

	public void listCustomers(String message) throws ServletException, IOException {
		List<Cliente> listCustomer = customerDAO.listAll();
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listCustomer", listCustomer);
		forwardToPage("customer_list.jsp", message, request, response);
	}
	
	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}
	public void  createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Cliente existCustomer = customerDAO.findByEmail(email);
		
		if (existCustomer != null) {
			String message = "Non è possibile creare nuovo utente: l'email " 
					+ email + " esiste già";
			listCustomers(message);
		} else {
			
			Cliente newCustomer = new Cliente();
			updteCustomerFieldsForm(newCustomer);
			customerDAO.create(newCustomer);
			
			String message = "Nuovo cliente è stato creato con successo";
			listCustomers(message);
			
		}
		
	}

	private void updteCustomerFieldsForm(Cliente customer) {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		
		
		if (email != null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		customer.setFullname(fullName);
		
		if (password != null && !password.equals("")) {
			customer.setPassword(password);
		}
		
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipcode);
		customer.setCountry(country);
	}
	public void editCustomer() throws ServletException, IOException {
		
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		Cliente customer=customerDAO.get(customerId);
		
		
		if (customer == null) {
			
			String message = "impossibile trovare il cliente con ID " + customerId;
			showMessageBackend(message, request, response);
		} else {
			request.setAttribute("cliente", customer);		
			forwardToPage("customer_form.jsp", request, response);			
		}		

	}

	public void updateCustomer() throws ServletException, IOException {
		
		Integer clienteId=Integer.parseInt(request.getParameter("clienteId"));
		String email=request.getParameter("email");
		
		Cliente customerByEmail=customerDAO.findByEmail(email);
		String message=null;
		
		if(customerByEmail !=null && customerByEmail.getClienteId()!=clienteId) {
			 message="Non possiamo aggiornare il cliente" + clienteId +  "perche' esiste gia un cliente con questa email";
		}
		else {
			
			Cliente customerById = customerDAO.get(clienteId);
			updteCustomerFieldsForm(customerById);
			customerDAO.update(customerById);
			 message="I dati del cliente sono stati aggiornati";
		}
		
		listCustomers(message);
	}

		public void  registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Cliente existCustomer = customerDAO.findByEmail(email);
		String message="";
		if (existCustomer != null) {
			message = "Non è possibile registrarsi: l'email " 
					+ email + " esiste già";
		} else {
			
			Cliente newCustomer = new Cliente();
			updteCustomerFieldsForm(newCustomer);
			customerDAO.create(newCustomer);
			
			message = "Registrazione avvenuta con successo.<br/>"
					+"<a href='login'>Clicca qui </a> per eseguire il login";
	
		}
		
		showMessageFrontend(message, request, response);
	}
		public void deleteCustomer() throws ServletException, IOException {
			Integer clienteId=Integer.parseInt(request.getParameter("id"));
			Cliente customer = customerDAO.get(clienteId);
			if (customer != null) {
				
					OrderDAO orderDAO = new OrderDAO();
					long orderCount = orderDAO.countByCustomer(clienteId);
					
					if (orderCount > 0) {
						String message = "Non si puo' eliminare il cliente con ID " + clienteId 
								+ " Perche' ha fatto degli ordini.";
						showMessageBackend(message, request, response);
					} else {
						customerDAO.delete(clienteId);			
						String message = "Il cliente e' stato eliminato.";
						listCustomers(message);
					}
				}
			else {
				String message = "Cliente non trovato con ID " + clienteId + ", "
						+ "oppure e' stato eliminato da un altro admin";
				showMessageBackend(message, request, response);
			}
			
		}
		

	public void showLogin() throws ServletException, IOException {
		forwardToPage("frontend/login.jsp", request, response);	
	}
	
	public void doLogin() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Cliente cliente=customerDAO.checkLogin(email, password);
		
		if(cliente==null) {
			String message="login failed.please check your email and password";
			request.setAttribute("message", message);
			showLogin();
			
		}else {
			request.getSession().setAttribute("loggedCustomer",cliente );
			showCustomerProfile();
			
		}
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		
		forwardToPage("frontend/customer_profile.jsp", request, response);
		
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		forwardToPage("frontend/edit_profile.jsp", request, response);

	}
	public void updateCustomerProfile() throws ServletException, IOException {
		Cliente customer = (Cliente) request.getSession().getAttribute("loggedCustomer");
		updteCustomerFieldsForm(customer);
		customerDAO.update(customer);
		
		showCustomerProfile();
		
	}
}
