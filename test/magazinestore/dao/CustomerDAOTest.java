package magazinestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import magazinestore.entity.Cliente;
import magazinestore.entity.Utenti;

public class CustomerDAOTest {

	private static CustomerDAO customerDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCliente() {
		Cliente customer = new Cliente();
		//customer.setClienteId(4);
		customer.setEmail("prova6@gmail.com");
		customer.setFullname("Prova6");
		customer.setCity("Milan");
		customer.setCountry("Italia");
		customer.setAddress("eee");
		customer.setPassword("87575");
		customer.setPhone("587578575");
		customer.setZipcode("27100");
		
		Cliente savedCustomer = customerDAO.create(customer);
		
		assertTrue(savedCustomer.getClienteId() > 0);
		
	}

	@Test
	public void testGet() {
		Integer customerId = 2;
		Cliente customer = customerDAO.get(customerId);
		
		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Cliente customer = customerDAO.get(2);
		String fullName = "aladiiin";
		customer.setFullname(fullName);
		
		Cliente updateCustomer = customerDAO.update(customer);
		
		assertTrue(updateCustomer.getFullname().equals(fullName));
		
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer customerId = 2;
		customerDAO.delete(customerId);
		Cliente customer = customerDAO.get(1);
		
		assertNull(customer);
	}
	
	@Test
	public void testListAll() {
		List<Cliente> listCliente = customerDAO.listAll();
		for (Cliente cliente : listCliente) {
			System.out.println(cliente.getFullname());
		}
		assertFalse(listCliente.isEmpty());
	}
	
	@Test
	public void testCount() {
		long totalCustomers = customerDAO.count();
		
		assertEquals(2, totalCustomers);
	}
	
	  @Test
	    public void testFindByEmail() {
	    	String email = "prova4@gmail.com";
	    	Cliente user = customerDAO.findByEmail(email);
	    	
	    	assertNotNull(user);
	    }
	  
	  @Test
	    public void testLoginSuccess() {
	    	String email = "omar123@gmail.com";
	    	String password="123";
	    	
	    	Cliente cliente = customerDAO.checkLogin(email,password);
	    	
	    	assertNotNull(cliente);
	    	
	  	    }  


}
